package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.PointsTable;

import javax.transaction.Transactional;


@Repository
public interface PointsTableRepository extends JpaRepository<PointsTable, Integer> {
	
	@Query(value=" SELECT points from points_table where action=:action order by id desc LIMIT 1", nativeQuery = true)
	Integer pointsByAction(@Param("action") String action);
	
	//show all counts
	
	@Query(value="SELECT COUNT(*) FROM all_about_user",nativeQuery = true)
	Integer numberofusers();
	
	@Query(value="SELECT COUNT(*) FROM files",nativeQuery = true)
	Integer numberofartifacts();


	//Change action and Date
	@Modifying
	@Transactional
	@Query(value = "update points_table set action=:new_action,enddate=:date,points=points where action=:act",nativeQuery = true)
	void changeActionAndDate(@Param("act") String action,@Param("date") String date,
							 @Param("new_action") String new_action);


//	@Transactional
//	@Modifying
//	@Query(value= "INSERT INTO points_table (id,action,points,startdate) VALUES ('10',:action,:points,:startdate)",nativeQuery = true)
//	int insertNewPoints(@Param("action") String action,@Param("points") String points ,@Param("startdate") String startdate);

//	@Modifying
//	@Transactional
//	@Query(value = "INSERT INTO points_table (action,points,startdate) VALUES (act,pt,date)",nativeQuery = true)
//	void change1(@Param("act") String action,@Param("pt") String points ,@Param("date") String date);

	//get old actions points
	@Query(value="SELECT points FROM points_table where action=:action order by enddate desc LIMIT 1",nativeQuery = true)
	String getOldActions(@Param("action") String action);
}
