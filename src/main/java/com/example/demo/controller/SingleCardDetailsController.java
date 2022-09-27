package com.example.demo.controller;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.SingleCardDetailsService;


@CrossOrigin(origins = "*")
@RestController
public class SingleCardDetailsController {
	@Autowired
	private SingleCardDetailsService cardservice;
	
	//get single card details by id
	@GetMapping("/cardDetails/component/cid/{cid}")
	public HashMap<String,String> getdetails(@PathVariable int cid)
	{
		return cardservice.details(cid);
	}
	
	//get single card details by poc
	@GetMapping("/cardDetails/component/email/{email}")
	public ArrayList<HashMap<String, String>> getModels(@PathVariable String email)
	{
		return cardservice.value(email);
	}
	
	//get all card details by latest
	@GetMapping("/cardDetails/component/latest/id/{id}")
	public ArrayList<HashMap<String, String>> getLatestModels(@PathVariable int id)
	{
		return cardservice.latest(id);
	}
	
	//get all card details by most downloaded
	@GetMapping("/cardDetails/component/downloaded/id/{id}")
	public ArrayList<HashMap<String, String>> getDownloadedModels(@PathVariable int id)
	{
		return cardservice.most_downloaded(id);
	}
	
	//get all card details by most liked
	@GetMapping("/cardDetails/component/liked/id/{id}")
	public ArrayList<HashMap<String, String>> getLikedModels(@PathVariable int id)
	{
		return cardservice.most_liked(id);
	}

	//get all card details
	@GetMapping("/cardDetails/component/search/{query}/num/{num}")
	public ArrayList<HashMap<String, String>> getSearch(@PathVariable String query,@PathVariable int num)
	{
		return cardservice.searchArtifacts(query,num);
	}
	
	//get comments under a post
	@GetMapping("/comments/cid/{cid}")
	public ArrayList<HashMap<String, String>> getAllComments(@PathVariable int cid)
	{
		return cardservice.comments(cid);
	}
	
	//my Downloads
	@GetMapping("/myDownloads/component/email/{email}")
	public ArrayList<HashMap<String,String>> MyDownloads(@PathVariable String email)
	{
		return cardservice.myDownloads(email);
	}
	
	
	//for list view
	
	//get all card details by latest
	@GetMapping("/cardDetails/component/latestlist/id/{id}")
	public ArrayList<HashMap<String, String>> getLatestModelsList(@PathVariable int id)
	{
		return cardservice.latestList(id);
	}
	
	//get all card details by most downloaded
	@GetMapping("/cardDetails/component/downloadedlist/id/{id}")
	public ArrayList<HashMap<String, String>> getDownloadedModelsList(@PathVariable int id)
	{
		return cardservice.most_downloaded_list(id);
	}
	
	//get all card details by most liked
	@GetMapping("/cardDetails/component/likedlist/id/{id}")
	public ArrayList<HashMap<String, String>> getLikedModelsList(@PathVariable int id)
	{
		return cardservice.most_liked_list(id);
	}
	

}
