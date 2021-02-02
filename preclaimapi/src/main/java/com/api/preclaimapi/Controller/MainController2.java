package com.api.preclaimapi.Controller;

import java.util.Base64;
import java.util.Base64.Encoder;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.preclaimapi.dao.adminRepository;
import com.api.preclaimapi.dao.caseDetailsDao;
import com.api.preclaimapi.models.admin_user;
import com.api.preclaimapi.models.case_detail;


@RequestMapping("/preclaimapi")
@RestController
public class MainController2 {

	@Autowired
	private adminRepository admin;
	
	@Autowired
	private caseDetailsDao caseDetailDao;
	
	@PostMapping("/login")
	@Transactional
	public admin_user Login(HttpServletRequest request) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		Encoder encoder = Base64.getEncoder();
		String encodedPassword = encoder.encodeToString(password.getBytes());
		System.out.println("Username: " + username);
		System.out.println("Password: " + encodedPassword);
		admin_user user=admin.findByUsernameAndPassword(username, encodedPassword);
		System.out.println("user"+user);	  
		return user;	
	}
	
	/*
	@PostMapping(path = "/caseDetail", consumes = { "application/json" }, produces = { "application/json" })
	public caseDetails getCaseDetails(@RequestBody Map<String, Object> payload) {

		String username = (String) payload.get("username");
		String description = (String) payload.get("description");
		String longitude = (String) payload.get("longitude");
		String latitude = (String) payload.get("latitude");

		System.out.println("All details"+username + "  " + description+" "+longitude+" "+latitude);

	       caseDetails responseData = caseDetailDao.findBycaseDetail(username, description, latitude, longitude);
		

		System.out.println(responseData);

		if (responseData != null) {

			HashMap<String, Object> response = new HashMap<>();

			response.put("Status", 1005);
			response.put("Data", username);

			return ResponseEntity.ok(response);
		} else {

			return new ResponseEntity<Object>(Collections.singletonMap("error", "Email not validate."),
					HttpStatus.BAD_REQUEST);
		}

	} */
	
	@PostMapping("/casedetail")
	@Transactional
	public String getCaseDetails(HttpServletRequest request) {
		int caseId = Integer.parseInt(request.getParameter("caseId"));
		String policyNumber=request.getParameter("policyNumber");
		String description = request.getParameter("description");
		String longitude = request.getParameter("longitude");
		String latitude = request.getParameter("latitude");
		int detail = caseDetailDao.addycaseDetail(caseId, policyNumber, description, latitude, longitude);
		System.out.println("user"+ detail);	  
		return "Case details added successfully...";	
	}
	
	@PostMapping("/changePassword")
	@Transactional
	public String changePassword(HttpServletRequest request) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String newPassword = request.getParameter("newPassword");
		Encoder encoder = Base64.getEncoder();
		String encodedPassword = encoder.encodeToString(password.getBytes());
		System.out.println("Username: " + username);
		System.out.println("Password: " + encodedPassword);
		admin_user user=admin.findByUsernameAndPassword(username, encodedPassword);
		if(encodedPassword.equals(user.getPassword())) 
		{
			user.setPassword(encoder.encodeToString(newPassword.getBytes()));
			 admin.save(user);
		}
		else {
			
			return "please enter correct password"; 
		     }
		
		
		return "password changed successfully";	
	}
	
	
}
