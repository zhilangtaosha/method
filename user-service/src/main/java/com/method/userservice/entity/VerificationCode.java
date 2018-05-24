package com.method.userservice.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class VerificationCode {
	@Id
	String id;
	String email;
	String code;
	Long expiry;
	
	public VerificationCode() {
	}
	public VerificationCode(String email, String code, Long expiry) {
		this.email = email;
		this.code = code;
		this.expiry = expiry;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Long getExpiry() {
		return expiry;
	}
	public void setExpiry(Long expiry) {
		this.expiry = expiry;
	}
	
}
