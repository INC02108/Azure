package com.example.demo.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.SocialActions;
import com.example.demo.service.SocialActionsService;

@CrossOrigin(origins = "*")
@RestController
public class SocialActionController {
	
	@Autowired
	private SocialActionsService socialService;


	@PostMapping("/addSocialTableDetails")
	public HashMap<String, String> addActions(@RequestBody SocialActions social)
	{
		return socialService.getSocialActions(social);

	}
	
	@GetMapping("/getLikesCount/cid/{cid}")
	public Integer getLikesCount(@PathVariable int cid)
	{
		return socialService.getLikesCount(cid);
	}
	
//	@GetMapping("/getPoints/action/{action}")
//	public Integer getPoints(@PathVariable String action)
//	{
//		return socialService.checkPoints(action);
//	}
	
	@GetMapping("/getShareCount/cid/{cid}")
	public Integer getShareCount(@PathVariable int cid)
	{
		return socialService.getSharesCount(cid);
	}
	
	@GetMapping("/getDownloadCount/cid/{cid}")
	public Integer getDownloadCount(@PathVariable int cid)
	{
		return socialService.getDownloadsCount(cid);
	}

	@GetMapping("/getCommentsCount/cid/{cid}")
	public Integer getCommentCount(@PathVariable int cid)
	{
		return socialService.getCommentsCount(cid);
	}
	
	@PutMapping("/updateComment")
	public HashMap<String, String> updateUserDetails(@RequestParam("id") int id,@RequestParam("comment") String comment) 
	{
		return socialService.updateComment(id, comment);
	}
	
	
	@DeleteMapping("/delete/id/{id}")
	public HashMap<String,String> deleteComment(@PathVariable int id)
	{
		return socialService.delete(id);
	}


}

