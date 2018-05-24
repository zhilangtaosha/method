package com.method.userservice.bl;

import javax.mail.MessagingException;

import com.method.userservice.entity.User;
import com.method.userservice.entity.VerificationCode;

public interface UserManager {
	User register(User provided) throws Exception;

	User auth(User provided);

	User login(User provided) throws Exception;

	String sendVerificationCode(User provided) throws MessagingException, Exception;

	User retrivePassword(User provided, VerificationCode providedCode, String newPassword) throws Exception;
}
