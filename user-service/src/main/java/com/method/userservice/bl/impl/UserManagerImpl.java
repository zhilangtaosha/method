package com.method.userservice.bl.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.method.userservice.bl.UserManager;
import com.method.userservice.entity.User;
import com.method.userservice.entity.VerificationCode;

import static com.method.userservice.util.Constants.*;
import static com.method.userservice.util.Email.*;

import java.util.Date;

import javax.mail.MessagingException;

@Service
public class UserManagerImpl implements UserManager {

	@Autowired
	MongoTemplate mongoTemplate;

	public User register(User provided) throws Exception {
		Query query = new Query();
		query.addCriteria(Criteria.where(NAME).is(provided.getName()));
		User user = mongoTemplate.findOne(query, User.class);
		if (user != null)
			throw new Exception("name already is exist !!, please change other name");
		mongoTemplate.save(provided);
		return provided;
	}

	public User auth(User provided) {
		return null;
	}

	public User login(User provided) throws Exception {
		Query query = new Query();
		query.addCriteria(Criteria.where(NAME).is(provided.getName())
				.andOperator(Criteria.where(PASSWORD).is(provided.getPassword())));
		User user = mongoTemplate.findOne(query, User.class);
		if (user == null)
			throw new Exception("username and password is not avlid!!");
		return user;
	}

	@Override
	public String sendVerificationCode(User provided) throws MessagingException, Exception {
		int code = getRandomNum();
		sendMail(provided.getEmail(), "verificationCode", "you verificationCode is:" + code);

		Query query = new Query();
		query.addCriteria(Criteria.where(EMAIL).is(provided.getEmail()));
		VerificationCode verificationCode = mongoTemplate.findOne(query, VerificationCode.class);
		if (verificationCode != null) {
			verificationCode.setCode(code + "");
			verificationCode.setExpiry(new Date().getTime() + 30 * 60 * 1000);
			mongoTemplate.save(verificationCode);
			return code + "";
		}

		mongoTemplate.save(new VerificationCode(provided.getEmail(), code + "", new Date().getTime() + 30 * 60 * 1000));
		return code + "";
	}

	@Override
	public User retrivePassword(User provided, VerificationCode providedCode, String newPassword) throws Exception {
		Criteria criteria = new Criteria();
		criteria.and(EMAIL).is(provided.getEmail());
		criteria.and(CODE).is(providedCode.getCode());
		criteria.and(EXPIRY).gt(new Date().getTime());
		Query query = new Query(criteria);
		VerificationCode verificationCode = mongoTemplate.findOne(query, VerificationCode.class);
		if (verificationCode == null)
			throw new Exception("the verificationCode is not available");
		Query queryUser = new Query();
		queryUser.addCriteria(Criteria.where(EMAIL).is(provided.getEmail()));
		User user = mongoTemplate.findOne(queryUser, User.class);
		if (user == null)
			throw new Exception("user is not exist !!");
		user.setPassword(newPassword);
		mongoTemplate.save(user);
		mongoTemplate.remove(verificationCode);
		return user;
	}

}
