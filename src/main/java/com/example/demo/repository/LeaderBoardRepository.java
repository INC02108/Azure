package com.example.demo.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.LeaderBoard;

import javax.transaction.Transactional;

@Repository
public interface LeaderBoardRepository extends JpaRepository<LeaderBoard,Integer> {
	
	@Query(value="SELECT * FROM leader_board WHERE uid=:uid",nativeQuery = true)
	LeaderBoard findLeaderBoardDetailsByUid(@Param("uid") String uid);
	
	public LeaderBoard findByuid(String uid);
	
	//Get top 5 from leader_board

	@Query(value="SELECT * FROM leader_board ORDER BY points DESC LIMIT 5",nativeQuery = true)
	ArrayList<LeaderBoard> GetTopFive();

	//get points top 1
	@Query(value="SELECT * FROM leader_board ORDER BY points DESC LIMIT 1",nativeQuery = true)
	LeaderBoard GetTopOne();



	//insert
	//@Query(value = "INSERT INTO leader_board ")
	
	//get points by uid
	@Query(value="SELECT points FROM leader_board WHERE uid=:uid",nativeQuery=true)
	Integer GetPointsFromUid(@Param("uid") String uid);
	
	//get image string by uid
	@Query(value="SELECT image FROM all_about_user WHERE uid=:uid",nativeQuery = true)
	String GetImageUrlByUid(@Param("uid") String uid);


	//get all user's point
	@Query(value="SELECT * FROM leader_board ORDER BY points DESC",nativeQuery = true)
	ArrayList<LeaderBoard> GetAll();

	//set point to 0 in every quarter
	@Modifying
	@Transactional
	@Query(value = "update leader_board set points=0", nativeQuery = true)
	int assignToZero();

//	//assign points to prev_Quarter_points
//	@Modifying
//	@Transactional
//	@Query(value = "UPDATE `leader_board` SET prev_point=points", nativeQuery = true)
//	int copyFromPoints();


	@Modifying
	@Transactional
	@Query(value = "UPDATE `leader_board` SET points_add=points", nativeQuery = true)
	int copyTopoints_Add();


}
