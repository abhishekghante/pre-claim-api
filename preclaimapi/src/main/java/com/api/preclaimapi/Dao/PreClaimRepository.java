package com.api.preclaimapi.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.preclaimapi.models.admin_user;

@Repository
public interface PreClaimRepository extends JpaRepository<admin_user, Integer>{
	
	admin_user findByUsername(String username);

}
