package com.example.demo.service;

import com.example.demo.entity.Announcement;
import com.example.demo.repository.AnnouncementRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;

import java.time.temporal.TemporalAccessor;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

@Service
public class AnnouncementService {

    @Autowired
    private AnnouncementRepository announcementRepository;


    public HashMap<String, String> getAnnouncement(int id) {

        HashMap<String,String> hash = new HashMap<String,String>();

        Announcement announcement = announcementRepository.findById(id).get();
        hash.put("announcement",announcement.getAnnouncement_body());
        return hash;
    }

    public Announcement writeAnnouncement(Announcement announcement) {

        HashMap<String,String> hash = new HashMap<String,String>();

            announcementRepository.save(announcement);

            hash.put("announcement", announcement.getAnnouncement_body());

            return announcement;

//        LocalDate ldt = LocalDate.now();
//        System.out.println(DateTimeFormatter.ofPattern("dd-MMM-yyyy", Locale.ENGLISH).format(ldt));
////        announcement.setLocalDate(ldt);
//
//        System.out.println("Date Format with MM/dd/yyyy : "+ldt);
//
//        announcementRepository.save(announcement);
//
//
//        hash.put("announcement", announcement.getAnnouncement_body());
//        hash.put("Date", String.valueOf(DateTimeFormatter.ofPattern("dd-MMM-yyyy", Locale.ENGLISH).format(ldt)));
//        return announcement;

    }


    public HashMap<String, String> getAnnounce() {

        HashMap<String,String> hashMap = new HashMap<String, String>();

        Announcement announcement = announcementRepository.getAnnounceOnlyOne();

//        java.sql.Date date=new java.sql.Date(System.currentTimeMillis());
//        announcement.setDate(date);
        hashMap.put("announcement",announcement.getAnnouncement_body());
//        hashMap.put("Date", String.valueOf(announcement.getDate()));
        return hashMap;
    }


}
