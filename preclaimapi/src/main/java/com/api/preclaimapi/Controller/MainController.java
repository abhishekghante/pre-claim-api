package com.api.preclaimapi.Controller;

import java.util.Base64;
import java.util.Base64.Encoder;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
