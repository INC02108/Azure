package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.UserDetails;

import java.util.List;

@Repository
public interface UserDetailsRepository extends JpaRepository<UserDetails,Integer>{
	
	@Query(value = "SELECT * from all_about_user WHERE uid=:uid",nativeQuery = true)
	UserDetails GetDetailsById(@Param("uid") String uid);
	
	@Query(value = "SELECT * from all_about_user WHERE email=:email",nativeQuery = true)
	UserDetails GetDetailsByEmail(@Param("email") String email);
	
	@Query(value="SELECT name from all_about_user WHERE uid=:uid",nativeQuery = true)
	String getNameByUid(@Param("uid") String uid);
	
	@Query(value="SELECT email from all_about_user WHERE uid=:uid",nativeQuery = true)
	String getEmailByUid(@Param("uid") String uid);
	
	@Query(value="SELECT uid from all_about_user WHERE email=:email",nativeQuery = true)
	String getUidByEmail(@Param("email") String email);

	
	@Query(value="SELECT image from all_about_user WHERE email=:email",nativeQuery = true)
	String GetImageUrlByEmail(@Param("email") String email);
	
	@Query(value="SELECT role from all_about_user WHERE email=:email",nativeQuery = true)
	String GetRoleByEmail(@Param("email") String email);


	@Query(value = "SELECT * from all_about_user WHERE name=:name",nativeQuery = true)
	UserDetails findByName(@Param("name") String name);

//	//like operator
	@Query(value = "SELECT * FROM all_about_user WHERE name LIKE :name% OR role LIKE :name%" ,nativeQuery = true )
	List<UserDetails> findByNameLike(@Param("name") String name);


	//to get name and position
	@Query(value="SELECT position from all_about_user WHERE uid=:uid",nativeQuery = true)
	String getPositionByUid(@Param("uid") String uid);

	//to get role by uid
	@Query(value="SELECT role from all_about_user WHERE uid=:uid",nativeQuery = true)
	String getRoleByUid(@Param("uid") String uid);


	//user with point
	@Query(value = "SELECT all_about_user.name,leader_board.points FROM all_about_user INNER JOIN leader_board ON all_about_user.uid=leader_board.uid",nativeQuery = true)
	UserDetails userWithPoints();

	//search by name filter
	public List<UserDetails> findByNameContaining(String name);

	//like operator
	//List<UserDetails> findByNameLike(String name);


}
