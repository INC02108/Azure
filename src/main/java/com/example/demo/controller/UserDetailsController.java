
package com.example.demo.controller;

import java.io.IOException;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.service.UserDetailsService;

@CrossOrigin(origins = "*")
@RestController
public class UserDetailsController {
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@PostMapping("/addUserDetails")
	public HashMap<String, String> addUserDetails(@RequestParam("uid") String uid,@RequestParam("name") String name,@RequestParam("email") String email,@RequestParam("position") String position)
		{
			return userDetailsService.saveUserDetails(uid,name, email,position);
		}
	
	@GetMapping("/userDetails/uid/{uid}")
	public HashMap<String, String> getAllDetailsById(@PathVariable String uid)
	{
		return userDetailsService.getReqDetails(uid);
	}
	
	@GetMapping("/userDetails/email/{email}")
	public HashMap<String, String> getAllDetailsByEmail(@PathVariable String email)
	{
		return userDetailsService.getReqDetailsByEmail(email);
	}
	
	//Get Top Component Details
	@GetMapping("/userTopComponentDetails/email/{email}")
	public HashMap<String, String> getTopArtifacts(@PathVariable String email)
	{
		return userDetailsService.getTopComponentsByEmail(email);
	}
	
	
	//update comment details

	@PutMapping("/updateDetailsWithImage")
	public HashMap<String, String> updateUserDetails(@RequestParam("uid") String uid,@RequestParam("altemail") String altemail,@RequestParam("linkedin") String linkedin,@RequestParam("github") String github,@RequestParam("phno") String phno,@RequestParam("bio") String bio,@RequestParam("foe") String foe,@RequestParam("image") MultipartFile image) throws IOException 
	{

			return userDetailsService.updatewithimage(uid, altemail, linkedin, github, phno, bio, foe,image);
	}

	@PutMapping("/updateDetailsWithoutImage")
	public HashMap<String, String> updateUserWithoutDetails(@RequestParam("uid") String uid,@RequestParam("altemail") String altemail,@RequestParam("linkedin") String linkedin,@RequestParam("github") String github,@RequestParam("phno") String phno,@RequestParam("bio") String bio,@RequestParam("foe") String foe)
	{

			return userDetailsService.updatewithoutimage(uid, altemail, linkedin, github, phno, bio, foe);
	}
	
	
	@GetMapping("/adminCheck/email/{email}")
	public boolean AdminCheck(@PathVariable String email)
	{
		return userDetailsService.getRoleByEmail(email);
	}
	
	

}
