package com.api.preclaimapi.Controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Base64;
import java.util.Base64.Encoder;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.logging.log4j.core.util.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.api.preclaimapi.common.Config;
import com.api.preclaimapi.common.CustomMethods;
import com.api.preclaimapi.Dao.adminRepository;
import com.api.preclaimapi.Dao.caseDetailsDao;
import com.api.preclaimapi.models.admin_user;
import com.api.preclaimapi.models.case_details;
import com.api.preclaimapi.models.case_lists;
import com.api.preclaimapi.service.PreClaimService;


@RequestMapping("/preclaimapi")
@RestController
public class MainController {

	@Autowired
	private adminRepository admin;
	
	@Autowired
	private caseDetailsDao caseDetailDao;
	
	@Autowired
	PreClaimService pre;
	
	@PostMapping("/login")
	@Transactional
	public admin_user Login(HttpServletRequest request) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		Encoder encoder = Base64.getEncoder();
		String encodedPassword = encoder.encodeToString(password.getBytes());
		System.out.println("Username: " + username);
		System.out.println("Password: " + encodedPassword);
		admin_user user = admin.findByUsernameAndPassword(username, encodedPassword);
		return user;	
	}
	
	@PostMapping("/forgotPassword")
	public String forgotPassword(HttpServletRequest request) 	
	{
		String username = request.getParameter("username");
		System.out.println(username);
		admin_user user = pre.getbyusername(username);
		
		if(user!= null) 
		{
			String pass= RandomStringUtils.random(6, true, true);
			Encoder encoder = Base64.getEncoder();
			String encodedPassword = encoder.encodeToString(pass.getBytes());
			System.out.println(user.getUser_email());
			String message = pre.Sendmail(user,encodedPassword);	
			return message;
		}	
		else
			
			return "Username not found";	
		}
	@PostMapping("/addCaseDetails")
	@Transactional
	public HashMap<String,String> addCaseDetails(HttpServletRequest request) {
		HashMap<String,String> error_log = new HashMap<String, String>();
		
		//Input Parameters
		int caseId = Integer.parseInt(request.getParameter("caseId"));
		String description = request.getParameter("description");
		String longitude = request.getParameter("longitude");
		String latitude = request.getParameter("latitude");
		String capturedDate = request.getParameter("capturedDate");
		String username = request.getParameter("username");
		int addCount = caseDetailDao.addcaseDetails(caseId, description, longitude, latitude, 
				capturedDate, username);
		if(addCount >= 0)
		{
			error_log.put("error_code", "****");	
			error_log.put("error_description", "Cases Details submitted successfully");
		}
		else
		{
			error_log.put("error_code", "failed");	
			error_log.put("error_description", "Error adding case. Kindly contact system administrator");
		}
		return error_log;	
	}
	
	@PostMapping("/updateCaseDetails")
	@Transactional
	public HashMap<String,String> updateCaseDetails(HttpServletRequest request) {
		HashMap<String,String> error_log = new HashMap<String, String>();
		
		//Input Parameters
		int caseId = Integer.parseInt(request.getParameter("caseId"));
		String description = request.getParameter("description");
		String longitude = request.getParameter("longitude");
		String latitude = request.getParameter("latitude");
		String capturedDate = request.getParameter("capturedDate");
		String username = request.getParameter("username");
		int addCount = caseDetailDao.addcaseDetails(caseId, description, longitude, latitude, 
				capturedDate, username);
		if(addCount >= 0)
		{
			error_log.put("error_code", "****");	
			error_log.put("error_description", "Cases Details submitted successfully");
		}
		else
		{
			error_log.put("error_code", "failed");	
			error_log.put("error_description", "Error adding case. Kindly contact system administrator");
		}
		return error_log;	
	}
	
	@PostMapping("/changePassword")
	@Transactional
	public HashMap<String,String> changePassword(HttpServletRequest request) 
	{
		HashMap<String,String> error_log = new HashMap<String, String>();
		
		//Input Parameters
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String newPassword = request.getParameter("newPassword");
		Encoder encoder = Base64.getEncoder();
		String encodedPassword = encoder.encodeToString(newPassword.getBytes());
		String oldPassword = encoder.encodeToString(password.getBytes());
		int updateCount = admin.changePassword(encodedPassword, username, oldPassword);
		if(updateCount > 0)
		{
			error_log.put("error_code", "****");	
			error_log.put("error_description", "Password changed successfully");
		}
		else
		{
			error_log.put("error_code", "failed");
			error_log.put("error_description", "Invalid Credentials");
		}
		return error_log;	
	}
	
	@PostMapping("/getCaseDetailsByCaseId")
	public case_details getCaseDetailsByCaseId(HttpServletRequest request)
	{
		int caseId = Integer.parseInt(request.getParameter("caseId"));
		return caseDetailDao.getCaseDetailsByCaseId(caseId);
	}
	@PostMapping("/getCaseDetailsByUsername")
	public case_details getCaseDetailsByUsername(HttpServletRequest request)
	{
		String username = request.getParameter("username");
		return caseDetailDao.getCaseDetailsByUsername(username);
	}
	@PostMapping("/getCaseListByUsername")
	public case_lists getCaseListByUsername(HttpServletRequest request)
	{
		String username = request.getParameter("username");
		return caseDetailDao.getCaseListByUsername(username);
	}
	
	@PostMapping("/uploadFile")
	public HashMap<String, String> uploadFile(@RequestParam("uploadedFile") MultipartFile uploadedFile, 
			HttpServletRequest request)
	{
		HashMap<String,String> error_log = new HashMap<String, String>();
		//Input Parameters
		try
		{
			String username = request.getParameter("username");
			String fileType = request.getParameter("uploadType").toLowerCase();
			String longitude = request.getParameter("longitude");
			String latitude = request.getParameter("latitude");
			int caseId = Integer.parseInt(request.getParameter("caseId"));
			//Input Parameters Validation
			if(username == null)
			{
				error_log.put("error_code", "failed");
				error_log.put("error_description", "Username not entered");
				return error_log;
			}
			if(longitude == null || latitude == null)
			{
				error_log.put("error_code", "failed");
				error_log.put("error_description", "Geo-tagging missing");
				return error_log;
			}
			if(fileType == null)
			{
				error_log.put("error_code", "failed");
				error_log.put("error_description", "File Type not entered");
				return error_log;
			}
			List<String> uploadType = CustomMethods.getUploadType();
			if(!uploadType.contains(fileType))
			{
				error_log.put("error_code", "failed");
				error_log.put("error_description", "Invalid File Type");
				return error_log;
			}
			if (!uploadedFile.isEmpty()) 
			{
				byte[] bytes = uploadedFile.getBytes();
				String originalFilename = uploadedFile.getOriginalFilename();
				String filename = username + "_" + fileType + "_" + 
						FileUtils.getFileExtension(new File(originalFilename));
				File serverFile = new File(Config.uploadDirectory + filename);
				BufferedOutputStream stream = new BufferedOutputStream(
						new FileOutputStream(serverFile));
				stream.write(bytes);
				stream.close();
				//Database Logic
				String fileURL = Config.uploadURL + filename;
				switch(fileType)
				{
					case "pdf1":
					{
						caseDetailDao.addPDF1(fileURL, username, caseId);
						break;
					}
					case "pdf2":
					{
						caseDetailDao.addPDF2(fileURL, username, caseId);
						break;
					}
					case "pdf3":
					{
						caseDetailDao.addPDF3(fileURL, username, caseId);
						break;
					}
					case "audio":
					{
						caseDetailDao.addAudio(fileURL, username, caseId);
						break;
					}
					case "video":
					{
						caseDetailDao.addVideo(fileURL, username, caseId);
						break;
					}
					case "signature":
					{
						caseDetailDao.addSign(fileURL, username, caseId);
						break;
					}
				}
				
				error_log.put("error_code", "****");
				error_log.put("error_description", "File uploaded Successfully");
				return error_log;
			} 
			else 
			{
				error_log.put("error_code", "failed");
				error_log.put("error_description", "No file uploaded");
				return error_log;
			}
		}
		catch(Exception e)
		{
			error_log.put("error_code", "failed");
			error_log.put("error_description", e.getMessage());
			return error_log;
		}
	}

	
}
