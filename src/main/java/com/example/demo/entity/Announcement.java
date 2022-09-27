package com.example.demo.entity;



import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "announcement")
public class Announcement {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String announcement_body;

    public Announcement() {
    }

    public Announcement(String announcement) {
    }

    public Announcement(int id, String announcement_body) {
        this.id = id;
        this.announcement_body = announcement_body;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAnnouncement_body() {
        return announcement_body;
    }

    public void setAnnouncement_body(String announcement_body) {
        this.announcement_body = announcement_body;
    }


}