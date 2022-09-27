package com.example.demo.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.FileDetails;
import com.example.demo.entity.Files;
import com.example.demo.entity.SocialActions;
import com.example.demo.repository.FileDetailsRepository;
import com.example.demo.repository.FilesRepository;
import com.example.demo.repository.SocialActionsRepository;
import com.example.demo.repository.UserDetailsRepository;

@Service
public class SingleCardDetailsService {
	
	@Autowired
	private FilesRepository fileRepo;
	@Autowired
	private FileDetailsRepository fileDetailsRepo;
	@Autowired
	private SocialActionsRepository socialRepo;
	@Autowired
	private UserDetailsRepository userRepo;
	
	
	
	public ArrayList<HashMap<String,String>> searchArtifacts(String query,int number)
	{
		List<Files> models = fileRepo.searchModel(query,8*(number-1));
		ArrayList<HashMap<String,String>> val = new ArrayList<HashMap<String,String>>();
		for(int i=0;i<models.size();i++)
		{
			HashMap<String,String> det = new HashMap<String,String>();
		
				int component_id = models.get(i).getCid();
			
				Files curr_obj = models.get(i);
				det.put("component_id", Integer.toString(component_id));
				String component_name = curr_obj.getComponent();
				det.put("component_name", component_name);
				String username = curr_obj.getName();
				det.put("username", username);
				String emailId = curr_obj.getEmail();
				det.put("userEmail", emailId);
				String imageurl = userRepo.GetImageUrlByEmail(emailId);
				det.put("imageUrl", imageurl);
				String date = curr_obj.getDate();
				det.put("date", date);
//				String time = java.time.LocalTime.now().toString();
//				det.put("time", time);
				String tags = curr_obj.getTag();
				det.put("tags", tags);
				String description = curr_obj.getDescription();
				det.put("description", description);
			
				String ndownload = socialRepo.CountSocialActions(component_id,"Download").toString();
			
				det.put("ndownloads", ndownload);
				String nlikes = socialRepo.CountSocialActions(component_id,"Like").toString();
				det.put("nlikes", nlikes);
				String nshares = socialRepo.CountSocialActions(component_id,"Share").toString();
				det.put("nshares", nshares);
				String ncomments = socialRepo.CountSocialActions(component_id,"Comment").toString();
				det.put("ncomments", ncomments);
			
				val.add(det);
			
		}
		return val;
		
		
	}
		
	
	//For Grid View 
	
	public ArrayList<HashMap<String,String>> latest(int number)
	{
		ArrayList<Files> models = fileRepo.Latest(8*(number-1));
		//ArrayList<Files> models = new ArrayList<Files>(model.subList(4*(number-1));
		//List<Files> model = fileRepo.findAll();
		//ArrayList<Files> models = new ArrayList<Files>(model.subList(4*(number-1),(4*number)));
		ArrayList<HashMap<String,String>> val = new ArrayList<HashMap<String,String>>();
		for(int i=0;i<models.size();i++)
		{
			HashMap<String,String> det = new HashMap<String,String>();
		
				int component_id = models.get(i).getCid();
			
				Files curr_obj = models.get(i);
				
				if(curr_obj == null) {
					continue;
				}
				
				det.put("component_id", Integer.toString(component_id));
				String component_name = curr_obj.getComponent();
				det.put("component_name", component_name);
				String username = curr_obj.getName();
				det.put("username", username);
				String date = curr_obj.getDate();
				String emailId = curr_obj.getEmail();
				det.put("userEmail", emailId);
				String imageurl = userRepo.GetImageUrlByEmail(emailId);
				det.put("imageUrl", imageurl);
				det.put("date", date);
//				String time = java.time.LocalTime.now().toString();
//				det.put("time", time);
				String tags = curr_obj.getTag();
				det.put("tags", tags);
				String description = curr_obj.getDescription();
				det.put("description", description);
			
				String ndownload = socialRepo.CountSocialActions(component_id,"Download").toString();
			
				det.put("ndownloads", ndownload);
				String nlikes = socialRepo.CountSocialActions(component_id,"Like").toString();
				det.put("nlikes", nlikes);
				String nshares = socialRepo.CountSocialActions(component_id,"Share").toString();
				det.put("nshares", nshares);
				String ncomments = socialRepo.CountSocialActions(component_id,"Comment").toString();
				det.put("ncomments", ncomments);
			
				val.add(det);
			
		}
		return val;
		
		
	}
	
	public ArrayList<HashMap<String,String>> most_downloaded(int number)
	{
		ArrayList<Integer> model_ids = socialRepo.findMostDownloaded(8*(number-1));
		//ArrayList<Integer> model_ids = new ArrayList<Integer>(models.subList(4*(number-1),4*(number+1)));
		ArrayList<HashMap<String,String>> val = new ArrayList<HashMap<String,String>>();
		for(int i=0;i<model_ids.size();i++)
		{
			HashMap<String,String> det = new HashMap<String,String>();
		
				int component_id = model_ids.get(i);
				Files curr_obj = fileRepo.findById(component_id).orElse(null);
				
				if(curr_obj == null) {
					continue;
				}
				
				det.put("component_id", Integer.toString(component_id));
				String component_name = curr_obj.getComponent();
				det.put("component_name", component_name);
				String username = curr_obj.getName();
				det.put("username", username);
				String emailId = curr_obj.getEmail();
				det.put("userEmail", emailId);
				String imageurl = userRepo.GetImageUrlByEmail(emailId);
				det.put("imageUrl", imageurl);
				String date = curr_obj.getDate();
				det.put("date", date);
//				String time = java.time.LocalTime.now().toString();
//				det.put("time", time);
				String tags = curr_obj.getTag();
				det.put("tags", tags);
				String description = curr_obj.getDescription();
				det.put("description", description);
			
				String ndownload = socialRepo.CountSocialActions(component_id,"Download").toString();
			
				det.put("ndownloads", ndownload);
				String nlikes = socialRepo.CountSocialActions(component_id,"Like").toString();
				det.put("nlikes", nlikes);
				String nshares = socialRepo.CountSocialActions(component_id,"Share").toString();
				det.put("nshares", nshares);
				String ncomments = socialRepo.CountSocialActions(component_id,"Comment").toString();
				det.put("ncomments", ncomments);
			
				val.add(det);
			
		}
		return val;
		
		
	}

	
	public ArrayList<HashMap<String,String>> most_liked(int number)
	{
		ArrayList<Integer> model_ids = socialRepo.findMostLiked(8*(number-1));
		//ArrayList<Integer> model_ids = new ArrayList<Integer>(models.subList(4*(number-1),4*(number+1)));
		ArrayList<HashMap<String,String>> val = new ArrayList<HashMap<String,String>>();
		for(int i=0;i<model_ids.size();i++)
		{
			HashMap<String,String> det = new HashMap<String,String>();
		
				int component_id = model_ids.get(i);		
				Files curr_obj = fileRepo.findById(component_id).orElse(null);
				
				if(curr_obj == null) {
					continue;
				}
				
				det.put("component_id", Integer.toString(component_id));
				String component_name = curr_obj.getComponent();
				det.put("component_name", component_name);
				String username = curr_obj.getName();
				det.put("username", username);
				String emailId = curr_obj.getEmail();
				det.put("userEmail", emailId);
				String imageurl = userRepo.GetImageUrlByEmail(emailId);
				det.put("imageUrl", imageurl);
				String date = curr_obj.getDate();
				det.put("date", date);
//				String time = java.time.LocalTime.now().toString();
//				det.put("time", time);
				String tags = curr_obj.getTag();
				det.put("tags", tags);
				String description = curr_obj.getDescription();
				det.put("description", description);
			
				String ndownload = socialRepo.CountSocialActions(component_id,"Download").toString();
			
				det.put("ndownloads", ndownload);
				String nlikes = socialRepo.CountSocialActions(component_id,"Like").toString();
				det.put("nlikes", nlikes);
				String nshares = socialRepo.CountSocialActions(component_id,"Share").toString();
				det.put("nshares", nshares);
				String ncomments = socialRepo.CountSocialActions(component_id,"Comment").toString();
				det.put("ncomments", ncomments);
			
				val.add(det);
			
		}
		return val;
		
		
	}
	
	
	//all details by id
	public HashMap<String,String> details(int cid)
	{
		HashMap<String,String> details = new HashMap<String,String>();
		try
		{
			details.put("component_id",Integer.toString(cid));
		
			Files curr_obj = fileRepo.findById(cid).orElse(null);
			FileDetails obj_details = fileDetailsRepo.findByCid(cid);
		
			String component_name = curr_obj.getComponent();
			details.put("component_name", component_name);
			String username = curr_obj.getName();
			details.put("username", username);
			String emailId = curr_obj.getEmail();
			details.put("userEmail", emailId);
			String imageurl = userRepo.GetImageUrlByEmail(emailId);
			details.put("imageUrl", imageurl);
			String date = curr_obj.getDate();
			details.put("date", date);
//			String time = java.time.LocalTime.now().toString();
//			details.put("time", time);
			String tags = curr_obj.getTag();
			details.put("tags", tags);
			String description = curr_obj.getDescription();
			details.put("description", description);
		
			String ndownload = socialRepo.CountSocialActions(cid,"Download").toString();
		
			details.put("ndownloads", ndownload);
			String nlikes = socialRepo.CountSocialActions(cid,"Like").toString();
			details.put("nlikes", nlikes);
			String nshares = socialRepo.CountSocialActions(cid,"Share").toString();
			details.put("nshares", nshares);
			String ncomments = socialRepo.CountSocialActions(cid,"Comment").toString();
			details.put("ncomments", ncomments);
//			
//			String pom = obj_details.getPom();
//			details.put("pom", pom);
//			
			String jarname = obj_details.getJarname();
			details.put("jarname", jarname);
			String srcname = obj_details.getSrcname();
			details.put("srcname",srcname);
			String docname = obj_details.getDocname();
			details.put("docname",docname);

		
		
			return details;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			details.put("status", "0");
			return details;
		}
		
		
		
		
	}
	
	
	public ArrayList<HashMap<String,String>> value(String email)
	{
		List<Files> models = fileRepo.findByEmail(email);
		ArrayList<HashMap<String,String>> val = new ArrayList<HashMap<String,String>>();
		for(int i=0;i<models.size();i++)
		{
			HashMap<String,String> det = new HashMap<String,String>();
		
				int component_id = models.get(i).getCid();
			
				Files curr_obj = models.get(i);
				det.put("component_id", Integer.toString(component_id));
				String component_name = curr_obj.getComponent();
				det.put("component_name", component_name);
				String username = curr_obj.getName();
				det.put("username", username);
				String emailId = curr_obj.getEmail();
				det.put("userEmail", emailId);
				String imageurl = userRepo.GetImageUrlByEmail(emailId);
				det.put("imageUrl", imageurl);
				String date = curr_obj.getDate();
				det.put("date", date);
//				String time = java.time.LocalTime.now().toString();
//				det.put("time", time);
				String tags = curr_obj.getTag();
				det.put("tags", tags);
				String description = curr_obj.getDescription();
				det.put("description", description);
			
				String ndownload = socialRepo.CountSocialActions(component_id,"Download").toString();
			
				det.put("ndownloads", ndownload);
				String nlikes = socialRepo.CountSocialActions(component_id,"Like").toString();
				det.put("nlikes", nlikes);
				String nshares = socialRepo.CountSocialActions(component_id,"Share").toString();
				det.put("nshares", nshares);
				String ncomments = socialRepo.CountSocialActions(component_id,"Comment").toString();
				det.put("ncomments", ncomments);
			
				val.add(det);
			
		}
		return val;
		
		
	}
	
	//get comments under a post
	public ArrayList<HashMap<String,String>> comments(int cid)
	{
		ArrayList<HashMap<String,String>> detailsOfComment = new ArrayList<HashMap<String,String>>();
		List<SocialActions> socialmodel = socialRepo.getAllFromSocialTableForComments(cid);
		for(int i=0;i<socialmodel.size();i++)
		{
			HashMap<String,String> map = new HashMap<String,String>();
			map.put("name", userRepo.getNameByUid(socialmodel.get(i).getUid()));
			map.put("email",userRepo.getEmailByUid(socialmodel.get(i).getUid()));
			map.put("comment", socialmodel.get(i).getComment());
			map.put("date", socialmodel.get(i).getDate());
			map.put("comment_id", Integer.toString(socialmodel.get(i).getId()));
			detailsOfComment.add(map);
			
			
		}
		return detailsOfComment;
	}
	
	
	
	//Service for my Downlods
	public ArrayList<HashMap<String,String>> myDownloads(String email)
	{
		HashSet<Integer> listofcid = socialRepo.getUniqueCid(userRepo.getUidByEmail(email),"Download");
		ArrayList<Integer> cidList = new ArrayList<>(listofcid);
		//System.out.println(cidList.size());
		ArrayList<HashMap<String,String>> val = new ArrayList<HashMap<String,String>>();
		for(int i=0;i<cidList.size();i++)
		{
				int component_id = cidList.get(i);
				//System.out.println(component_id);
				List<Files> models = fileRepo.findFilesByCid(component_id);
				for(int j=0;j<models.size();j++)
				{
					HashMap<String,String> det = new HashMap<String,String>();
					Files curr_obj = models.get(j);
					det.put("component_id", Integer.toString(component_id));
					String component_name = curr_obj.getComponent();
					det.put("component_name", component_name);
					String username = curr_obj.getName();
					det.put("username", username);
					String emailId = curr_obj.getEmail();
					det.put("userEmail", emailId);
					String imageurl = userRepo.GetImageUrlByEmail(emailId);
					det.put("imageUrl", imageurl);
					String date = curr_obj.getDate();
					det.put("date", date);
//					String time = java.time.LocalTime.now().toString();
//					det.put("time", time);
					String tags = curr_obj.getTag();
					det.put("tags", tags);
					String description = curr_obj.getDescription();
					det.put("description", description);
					String ndownload = socialRepo.CountSocialActions(component_id,"Download").toString();
				
					det.put("ndownloads", ndownload);
					String nlikes = socialRepo.CountSocialActions(component_id,"Like").toString();
					det.put("nlikes", nlikes);
					String nshares = socialRepo.CountSocialActions(component_id,"Share").toString();
					det.put("nshares", nshares);
					String ncomments = socialRepo.CountSocialActions(component_id,"Comment").toString();
					det.put("ncomments", ncomments);
				
					val.add(det);
					//System.out.println(det);
				}
			
		}
		return val;
	}
	
	
	
	
	//For List View 
	
		public ArrayList<HashMap<String,String>> latestList(int number)
		{
			ArrayList<Files> models = fileRepo.LatestGrid(15*(number-1));
			//ArrayList<Files> models = new ArrayList<Files>(model.subList(4*(number-1));
			//List<Files> model = fileRepo.findAll();
			//ArrayList<Files> models = new ArrayList<Files>(model.subList(4*(number-1),(4*number)));
			ArrayList<HashMap<String,String>> val = new ArrayList<HashMap<String,String>>();
			for(int i=0;i<models.size();i++)
			{
				HashMap<String,String> det = new HashMap<String,String>();
			
					int component_id = models.get(i).getCid();
				
					Files curr_obj = models.get(i);
					
					if(curr_obj == null) {
						continue;
					}
					
					det.put("component_id", Integer.toString(component_id));
					String component_name = curr_obj.getComponent();
					det.put("component_name", component_name);
					String username = curr_obj.getName();
					det.put("username", username);
					String date = curr_obj.getDate();
					String emailId = curr_obj.getEmail();
					det.put("userEmail", emailId);
					String imageurl = userRepo.GetImageUrlByEmail(emailId);
					det.put("imageUrl", imageurl);
					det.put("date", date);
//					String time = java.time.LocalTime.now().toString();
//					det.put("time", time);
					String tags = curr_obj.getTag();
					det.put("tags", tags);
					String description = curr_obj.getDescription();
					det.put("description", description);
				
					String ndownload = socialRepo.CountSocialActions(component_id,"Download").toString();
				
					det.put("ndownloads", ndownload);
					String nlikes = socialRepo.CountSocialActions(component_id,"Like").toString();
					det.put("nlikes", nlikes);
					String nshares = socialRepo.CountSocialActions(component_id,"Share").toString();
					det.put("nshares", nshares);
					String ncomments = socialRepo.CountSocialActions(component_id,"Comment").toString();
					det.put("ncomments", ncomments);
				
					val.add(det);
				
			}
			return val;
			
			
		}
		
		public ArrayList<HashMap<String,String>> most_downloaded_list(int number)
		{
			ArrayList<Integer> model_ids = socialRepo.findMostDownloadedListView(15*(number-1));
			//ArrayList<Integer> model_ids = new ArrayList<Integer>(models.subList(4*(number-1),4*(number+1)));
			ArrayList<HashMap<String,String>> val = new ArrayList<HashMap<String,String>>();
			for(int i=0;i<model_ids.size();i++)
			{
				HashMap<String,String> det = new HashMap<String,String>();
			
					int component_id = model_ids.get(i);
					Files curr_obj = fileRepo.findById(component_id).orElse(null);
					
					if(curr_obj == null) {
						continue;
					}
					
					det.put("component_id", Integer.toString(component_id));
					String component_name = curr_obj.getComponent();
					det.put("component_name", component_name);
					String username = curr_obj.getName();
					det.put("username", username);
					String emailId = curr_obj.getEmail();
					det.put("userEmail", emailId);
					String imageurl = userRepo.GetImageUrlByEmail(emailId);
					det.put("imageUrl", imageurl);
					String date = curr_obj.getDate();
					det.put("date", date);
//					String time = java.time.LocalTime.now().toString();
//					det.put("time", time);
					String tags = curr_obj.getTag();
					det.put("tags", tags);
					String description = curr_obj.getDescription();
					det.put("description", description);
				
					String ndownload = socialRepo.CountSocialActions(component_id,"Download").toString();
				
					det.put("ndownloads", ndownload);
					String nlikes = socialRepo.CountSocialActions(component_id,"Like").toString();
					det.put("nlikes", nlikes);
					String nshares = socialRepo.CountSocialActions(component_id,"Share").toString();
					det.put("nshares", nshares);
					String ncomments = socialRepo.CountSocialActions(component_id,"Comment").toString();
					det.put("ncomments", ncomments);
				
					val.add(det);
				
			}
			return val;
			
			
		}

		
		public ArrayList<HashMap<String,String>> most_liked_list(int number)
		{
			ArrayList<Integer> model_ids = socialRepo.findMostLikedListview(15*(number-1));
			//ArrayList<Integer> model_ids = new ArrayList<Integer>(models.subList(4*(number-1),4*(number+1)));
			ArrayList<HashMap<String,String>> val = new ArrayList<HashMap<String,String>>();
			for(int i=0;i<model_ids.size();i++)
			{
				HashMap<String,String> det = new HashMap<String,String>();
			
					int component_id = model_ids.get(i);		
					Files curr_obj = fileRepo.findById(component_id).orElse(null);
					
					if(curr_obj == null) {
						continue;
					}
					
					det.put("component_id", Integer.toString(component_id));
					String component_name = curr_obj.getComponent();
					det.put("component_name", component_name);
					String username = curr_obj.getName();
					det.put("username", username);
					String emailId = curr_obj.getEmail();
					det.put("userEmail", emailId);
					String imageurl = userRepo.GetImageUrlByEmail(emailId);
					det.put("imageUrl", imageurl);
					String date = curr_obj.getDate();
					det.put("date", date);
//					String time = java.time.LocalTime.now().toString();
//					det.put("time", time);
					String tags = curr_obj.getTag();
					det.put("tags", tags);
					String description = curr_obj.getDescription();
					det.put("description", description);
				
					String ndownload = socialRepo.CountSocialActions(component_id,"Download").toString();
				
					det.put("ndownloads", ndownload);
					String nlikes = socialRepo.CountSocialActions(component_id,"Like").toString();
					det.put("nlikes", nlikes);
					String nshares = socialRepo.CountSocialActions(component_id,"Share").toString();
					det.put("nshares", nshares);
					String ncomments = socialRepo.CountSocialActions(component_id,"Comment").toString();
					det.put("ncomments", ncomments);
				
					val.add(det);
				
			}
			return val;
			
			
		}
		
}
