package com.example.demo.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

import com.example.demo.entity.MonthlyLeader;
import com.example.demo.repository.MonthlyLeaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.LeaderBoard;
import com.example.demo.repository.LeaderBoardRepository;
import com.example.demo.repository.PointsTableRepository;
import com.example.demo.repository.UserDetailsRepository;

@Service
public class LeaderBoardService {

	@Autowired
	private LeaderBoardRepository leaderBoardRepo;

	@Autowired
	private MonthlyLeaderRepository monthlyLeaderRepository;

	@Autowired
	private UserDetailsRepository userRepo;

	@Autowired
	private PointsTableRepository pointsRepo;

	public void updateLeaderBoard(String uid, int points) {
		//System.out.print("leaderboarduid"+uid);
		LeaderBoard leader = leaderBoardRepo.findByuid(uid);
		int point = leader.getPoints();
		point = point + points;
		leader.setPoints(point);
		leaderBoardRepo.save(leader);

	}

	//get Top 5
	public ArrayList<HashMap<String, String>> topFive() {
		ArrayList<LeaderBoard> leaderList = leaderBoardRepo.GetTopFive();

		ArrayList<HashMap<String, String>> listsofhash = new ArrayList<HashMap<String, String>>();

		for (int i = 0; i < leaderList.size(); i++) {
			HashMap<String, String> map = new HashMap<String, String>();
			String name = userRepo.getNameByUid(leaderList.get(i).getUid());
			map.put("name", name);
			String points = Integer.toString(leaderList.get(i).getPoints());
			map.put("points", points);
			String email = userRepo.getEmailByUid(leaderList.get(i).getUid());
			map.put("email", email);
			String imageurl = leaderBoardRepo.GetImageUrlByUid(leaderList.get(i).getUid());
			map.put("imageUrl", imageurl);
			listsofhash.add(map);
		}


		return listsofhash;

	}


	//get number of users
	public HashMap<String, String> getUsersCount() {
		HashMap<String, String> newmap = new HashMap<String, String>();
		int userCount = pointsRepo.numberofusers();
		newmap.put("numofusers", Integer.toString(userCount));
		return newmap;

	}


	//get number of users
	public HashMap<String, String> getFilesCount() {
		HashMap<String, String> newfile = new HashMap<String, String>();
		int filesCount = pointsRepo.numberofartifacts();
		newfile.put("numoffiles", Integer.toString(filesCount));
		return newfile;

	}


	//getting leader in every quarter and storing leader of prev quarter
	public ArrayList<HashMap<String, String>> updateToZero() {
		LocalDate currentDate = LocalDate.now();

		// Get day from date
		int day = currentDate.getDayOfMonth();

		// Get month from date
		String month = String.valueOf(currentDate.getMonth());

		System.out.println("Day: " + day);
		System.out.println("Month: " + month);

		if (day == 1) {
			if (month == "JULY" || month == "AUGUST" || month == "OCTOBER" || month == "JANUARY") {


				//int prev_LeaderBoard = leaderBoardRepo.copyFromPoints();

				int addedPoints = leaderBoardRepo.copyTopoints_Add();

				//int leaderBoard = leaderBoardRepo.assignToZero();
				//leaderBoard.setPoints(0);
				System.out.println("Success");
			}
			System.out.println("1st if");
		} else {
			System.out.println("Outer else");
		}


		ArrayList<LeaderBoard> leaderList = leaderBoardRepo.GetTopFive();

		ArrayList<HashMap<String, String>> listsofhash = new ArrayList<HashMap<String, String>>();

		for (int i = 0; i < leaderList.size(); i++) {
			HashMap<String, String> map = new HashMap<String, String>();
			String name = userRepo.getNameByUid(leaderList.get(i).getUid());
			map.put("name", name);
			String points = Integer.toString(leaderList.get(i).getPoints());
			map.put("points", points);
			String email = userRepo.getEmailByUid(leaderList.get(i).getUid());
			map.put("email", email);
			String imageurl = leaderBoardRepo.GetImageUrlByUid(leaderList.get(i).getUid());
			map.put("imageUrl", imageurl);
			listsofhash.add(map);
		}


		return listsofhash;
	}


//	public ArrayList<HashMap<String, String>> leaderInMonth() {
//
//		LocalDate currentDate=LocalDate.now();
//
//		int day = currentDate.getDayOfMonth();
//
//		String month = String.valueOf(currentDate.getMonth());
//
//		int year = currentDate.getYear();
//
//		if(day==1)
//		{
//			LeaderBoard leaderList = leaderBoardRepo.GetTopOne();
//
//			LeaderBoard leaderBoard = null;
//
//			leaderBoard=leaderList;
//
//			leaderBoard.setPrev_point(month + "-" + String.valueOf(year));
//			//leaderBoard.setPoints();
//
//		}
//		return null;
//	}

	//to get leader in every month
	public ArrayList<HashMap<String, String>> getLeaderInMonth() {
		LocalDate currentDate = LocalDate.now();

		//To get month -1
		LocalDate earlier = currentDate.minusMonths(1);

		int day = currentDate.getDayOfMonth();

		//Storing the prev month
		String month = String.valueOf(earlier.getMonth());

		month = month.toLowerCase(Locale.ROOT);
		month = month.substring(0, 1).toUpperCase() + month.substring(1);
		month = month.substring(0,3);
		//System.out.println(month);

		String year = String.valueOf(currentDate.getYear());
//		System.out.println(year.substring(2,4));

		LeaderBoard leaderList = leaderBoardRepo.GetTopOne();



		MonthlyLeader monthlyLeader = new MonthlyLeader();
		if (day == 1) {
			//int prev_LeaderBoard = leaderBoardRepo.copyFromPoints();

			int id = leaderList.getId();
			String uid = leaderList.getUid();
			int points = leaderList.getPoints();

			String Date = month+"-"+year.substring(2,4);

			monthlyLeader.setId(id);
			monthlyLeader.setUid(uid);
			monthlyLeader.setPrev_point(points);
			monthlyLeader.setMonth_year(Date);
			//System.out.println(Date);

			monthlyLeaderRepository.save(monthlyLeader);
		}
		ArrayList<MonthlyLeader> monthlyLeader1 = monthlyLeaderRepository.GetAll();
		ArrayList<HashMap<String, String>> listsofhash = new ArrayList<HashMap<String, String>>();

		for (int i = 0; i < monthlyLeader1.size(); i++) {//need to improve
			HashMap<String, String> map = new HashMap<String, String>();
			String name = userRepo.getNameByUid(monthlyLeader1.get(i).getUid());
			map.put("name", name);
			//System.out.println(name);
			int points = monthlyLeader1.get(i).getPrev_point();//Integer.toString(leaderList.getPoints());
			map.put("points", String.valueOf(points));
			//System.out.println(points);
			String email = userRepo.getEmailByUid(monthlyLeader1.get(i).getUid());
			map.put("email", email);
			String month_year = monthlyLeader1.get(i).getMonth_year();
			map.put("Date", month_year);
			//System.out.println(month_year);
			String imageurl = leaderBoardRepo.GetImageUrlByUid(monthlyLeader1.get(i).getUid());
			map.put("imageUrl", imageurl);

			listsofhash.add(map);
		}
		return listsofhash;

	}
}