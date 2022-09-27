package com.example.demo.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.LeaderBoard;
import com.example.demo.entity.Files;
import com.example.demo.entity.UserDetails;
import com.example.demo.repository.FileDetailsRepository;
import com.example.demo.repository.FilesRepository;
import com.example.demo.repository.LeaderBoardRepository;
import com.example.demo.repository.SocialActionsRepository;
import com.example.demo.repository.UserDetailsRepository;

@Service
public class UserDetailsService {
	
	@Autowired
	private UserDetailsRepository userDetailsRepo;
	
	@Autowired
	private FilesRepository filesRepo;
	
	@Autowired
	private SocialActionsRepository socialRepo;

	
	@Autowired
	private LeaderBoardRepository leaderBoardRepo;
	
	@Autowired
	private FileDetailsRepository fileDetailsRepo;
	
	
	public HashMap<String,String> saveUserDetails(String uid,String name,String email,String position)
	{
		HashMap<String,String> details = new HashMap<String,String>();
		
    	UserDetails userDet = userDetailsRepo.GetDetailsById(uid);
    	if(userDet == null)
    	{
    		try
    		{
    			String Role= "User";
    			UserDetails det = new UserDetails(uid,name,email,position,"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",Role);
    			userDetailsRepo.save(det);
    			LeaderBoard luser = new LeaderBoard(uid,0);
    			
    			leaderBoardRepo.save(luser);
    			details.put("status", "1");
    			details.put("imageurl","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");
    			details.put("userRole", Role);
    			return details;
    		}
    		catch(Exception e)
    		{
    			e.printStackTrace();
    			details.put("status", "0");
    			return details;
    			
    		} 
    	}
    	else
    	{
    		details.put("status", "1");
			details.put("imageurl", leaderBoardRepo.GetImageUrlByUid(uid));
			details.put("userRole",userDetailsRepo.GetRoleByEmail(email));
			return details;
    	}
    		
	}
	
	
	//get all details of user
	
	public HashMap<String,String> getReqDetails(String uid)
	{
		HashMap<String,String> hash = new HashMap<String,String>();
		
		UserDetails curr_user = userDetailsRepo.GetDetailsById(uid);
		hash.put("name", curr_user.getName());
		hash.put("email", curr_user.getEmail());
		hash.put("alternate_email", curr_user.getAltEmail());
		hash.put("position", curr_user.getPosition());
		hash.put("phone_num",curr_user.getPhno());
		hash.put("bio", curr_user.getBio());
		hash.put("linkedin_url", curr_user.getLinkedin());
		hash.put("github_url", curr_user.getGithub());
		hash.put("field_of_expertise", curr_user.getFoe());
		hash.put("NumberOfUploads", Integer.toString(filesRepo.findTotalUploadsByUser(filesRepo.GetEmailFromUid(uid))));
		hash.put("NumberOfDownloads", Integer.toString(socialRepo.findCountOfSocialActionByUser(uid, "Download")));
		hash.put("imageUrl", leaderBoardRepo.GetImageUrlByUid(uid));
		
		return hash;		
		
	}
	
	
	//get all details of user
	
	public HashMap<String,String> getReqDetailsByEmail(String email)
	{
		HashMap<String,String> hash = new HashMap<String,String>();
		
		UserDetails curr_user = userDetailsRepo.GetDetailsByEmail(email);
	
		hash.put("name", curr_user.getName());
		hash.put("email", email);
		hash.put("alternate_email", curr_user.getAltEmail());
		hash.put("position", curr_user.getPosition());
		hash.put("phone_num",curr_user.getPhno());
		hash.put("bio", curr_user.getBio());
		hash.put("linkedin_url", curr_user.getLinkedin());
		hash.put("github_url", curr_user.getGithub());
		hash.put("field_of_expertise", curr_user.getFoe());
		hash.put("NumberOfUploads", Integer.toString(filesRepo.findTotalUploadsByUser(filesRepo.GetEmailFromUid(curr_user.getUid()))));
		HashSet<Integer> listofcid = socialRepo.getUniqueCid(userDetailsRepo.getUidByEmail(email),"Download");
		ArrayList<Integer> cidList = new ArrayList<>(listofcid);
		int count = 0;
		for(int i=0;i<cidList.size();i++)
		{
			
			if(fileDetailsRepo.checkIfComponentExist(cidList.get(i)) != null)
			{
				count = count+1;
			}
		}
		hash.put("NumberOfDownloads", Integer.toString(count));
		int points = (leaderBoardRepo.GetPointsFromUid(curr_user.getUid())<0)?0:leaderBoardRepo.GetPointsFromUid(curr_user.getUid());
		hash.put("user_points", Integer.toString(points));
		hash.put("imageUrl", leaderBoardRepo.GetImageUrlByUid(curr_user.getUid()));
		
		return hash;		
		
	}
	
	
	//get top component details of user
	
	public HashMap<String,String> getTopComponentsByEmail(String email)
	{
		HashMap<String,String> res = new HashMap<String,String>();
		
//		UserDetails curr_user = userDetailsRepo.GetDetailsByEmail(email);
		int topDownloads = 0;
		int topShared = 0;
		int topLiked = 0;
		//top comment
		int topComment = 0;
		String topDownloadsName = "";
		String topSharedName = "";
		String topLikedName = "";
		String topCommentName = "";

		int topDownloadsId = 0;
		int topSharedId = 0;
		int topLikedId = 0;
		int topCommentId = 0;


		List<Files> userComponents = filesRepo.findByEmail(email);

		for(int i=0;i<userComponents.size();i++)
		{
			int component_id = userComponents.get(i).getCid();

			Files curr_obj = userComponents.get(i);
			String component_name = curr_obj.getComponent();
			int ndownload = socialRepo.CountSocialActions(component_id,"Download");
			int nlikes = socialRepo.CountSocialActions(component_id,"Like");
			int nshares = socialRepo.CountSocialActions(component_id,"Share");
			int ncomment = socialRepo.CountSocialActions(component_id,"Comment");

			if(ndownload>topDownloads){
				topDownloads = ndownload;
				topDownloadsId = component_id;
				topDownloadsName = component_name;
			}
			if(nshares>topShared){
				topShared = nshares;
				topSharedId = component_id;
				topSharedName = component_name;
			}
			if(nlikes>topLiked){
				topLiked = nlikes;
				topLikedId = component_id;
				topLikedName = component_name;
			}
			if (ncomment>topComment){
				topComment = ncomment;
				topCommentId = component_id;
				topCommentName = component_name;
			}

		}

		res.put("topDownloads",Integer.toString(topDownloads));
		res.put("topDownloadsName",topDownloadsName);
		res.put("topDownloadsId",Integer.toString(topDownloadsId));

		res.put("topShared",Integer.toString(topShared));
		res.put("topSharedName",topSharedName);
		res.put("topSharedId",Integer.toString(topSharedId));

		res.put("topLiked",Integer.toString(topLiked));
		res.put("topLikedName",topLikedName);
		res.put("topLikedId",Integer.toString(topLikedId));

		res.put("topComment",Integer.toString(topComment));
		res.put("topCommentName",topCommentName);
		res.put("topCommentId",Integer.toString(topCommentId));
		return res;

	}
	
	
	//update user details with image
	
	public HashMap<String,String> updatewithimage(String uid,String altemail,String linkedin,String github,String phno,String bio,String foe, MultipartFile image) throws IOException
	{	
		HashMap<String,String> updatemap = new HashMap<String,String>();
		UserDetails optionalUserDetails = userDetailsRepo.GetDetailsById(uid);
		    //System.out.println(image);
			optionalUserDetails.setAltEmail(altemail);
			optionalUserDetails.setLinkedin(linkedin);
			optionalUserDetails.setGithub(github);
			optionalUserDetails.setPhno(phno);
			optionalUserDetails.setBio(bio);
			optionalUserDetails.setFoe(foe);
			byte[] profileimage=  Base64.getEncoder().encode(image.getBytes());
			String s=new String(profileimage);
			optionalUserDetails.setImage(s);
			try
			{
				userDetailsRepo.save(optionalUserDetails);	
			
				updatemap.put("msg", "profile update successfully");
				updatemap.put("imagurl", s);
				return updatemap;

			}
			catch(Exception e)
			{
				e.printStackTrace();
				updatemap.put("msg", "profile update failed");
				return updatemap;
			}
			
		}
	
	//update without image
	public HashMap<String,String> updatewithoutimage(String uid,String altemail,String linkedin,String github,String phno,String bio,String foe)
	{
		HashMap<String,String> updatemap = new HashMap<String,String>();
		UserDetails optionalUserDetails = userDetailsRepo.GetDetailsById(uid);
		optionalUserDetails.setAltEmail(altemail);
		optionalUserDetails.setLinkedin(linkedin);
		optionalUserDetails.setGithub(github);
		optionalUserDetails.setPhno(phno);
		optionalUserDetails.setBio(bio);
		optionalUserDetails.setFoe(foe);
		String s= leaderBoardRepo.GetImageUrlByUid(uid);
			optionalUserDetails.setImage(s);
			try
			{
				userDetailsRepo.save(optionalUserDetails);	
			
				updatemap.put("msg", "profile update successfully");
				updatemap.put("imagurl", s);
				return updatemap;

			}
			catch(Exception e)
			{
				e.printStackTrace();
				updatemap.put("msg", "profile update failed");
				return updatemap;
			}
		}
	
	
	
	
	
	
	//check for admin
	public boolean getRoleByEmail(String email)
	{
		String role = userDetailsRepo.GetRoleByEmail(email);
		if(role.equals("Admin"))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
		
		
		
	}	
	

