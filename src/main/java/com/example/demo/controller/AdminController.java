package com.example.demo.controller;

import com.example.demo.entity.FileDetails;
import com.example.demo.entity.SocialActions;
import com.example.demo.entity.UserDetails;
import com.example.demo.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController

public class AdminController {

    @Autowired
    private AdminService adminService;

    //get all users
    @GetMapping("/getAllUsers")
    public List<UserDetails> getAllUsers(){
        return this.adminService.getAllUsers();
    }

    //get user by id
    @GetMapping("/user/{id}")
    public UserDetails getUserById(@PathVariable int id) {
        return this.adminService.getUserById(id);
    }

    //Create a user
    @PostMapping("/users")
    public void CreateUser(@RequestBody UserDetails userDetails){
        adminService.createUser(userDetails);
    }

    //Get user by name using like operator
    @GetMapping("/getUserByName/{name}")
    public List<UserDetails> getUserByName(@PathVariable String name){
        return this.adminService.getUserByName(name);
    }

    //delete user file
    @DeleteMapping("/deleteFile/{cid}")
    public FileDetails deleteById(@PathVariable int cid){
       return this.adminService.deleteFile(cid);
    }

    //update user post
   @RequestMapping(method = RequestMethod.PUT, value = "/updatePost/{id}")
    public SocialActions updatePost(@RequestBody SocialActions socialActions, @PathVariable int id){
       return this.adminService.updatePost(socialActions,id);
    }

    //get all posts
    @GetMapping("/getAllPosts")
    public List<SocialActions> getAllPosts(){
        return this.adminService.getAllPosts();
    }

    //Get user total post By Id
    @GetMapping("/getusertotalpost/{uid}")
    public String getUserTotalPostById(@PathVariable String uid) {
        return this.adminService.getUserTotalPostById(uid);
    }

    //Get user total Likes By id
    @GetMapping("/getusertotalLikes/{uid}")
    public String getUserTotalLikesById(@PathVariable String uid) {
        return  adminService.getUserTotalLikesById(uid);
    }


    //Get users total Shares By id
    @GetMapping("/getusertotalshares/{uid}")
    public String getUserTotalShareById(@PathVariable String uid) {
        return  adminService.getUserTotalSharesById(uid);
    }

    //Get users total download By id
    @GetMapping("/getusertotaldownloads/{uid}")
    public String getUserTotaldownloadById(@PathVariable String uid) {
        return  adminService.getUserTotaldownloadsById(uid);
    }

    //Get users total comments By id
    @GetMapping("/getusertotalcomments/{uid}")
    public String getUserTotalCommentById(@PathVariable String uid) {
        return  adminService.getUserTotalcommentsById(uid);
    }

   //Update Action Points
   @PostMapping("/updateActionPoint/Action")
   public void updateLikePoint(@RequestParam("action") String action,@RequestParam("points")  String points) throws IOException
   {
       adminService.updateActionPoint(action, points);
   }

   //Get old actions
   @GetMapping ("/GetOldActionPoints/Action/{action}")
   public String GetOldActionPoints(@PathVariable String action) {
        return this.adminService.getoldactionpoints(action);
   }

    //Total users with points
    @GetMapping("/getTotalUsersWithTotaLPoints")
    public ArrayList<HashMap<String, String>> getList()
    {
        return adminService.getListwithPoints();
    }


    //Assign role to user
    @PutMapping("/updateRole/{uid}")
    public HashMap<String, String> updateRoleByAdmin(@PathVariable("uid") String uid, @RequestParam(name = "role") String role ) throws IOException
    {
        return this.adminService.updateRole(uid, role);
    }

    //Delete user by id
    @DeleteMapping("/deleteUserDetails/{id}")
    public UserDetails deleteUser(@PathVariable int id){
        return this.adminService.deleteUser(id);
    }

    //Get total users who posted
    @GetMapping("/CountTotalUserWhoPosted")
    public HashMap<String, String> CountTotalUserWhoPosted(){
        return adminService.CountTotalUsersWhoPosted();
    }



}
