package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;


@Entity
@IdClass(MyKey.class)
public class MonthlyLeader {
    @Id
    private int id;
    private String uid;

    private int prev_point;

    @Id
    private String month_year;

    public MonthlyLeader() {

    }

    public MonthlyLeader(String uid, int prev_point, String month_year) {
        this.uid = uid;
        this.prev_point = prev_point;
        this.month_year = month_year;
    }

    public MonthlyLeader(int prev_point) {
        this.prev_point = prev_point;
    }

    public int getPrev_point() {
        return prev_point;
    }

    public void setPrev_point(int prev_point) {
        this.prev_point = prev_point;
    }

    public MonthlyLeader(String uid, int i) {
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

    public String getMonth_year() {
        return month_year;
    }

    public void setMonth_year(String month_year) {
        this.month_year = month_year;
    }
}
