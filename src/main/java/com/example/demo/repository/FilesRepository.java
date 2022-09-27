package com.example.demo.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Files;


@Repository
public interface FilesRepository extends JpaRepository<Files,Integer>{
	
	public List<Files> findByEmail(String email);
	
	@Query(value = "SELECT e FROM Files e WHERE e.description LIKE %:searchQuery% OR e.tag LIKE %:searchQuery% OR e.component LIKE %:searchQuery%")
	List<Files> searchFile(@Param("searchQuery") String searchQuery);
	
	@Query(value = "SELECT * FROM files WHERE email=:email",nativeQuery = true)
	List<Files> findFilesByEmail(@Param("email") String email);
	
	@Query(value = "SELECT * FROM Files  WHERE description LIKE %:searchQuery% OR tag LIKE %:searchQuery% OR component LIKE %:searchQuery% OR name LIKE %:searchQuery% LIMIT :n,8",nativeQuery = true)
	List<Files> searchModel(@Param("searchQuery") String searchQuery,@Param("n") int n);
	
	
	@Query(value="SELECT email FROM all_about_user WHERE uid=:uid",nativeQuery = true)
	String GetEmailFromUid(@Param("uid") String uid);
	
	@Query(value="SELECT COUNT(*) FROM files WHERE email=:email",nativeQuery = true)
	Integer findTotalUploadsByUser(@Param("email") String email);
	
	@Query(value="SELECT * FROM files WHERE cid=:cid",nativeQuery = true)
	List<Files> findFilesByCid(@Param("cid") int cid);
	
	//for grid
	@Query(value="SELECT * FROM files ORDER BY cid DESC LIMIT :n,4",nativeQuery = true)
	ArrayList<Files> Latest(@Param("n") int n);
	
	//for list
	@Query(value="SELECT * FROM files ORDER BY cid DESC LIMIT :n,15",nativeQuery = true)
	ArrayList<Files> LatestGrid(@Param("n") int n);

	@Query(value="SELECT COUNT( DISTINCT email) FROM files ",nativeQuery=true)
	String CountTotaluserswhoposted();


}
