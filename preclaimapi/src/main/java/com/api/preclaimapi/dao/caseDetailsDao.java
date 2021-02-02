package com.api.preclaimapi.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.api.preclaimapi.models.case_detail;

@Repository
public interface caseDetailsDao extends JpaRepository<case_detail, Integer> {

	  @Modifying
	  @Query("INSERT INTO case_detail(caseId, policyNumber, longitude, latitude, description, createdBy, createdDate, updatedDate, updatedBy) "
	  		+ "VALUES(:caseId, :policyNumber, :longitude, :latitude, :createdBy, now(), '0000-00-00 00:00:00', '')")
      public int addycaseDetail(@Param("caseId") int caseId, @Param("policyNumber") String policyNumber, @Param("description") String description ,@Param("latitude") String latitude, @Param("longitude") String longitude);
	
}
