package com.api.preclaimapi.Controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Base64;
import java.util.Base64.Encoder;
import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.apache.logging.log4j.core.util.FileUtils;
import org.apache.tomcat.util.descriptor.web.JspPropertyGroupDescriptorImpl;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.api.preclaimapi.common.Config;
import com.api.preclaimapi.common.CustomMethods;
import com.api.preclaimapi.models.admin_user;


@RequestMapping("/preclaimapi")
@RestController
public class MainController {

	@Autowired
	private EntityManager entity_manager;
	private Session W0SESSION;
	private Query W0QUERY;
	
	@PostMapping("/login")
	@Transactional
	public List<admin_user> Login(HttpServletRequest request) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		Encoder encoder = Base64.getEncoder();
		String encodedPassword = encoder.encodeToString(password.getBytes());
		System.out.println("Username: " + username);
		System.out.println("Password: " + encodedPassword);
		
		W0SESSION = entity_manager.unwrap(Session.class);
		W0QUERY = W0SESSION.createQuery("from admin_user where username = :W0USER and password = :W0PASSWORD");
		W0QUERY.setParameter("W0USER", username);
		W0QUERY.setParameter("W0PASSWORD", encodedPassword);
		@SuppressWarnings(value = { "unchecked" })
		List<admin_user> W0READ = W0QUERY.getResultList();
		System.out.println(W0READ.toString());
		return W0READ;
	}
	
	@PostMapping("/uploadFile")
	public HashMap<String, String> uploadImage(@RequestParam("uploadedFile") MultipartFile uploadedFile, 
			HttpServletRequest request)
	{
		HashMap<String,String> error_log = new HashMap<String, String>();
		//Input Parameters
		try
		{
			String username = request.getParameter("username");
			String fileType = request.getParameter("uploadType").toLowerCase();
			double longitude = Double.parseDouble(request.getParameter("longitude"));
			double latitude = Double.parseDouble(request.getParameter("latitude"));
			int caseId = Integer.parseInt(request.getParameter("caseId"));
			//Input Parameters Validation
			if(username == null)
			{
				error_log.put("error_code", "failed");
				error_log.put("error_description", "Username not entered");
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
