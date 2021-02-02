package com.api.preclaimapi.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;;

@Entity
@Table(name = "admin_user")
public class admin_user {
	@Id
	@Column
	private int user_id;
	@Column
	private String full_name;
	@Column
	private String role_name;
	@Column
	private String username;
	@Column
	private String user_email;
	@Column
	private String mobile_number;
	@Column
	private String password;
	@Column
	private String state;
	@Column
	private String zone;
	@Column
	private String city;
	@Column
	private int status;
	@Column
	private String user_image;
	
	public admin_user() {
		this.user_id = 0;
		this.full_name = "";
		this.username = "";
		this.password = "";
		this.user_email = "";
		this.role_name = "";
		this.status = 0;
		this.user_image = "";
		this.mobile_number = "";
		this.state = "";
		this.city = "";
		this.zone = "";
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getFull_name() {
		return full_name;
	}

	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}

	public String getRole_name() {
		return role_name;
	}

	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUser_email() {
		return user_email;
	}

	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}

	public String getMobile_number() {
		return mobile_number;
	}

	public void setMobile_number(String mobile_number) {
		this.mobile_number = mobile_number;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZone() {
		return zone;
	}

	public void setZone(String zone) {
		this.zone = zone;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getUser_image() {
		return user_image;
	}

	public void setUser_image(String user_image) {
		this.user_image = user_image;
	}

	@Override
	public String toString() {
		return "admin_user [user_id=" + user_id + ", full_name=" + full_name + ", role_name=" + role_name
				+ ", username=" + username + ", user_email=" + user_email + ", mobile_number=" + mobile_number
				+ ", password=" + password + ", state=" + state + ", zone=" + zone + ", city=" + city + ", status="
				+ status + ", user_image=" + user_image + "]";
	}

	

}
