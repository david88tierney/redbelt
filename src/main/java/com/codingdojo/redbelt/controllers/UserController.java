package com.codingdojo.redbelt.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.codingdojo.redbelt.services.IdeaService;
import com.codingdojo.redbelt.models.Idea;
import com.codingdojo.redbelt.models.User;
import com.codingdojo.redbelt.services.UserService;



@Controller
@RequestMapping("/users")
public class UserController {
	public UserService uS;
	private final IdeaService iS;
	
	public UserController(UserService uS, IdeaService iS) {
		this.uS = uS;
		this.iS = iS;

	}
	
	@GetMapping("")
	public String showIndex(@ModelAttribute("user") User user) {
		return "index";
	}
	
	@PostMapping("")
	public String register(@Valid @ModelAttribute("user") User user, BindingResult res, Model model) {
		if(res.hasErrors()) {
			return "index";
		} else {
			if(!user.getPassword().equals(user.getConfirm())) {
				model.addAttribute("userError", "Passwords dont match");
				return "index";
			} else {
				String pw = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
				user.setPassword(pw);
				
				User exists= uS.findByEmail(user.getEmail());
				if(exists != null) {
					model.addAttribute("userError", "A user with this email already exists");
					return "index";
				} else {
					uS.create(user);
					return "redirect:/users";
				}
			}
		}
	}
		
	
	@PostMapping("/login")
	public String login( @RequestParam("email") String email, @RequestParam("password") String password, Model model, HttpSession session) {
		if(email.length() < 1) {
			model.addAttribute("loginError", "Invalid Credentials");
			model.addAttribute("user", new User());
			return "index";
		}
		if(password.length() < 8) {
			model.addAttribute("loginError", "Invalid Credentials");
			model.addAttribute("user", new User());
			return "index";
		}
		
		User u = uS.findByEmail(email);
		
		if(u == null) {
			model.addAttribute("loginError", "Invalid Credentials");
			model.addAttribute("user", new User());
			return "index";
		} else {
			boolean matches = BCrypt.checkpw(password, u.getPassword());
			
			if(matches) {
				session.setAttribute("user", u.getId());
//				session.setAttribute("user", u.getName());
				return "redirect:/users/dashboard";
				} else {
					model.addAttribute("loginError", "Invalid Credentials");
					model.addAttribute("user", new User());
					return "index";
				}
			}
		}
	
	@GetMapping("/dashboard")
	public String dashboard(Model model, HttpSession session) {
		Long id = (Long) session.getAttribute("user");
		
		if(id == null) {
			return "redirect:/users";
		} else {
			User u = uS.findById(id);
			model.addAttribute("user", u);
			
			model.addAttribute("ideas", iS.findAll());		
			
			return "dashboard";
		}
	}
	
    @GetMapping("/add") 
    public String addRoute(@ModelAttribute("idea") Idea idea) {
    	return "create";
    }
    
	@PostMapping("/create")
	public String create(@Valid @ModelAttribute("idea")Idea idea, BindingResult res, Model model, HttpSession session) {

		
		if(res.hasErrors()) return "create";

		idea.setUser(uS.findById((Long)session.getAttribute("user")));
		
		
		iS.create(idea);
		return "redirect:/users/dashboard";
	}
	
	@GetMapping("/ideas/{idea_id}")
	public String displayIdea(@PathVariable("idea_id") Long idea_id, Model model, HttpSession session){
		
		Idea idea = iS.findById(idea_id);

		model.addAttribute("idea", idea );
		
		return "view";
	}
    
	@GetMapping("/edit/{idea_id}")
	public String editPage(@PathVariable("idea_id") Long idea_id, Model model){
		Idea idea = iS.findById(idea_id);
		model.addAttribute("idea", idea );
		return "edit";
	}
	
	@PostMapping("/update/{idea_id}")
	public String update(@Valid @ModelAttribute("newIdea") Idea newIdea, @PathVariable("idea_id") Long ideaId, BindingResult result, HttpSession session) {
		Idea tempIdea = iS.findById(ideaId);
		tempIdea.setName(newIdea.getName());
		tempIdea.setUser(uS.findById((Long)session.getAttribute("user")));
		iS.update(tempIdea);
		return "redirect:/users/dashboard";
	}
	
	@PostMapping ("{id}/destroy")
	public String delete(@PathVariable("id")Long id) {
		iS.destroy(id);
		return "redirect:/users/dashboard";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
