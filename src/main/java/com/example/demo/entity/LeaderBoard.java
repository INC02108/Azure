package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="LeaderBoard")
public class LeaderBoard {
	
	@Id
	@GeneratedValue
	private int id;
	private String uid;
	private int points;

	private int prev_point;

	public LeaderBoard() {
		
	}

	public LeaderBoard(String uid, int points, int prev_point) {
		this.uid = uid;
		this.points = points;
		this.prev_point = prev_point;
	}

	public LeaderBoard(int prev_point) {
		this.prev_point = prev_point;
	}

	public int getPrev_point() {
		return prev_point;
	}

	public void setPrev_point(int prev_point) {
		this.prev_point = prev_point;
	}

	public LeaderBoard(String uid, int i) {
	}


	public int getId() {
		return id;
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

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

}
