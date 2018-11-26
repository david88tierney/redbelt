package com.codingdojo.redbelt.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import com.codingdojo.redbelt.models.Idea;


@Entity
@Table(name="users")
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Size(min=1, max=255, message="Name is a required")
	private String name;
	
	
	@Email
	private String email;
	
	@Size(min=8, max=74, message="Password must be between 8-64 char.")
	private String password;
	
	
	@Transient
	@Size(min=8, max=74, message="Password confirmation must be between 8-64 char.")
	private String confirm;
	
	private Date createdAt;
	
	private Date updatedAt;
	
	@OneToMany(mappedBy = "user", fetch= FetchType.LAZY)
	private List<Idea> ideas;
	

	


	public List<Idea> getIdeas() {
		return ideas;
	}

	public void setIdeas(List<Idea> ideas) {
		this.ideas = ideas;
	}

	public User() {
		
	}
	
	public Long getId() {return id;}
	public void setId(Long id) {this.id = id;}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {return email;}
	public void setEmail(String email) {this.email = email;}

	public String getPassword() {return password;}
	public void setPassword(String password) {this.password = password;}

	public String getConfirm() {return confirm;}
	public void setConfirm(String confirm) {this.confirm = confirm;}

	public Date getCreatedAt() {return createdAt;}
	public void setCreatedAt(Date createdAt) {this.createdAt = createdAt;}

	public Date getUpdatedAt() {return updatedAt;}
	public void setUpdatedAt(Date updatedAt) {this.updatedAt = updatedAt;}


	@PrePersist
    protected void onCreate(){
        this.createdAt = new Date();
    }
    @PreUpdate
    protected void onUpdate(){
        this.updatedAt = new Date();
    }

}
