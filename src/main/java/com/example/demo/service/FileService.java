package com.example.demo.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.FileDetails;
import com.example.demo.entity.Files;
import com.example.demo.repository.FileDetailsRepository;
import com.example.demo.repository.FilesRepository;
import com.example.demo.repository.PointsTableRepository;
import com.example.demo.repository.SocialActionsRepository;
import com.example.demo.repository.UserDetailsRepository;

@Service
public class FileService {
	
	@Autowired
	private FilesRepository filesRepo;
	
	@Autowired
	private FileDetailsRepository fileDetailsRepo;
	
	@Autowired
	private LeaderBoardService leaderService;
	
	@Autowired
	private PointsTableRepository pointsRepo;
	
	@Autowired
	private FileDetailsRepository filedetails;
	
	@Autowired
	private SocialActionsRepository socialrepo;
	
	@Autowired
	private UserDetailsRepository userrepo;
	
	//service for upload
	public HashMap<String,String> uploadFiles(String email,String component,String tag,String name,String description,String date,MultipartFile jar,MultipartFile src,MultipartFile doc)
	{
		HashMap<String,String> file = new HashMap<String,String>();
		try
		{
			
			FileDetails fileDetails = new FileDetails(jar.getOriginalFilename(),jar.getContentType(),jar.getBytes(),src.getOriginalFilename(),src.getContentType(),src.getBytes(),doc.getOriginalFilename(),doc.getContentType(),doc.getBytes());
			fileDetailsRepo.save(fileDetails);
			
			Files files = new Files(fileDetails.getCid(),email,component,tag,name,description,date);
			filesRepo.save(files);

			String user_id = fileDetailsRepo.findUidFromEmail(email);
			leaderService.updateLeaderBoard(user_id, pointsRepo.pointsByAction("Upload"));
			
			file.put("status", "1");
			file.put("msg", "upload successful");
			return file;
			
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			file.put("status", "0");
			file.put("msg", "upload failed");
			return file;
		}
	}
	
	//service for upload without jar
	public HashMap<String,String> uploadFilesWithOutJar(String email,String component,String tag,String name,String description,String date,MultipartFile src,MultipartFile doc)
	{
		HashMap<String,String> file = new HashMap<String,String>();
		try
		{
			
			FileDetails fileDetails = new FileDetails(src.getOriginalFilename(),src.getContentType(),src.getBytes(),doc.getOriginalFilename(),doc.getContentType(),doc.getBytes());
			fileDetailsRepo.save(fileDetails);
			
			Files files = new Files(fileDetails.getCid(),email,component,tag,name,description,date);
			filesRepo.save(files);

			String user_id = fileDetailsRepo.findUidFromEmail(email);
			leaderService.updateLeaderBoard(user_id, pointsRepo.pointsByAction("Upload"));
			
			file.put("status", "1");
			file.put("msg", "upload successful");
			return file;
			
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			file.put("status", "0");
			file.put("msg", "upload failed");
			return file;
		}
	}
	
	
	//service for upload without src
	public HashMap<String,String> uploadFilesWithOutSrc(String email,String component,String tag,String name,String description,String date,MultipartFile jar,MultipartFile doc)
	{
		HashMap<String,String> file = new HashMap<String,String>();
		try
		{
			FileDetails fileDetails = new FileDetails(jar.getOriginalFilename(),jar.getContentType(),jar.getBytes(),doc.getOriginalFilename(),doc.getContentType(),doc.getBytes(),true);
			fileDetailsRepo.save(fileDetails);
			
			
			Files files = new Files(fileDetails.getCid(),email,component,tag,name,description,date);
			filesRepo.save(files);

			String user_id = fileDetailsRepo.findUidFromEmail(email);
			leaderService.updateLeaderBoard(user_id, pointsRepo.pointsByAction("Upload"));
			
			file.put("status", "1");
			file.put("msg", "upload successful");
			return file;
			
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			file.put("status", "0");
			file.put("msg", "upload failed");
			return file;
		}
	}
	
	
	
	
	
	
	
	
	//service for search
	public List<Files> searchArtifact(String query)
	{
		return filesRepo.searchFile(query);
			
	}
	
	//service for delete
	public HashMap<String,String> deleteArtefactById(int id)
	{
		int cid = id;
		HashMap<String,String> hash = new HashMap<String,String>();
		try
		{
			int download = socialrepo.CountSocialActions(cid, "Download");
			int share = socialrepo.CountSocialActions(cid, "Share");
			int like = socialrepo.CountSocialActions(cid, "Like");
			int comment = socialrepo.CountSocialActions(cid, "Comment");
			int points = download*pointsRepo.pointsByAction("Download")+like*pointsRepo.pointsByAction("Like")+share*pointsRepo.pointsByAction("Share")+comment*pointsRepo.pointsByAction("Comment")+pointsRepo.pointsByAction("Upload");
			leaderService.updateLeaderBoard(userrepo.getUidByEmail(fileDetailsRepo.GetEmailFromCid(cid)),-(points));
			hash.put("points_update", "1");
		}
		catch(Exception e)
		{
			hash.put("points_update", "0");
		}
		try
		{
			
			filesRepo.deleteById(cid);
			fileDetailsRepo.deleteById(cid);

			hash.put("status", "1");
			//return hash;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			hash.put("status", "0");
			//return hash;
		}

		
		return hash;
	}
	
	//service for find artefact by email
	public List<Files> findFilesByEmailId(String email)
	{
		return filesRepo.findFilesByEmail(email);
	}
	
	//update files
	public HashMap<String, String> updateDetails(Integer id, String component,String tag,String name,String email,String description,String updateDate,String updateBy)
	{
		HashMap<String,String> res = new HashMap<String,String>();
		
		try {
		Files tempModel = filesRepo.findById(id).orElse(null);
		
			
		tempModel.setComponent(component);
		tempModel.setTag(tag);
		tempModel.setName(name);
		tempModel.setEmail(email);
		tempModel.setDescription(description);	
//		tempModel.setDate(updateDate);
		tempModel.setUpdateBy(null);
		tempModel.setUpdateDate(java.time.LocalDate.now().toString());
		filesRepo.save(tempModel);
		res.put("status", "1");
		
		}
		catch(Exception e) {
			res.put("status", "0");
		}
		
		return res;
		
	}	
	
	//get by id
	public FileDetails getByCid(int cid)
	{
		return fileDetailsRepo.findById(cid).orElse(null);
	}
	

}
