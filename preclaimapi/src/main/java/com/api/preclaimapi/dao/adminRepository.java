package com.api.preclaimapi.dao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.api.preclaimapi.models.admin_user;

@Repository
public interface adminRepository extends JpaRepository<admin_user, Integer>{

	  @Query("SELECT u FROM admin_user u WHERE u.username = :username and u.password = :password")
	  public admin_user findByUsernameAndPassword( @Param("username") String username, @Param("password") String password);
	  
	  @Modifying
	  @Query("UPDATE admin_user u SET u.password =:password WHERE u.username = :username")
	  public void changePassword( @Param("password") String newPassword, @Param("username") String username);

}
