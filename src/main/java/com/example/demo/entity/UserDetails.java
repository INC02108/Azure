package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="all_about_user")
public class UserDetails {
	@Id
	@GeneratedValue
	private int id;
	
	private String uid;
	private String name;
	private String email;
	private String position;
	private String altEmail;
	private String linkedin;
	private String github;
	private String phno;
	@Column(columnDefinition = "TEXT")
	private String bio;
	
	private String foe;
	//private String password;
	
//	private String imagename;
	
	
	private String image;
	private String role;

	public UserDetails() {
		
	}
	
	





	public UserDetails(String uid, String name, String email, String position, String image, String role) {
		
		this.uid = uid;
		this.name = name;
		this.email = email;
		this.position = position;
		this.image = image;
		this.role = role;
	}







	public UserDetails(String uid, String name, String email, String position, String altEmail, String linkedin,
			String github, String phno, String bio, String foe, String role) {
		this.uid = uid;
		this.name = name;
		this.email = email;
		this.position = position;
		this.altEmail = altEmail;
		this.linkedin = linkedin;
		this.github = github;
		this.phno = phno;
		this.bio = bio;
		this.foe = foe;
		this.role = role;
	}









	public int getId() {
		return id;
	}
	
	

	public String getRole() {
		return role;
	}


	public void setRole(String role) {
		this.role = role;
	}


	public void setId(int id) {
		this.id = id;
	}









	public String getUid() {
		return uid;
	}









	public void setUid(String uid) {
		this.uid = uid;
	}









	public String getName() {
		return name;
	}









	public void setName(String name) {
		this.name = name;
	}









	public String getEmail() {
		return email;
	}









	public void setEmail(String email) {
		this.email = email;
	}









	public String getPosition() {
		return position;
	}




	public void setPosition(String position) {
		this.position = position;
	}









	public String getAltEmail() {
		return altEmail;
	}









	public void setAltEmail(String altEmail) {
		this.altEmail = altEmail;
	}









	public String getLinkedin() {
		return linkedin;
	}









	public void setLinkedin(String linkedin) {
		this.linkedin = linkedin;
	}









	public String getGithub() {
		return github;
	}









	public void setGithub(String github) {
		this.github = github;
	}









	public String getPhno() {
		return phno;
	}









	public void setPhno(String phno) {
		this.phno = phno;
	}









	public String getBio() {
		return bio;
	}









	public void setBio(String bio) {
		this.bio = bio;
	}









	public String getFoe() {
		return foe;
	}









	public void setFoe(String foe) {
		this.foe = foe;
	}









	public String getImage() {
		return image;
	}









	public void setImage(String image) {
		this.image = image;
	}











	
	
	




}
