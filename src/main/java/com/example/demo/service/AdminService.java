package com.example.demo.service;


import com.example.demo.entity.*;
import com.example.demo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class AdminService {

    @Autowired
    private UserDetailsRepository userDetailsRepository;

    @Autowired
    private LeaderBoardRepository leaderBoardRepo;

    @Autowired
    private SocialActionsRepository socialActionsRepository;

    @Autowired
    private FileDetailsRepository fileDetailsRepository;

    @Autowired
    private PointsTableRepository pointsTableRepository;

    @Autowired
    private FilesRepository filesRepository;

    //Get Users By Id
    public UserDetails getUserById(int id) {
        return userDetailsRepository.findById(id).get();

    }

    //Get all users
    public List<UserDetails> getAllUsers() {
        return userDetailsRepository.findAll();

    }

    //Create User
    public UserDetails createUser(UserDetails userDetails) {
        userDetailsRepository.save(userDetails);
        return userDetails;
    }

    //Get user by name using like operator
    public List<UserDetails> getUserByName(String name) {
        return userDetailsRepository.findByNameLike(name);
    }

    //Delete File
    public FileDetails deleteFile(int cid) {
        FileDetails file = fileDetailsRepository.findById(cid).get();
        fileDetailsRepository.delete(file);
        return file;
    }

    //Delete Post
    public SocialActions deletePost(int cid) {
        SocialActions post = socialActionsRepository.findById(cid).get();
        socialActionsRepository.delete(post);
        return post;
    }

    //Update Post
    public SocialActions updatePost(SocialActions socialActions, int id) {
        socialActionsRepository.save(socialActions);
        return socialActions;
    }

    //Get all posts
    public List<SocialActions> getAllPosts() {
      return socialActionsRepository.findAll();
    }

    // get user total post
    public String getUserTotalPostById(String uid) {
        return socialActionsRepository.findTotalPostById(uid);
    }

    //Get User Total Likes By id
    public String getUserTotalLikesById(String uid) {
        return socialActionsRepository.CountTotalLikesById(uid,"like");
    }

    //Get User Total shares By id
    public String getUserTotalSharesById(String uid) {
        return socialActionsRepository.CountTotalSharesById(uid,"share");
    }

    //Get user total download By id
    public String getUserTotaldownloadsById(String uid) {
        return socialActionsRepository.CountTotalDownloadById(uid,"download");
    }

    //Get user total comments by id
    public String getUserTotalcommentsById(String uid) {
        return socialActionsRepository.CountTotalCommentsById(uid,"comment");
    }

// update Action
    public void updateActionPoint(String action, String points) {
        Date date = new Date();

        // getting the object of the Timestamp class
        Timestamp ts = new Timestamp(date.getTime());

        String startdate=String.valueOf(ts);
        String new_action=action+"_old";
        pointsTableRepository.changeActionAndDate(action,startdate,new_action);

        PointsTable pointsTable = new PointsTable();
        pointsTable.setAction(action);
        pointsTable.setPoints(points);
        pointsTable.setStartdate(startdate);

        pointsTableRepository.save(pointsTable);


    }

    // Get Old action points
    public String getoldactionpoints(String action) {
        String new_action=action+"_old";
        String Previous_updated_point=pointsTableRepository.getOldActions(new_action);
        return Previous_updated_point;
    }


    //Delete User by id
    public UserDetails deleteUser(int id) {
        UserDetails entity = userDetailsRepository.findById(id).get();
        userDetailsRepository.delete(entity);
        return null;
    }

    //Update role
    public HashMap<String, String> updateRole(String uid, String role) {

        HashMap<String, String> updatemap = new HashMap<String, String>();
        UserDetails optionalUserDetails = userDetailsRepository.GetDetailsById(uid);
        optionalUserDetails.setRole(role);
        try{
            userDetailsRepository.save(optionalUserDetails);
            userDetailsRepository.getNameByUid(uid);
            updatemap.put("name",userDetailsRepository.getNameByUid(uid));
            updatemap.put("position",userDetailsRepository.getPositionByUid(uid));
            updatemap.put("role",userDetailsRepository.getRoleByUid(uid));
            updatemap.put("msg","role update successfully");

            return updatemap;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            updatemap.put("msg", "role update failed");
            return updatemap;
        }

    }

    //Get List with points
    public ArrayList<HashMap<String, String>> getListwithPoints() {

        ArrayList<LeaderBoard> leaderList = leaderBoardRepo.GetAll();

        ArrayList<HashMap<String,String>> listsofhash = new ArrayList<HashMap<String,String>>();

        for(int i=0;i<leaderList.size();i++)
        {
            HashMap<String,String> map = new HashMap<String,String>();
            String name = userDetailsRepository.getNameByUid(leaderList.get(i).getUid());
            map.put("name", name);
            String points = Integer.toString(leaderList.get(i).getPoints());
            map.put("points", points);

            listsofhash.add(map);
        }

    //total user with total points
        return listsofhash;
    }


    public HashMap<String, String> CountTotalUsersWhoPosted() {

        HashMap<String,String> hash = new HashMap<String,String>();

        String files = filesRepository.CountTotaluserswhoposted();
        hash.put("CountTotalUsersWhoPosted", filesRepository.CountTotaluserswhoposted());
        ///hash.put("CountTotalUsersWhoPosted",);
        return hash;


    }
}
