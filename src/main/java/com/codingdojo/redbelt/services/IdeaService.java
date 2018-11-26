package com.codingdojo.redbelt.services;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.codingdojo.redbelt.models.Idea;
import com.codingdojo.redbelt.repositories.IdeaRepository;

@Service
public class IdeaService {
	
	private final IdeaRepository iR;
	
	public IdeaService(IdeaRepository iR) {
		this.iR= iR;
	}
	
	public Idea create(Idea idea) {
		return iR.save(idea);
	}
	
	public ArrayList<Idea> findAll(){
		return (ArrayList<Idea>) iR.findAll();
	}
	
	public Idea findById(Long id) {
		return iR.findById(id).get();
	}

	public Idea findName(String name) {
		return iR.findByName(name);
	}
	public Idea update(Idea idea) {
		return iR.save(idea);
	}
	
	public void destroy(Long id) {
		iR.deleteById(id);
	}
}