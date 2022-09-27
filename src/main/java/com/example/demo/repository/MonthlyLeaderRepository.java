package com.example.demo.repository;

import com.example.demo.entity.MonthlyLeader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface MonthlyLeaderRepository extends JpaRepository<MonthlyLeader,Integer > {


    //get all info from masterkey table
    @Query(value="SELECT * FROM monthly_leader",nativeQuery = true)
    ArrayList<MonthlyLeader> GetAll();

}
