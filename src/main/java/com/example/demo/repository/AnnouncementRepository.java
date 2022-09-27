package com.example.demo.repository;

import com.example.demo.entity.Announcement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;


public interface AnnouncementRepository extends JpaRepository<Announcement,Integer> {

    @Query(value = "SELECT * from announcement",nativeQuery = true)
    Announcement getAnnounceById();

    @Query(value = "SELECT * from announcement ORDER BY id DESC LIMIT 1",nativeQuery = true)
    Announcement getAnnounceOnlyOne();

}
