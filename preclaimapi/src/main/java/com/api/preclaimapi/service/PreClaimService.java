package com.api.preclaimapi.service;

import java.util.List;

import javax.mail.internet.MimeMessage;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.api.preclaimapi.Dao.PreClaimRepository;
import com.api.preclaimapi.models.admin_user;

@Service
public class PreClaimService {
	
	@Autowired
	public  JavaMailSender mailSender;
	
	@Autowired
	PreClaimRepository repo;


	public String Sendmail(admin_user user ,String pass)
	{
		user.setPassword(pass);
		repo.save(user);
		String toAddress = "noxid9394@gmail.com";
	    String fromAddress = "claims@xangarsinfra.com";
	    String senderName = "Your company name";
	    String subject = "You temp password ";
	    String content = "Your temp password is<h3> [[name]]</h3>Kindly set your password,<br>"
	            + "Thank you,<br>"
	            + "Your company name.";
	     
	    MimeMessage message = mailSender.createMimeMessage();
	    MimeMessageHelper helper = new MimeMessageHelper(message);
	     
	    try 
	    {
		    helper.setFrom(fromAddress, senderName);
		    helper.setTo(toAddress);
		    helper.setSubject(subject);
		    content = content.replace("[[name]]", RandomStringUtils.random(6, true, true));
		    helper.setText(content, true); 
		    mailSender.send(message);
		    return "****";
	    }
	    catch(Exception e)
	    {
	    	return e.getMessage();
	    }
	}

	public List<admin_user> getusers()
	{	
		return repo.findAll();	
	}
	
	public admin_user getbyusername(String username)
	{	
		return repo.findByUsername(username);	
	}
	
}
