package com.api.preclaimapi.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.api.preclaimapi.models.case_details;
import com.api.preclaimapi.models.case_lists;

@Repository
public interface caseDetailsDao extends JpaRepository<case_details, Integer> {

	  @Modifying
	  @Query(value = "INSERT INTO case_details(caseId, description, longitude, latitude, capturedDate, "
	  		+ "createdBy, created_on, updatedBy, updated_on) "
	  		+ "VALUES ( :caseId, :description, :longitude, :latitude, :capturedDate, :createdBy, now(), "
	  		+ "'', '0000-00-00 00:00:00')", nativeQuery = true)
      public int addcaseDetails(@Param("caseId") Integer caseId, @Param("description") String description ,
    		  @Param("longitude") String longitude, @Param("latitude") String latitude, 
    		  @Param("capturedDate") String capturedDate, @Param("createdBy") String username);
	  
	  
	  //Updating All Docs
	  
	  @Modifying
	  @Query("UPDATE case_docs SET pdf1 = :pdf1, updated_on = now(), updatedBy = :updatedBy where caseId = :caseId")
	  public void addPDF1(@Param("pdf1") String pdf1, @Param("updatedBy") String updatedBy, @Param("caseId") Integer caseId);
	  
	  @Modifying
	  @Query("UPDATE case_docs SET pdf2 = :pdf2, updated_on = now(), updatedBy = :updatedBy  where caseId = :caseId")
	  public void addPDF2(@Param("pdf2") String pdf2, @Param("updatedBy") String updatedBy, @Param("caseId") Integer caseId);
	  
	  @Modifying
	  @Query("UPDATE case_docs SET pdf3 = :pdf3, updated_on = now(), updatedBy = :updatedBy  where caseId = :caseId")
	  public void addPDF3(@Param("pdf3") String pdf3, @Param("updatedBy") String updatedBy, @Param("caseId") Integer caseId);
	  
	  @Modifying
	  @Query("UPDATE case_docs SET audio = :audio, updated_on = now(), updatedBy = :updatedBy  where caseId = :caseId")
	  public void addAudio(@Param("audio") String audio, @Param("updatedBy") String updatedBy, @Param("caseId") Integer caseId);
	  
	  @Modifying
	  @Query("UPDATE case_docs SET video = :video, updated_on = now(), updatedBy = :updatedBy  where caseId = :caseId")
	  public void addVideo(@Param("video") String video, @Param("updatedBy") String updatedBy, @Param("caseId") Integer caseId);
	  
	  @Modifying
	  @Query("UPDATE case_docs SET signature = :signature, updated_on = now(), updatedBy = :updatedBy  where caseId = :caseId")
	  public void addSign(@Param("signature") String signature, @Param("updatedBy") String updatedBy, @Param("caseId") Integer caseId);
	  
	  
	  @Modifying
	  @Query(value = "UPDATE case_details SET description = :description, longitude = :longitude, updatedDate = "
	  		+ "now(), latitude = :latitude, capturedDate = :capturedDate, updatedBy = :username where"
	  		+ " caseId = :caseId", nativeQuery = true)
      public int updatecaseDetails(@Param("caseId") int caseId, @Param("description") String description ,
    		  @Param("longitude") String longitude, @Param("latitude") String latitude, 
    		  @Param("capturedDate") String capturedDate, @Param("username") String username);
	
	  @Query(value = "SELECT a FROM case_details a where caseId = :caseId", nativeQuery = true)
      public case_details getCaseDetailsByCaseId(@Param("caseId") Integer caseId);
	  
	  @Query("SELECT a FROM case_details a where createdBy = :username")
      public case_details getCaseDetailsByUsername(@Param("username") String username);
	  
	  @Query("SELECT a FROM case_lists a where investigator = :username")
      public case_lists getCaseListByUsername(@Param("username") String username);
}
