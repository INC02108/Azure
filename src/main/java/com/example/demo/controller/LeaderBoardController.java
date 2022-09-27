package com.example.demo.controller;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.LeaderBoardService;

@CrossOrigin(origins = "*")
@RestController
public class LeaderBoardController {
	
	@Autowired
	private LeaderBoardService leaderBoardService;
	
	@GetMapping("/leaderBoard")
	public ArrayList<HashMap<String, String>> getList()
	{
		return leaderBoardService.topFive();
	}
	
	
	@GetMapping("/usersCount")
	public HashMap<String,String> GetUserCount()
	{
		return leaderBoardService.getUsersCount();
	}
	
	
	@GetMapping("/filesCount")
	public HashMap<String,String> GetFilesCount()
	{
		return leaderBoardService.getFilesCount();
	}



	//To get Leader end of every quarter
	@GetMapping("/leaderInQuarter")
	public ArrayList<HashMap<String, String>> getListInEveryQuarter(){
		return leaderBoardService.updateToZero();
	}



	//to get leader in every month
	@GetMapping("/getleaderinMonth")
	public ArrayList<HashMap<String, String>> getLeaderInMonth(){
		return leaderBoardService.getLeaderInMonth();
	}



}