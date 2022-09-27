package com.example.demo.controller;

import java.text.ParseException;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.Announcement;
import com.example.demo.service.AnnouncementService;

@CrossOrigin(origins = "*")
@RestController
public class AnnouncementController {

    @Autowired
    private AnnouncementService announcementService;

    //To get Announcement by id
    @GetMapping("/getAnnouncement/{id}")
    public HashMap<String,String> getAnnouncement(@PathVariable int id)
    {
        return this.announcementService.getAnnouncement(id);
    }

    //To create Announcement
    @PostMapping("/addAnnouncement")
    public Announcement writeAnnouncement(@RequestBody Announcement announcement)
    {
        return this.announcementService.writeAnnouncement(announcement);
    }

    //To get only One Announcement
    @GetMapping("/getOneAnnouncement")
    public HashMap<String,String> getAnnounce()  {
        return this.announcementService.getAnnounce();
    }

}
