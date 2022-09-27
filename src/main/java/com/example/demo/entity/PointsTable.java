package com.example.demo.entity;

import javax.persistence.*;

@Entity
@Table(name="PointsTable")
public class PointsTable {
	



//	private int new_id;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id ;

	private String action;
	private String points;
	private String startdate;
	private String enddate;
	
	
	public PointsTable() {
		
	}


	public long getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}

//	public int getNew_id() {
//		return new_id;
//	}
//
//	public void setNew_id(int new_id) {
//		this.new_id = new_id;
//	}
	
	


	public String getAction() {
		return action;
	}


	public void setAction(String action) {
		this.action = action;
	}


	public String getPoints() {
		return points;
	}


	public void setPoints(String points) {
		this.points = points;
	}


	public String getStartdate() {
		return startdate;
	}


	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}

	public PointsTable(int id) {
		this.id = id;
	}

	public String getEnddate() {
		return enddate;
	}


	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}


	public PointsTable(String action, String points, String startdate, String enddate) {
		
//		this.new_id = new_id;
		this.action = action;
		this.points = points;
		this.startdate = startdate;
		this.enddate = enddate;
	}
}
