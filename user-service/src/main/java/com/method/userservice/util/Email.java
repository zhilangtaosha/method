package com.method.userservice.util;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

public class Email {
	private static JavaMailSenderImpl mailSender = createMailSender();

	private static JavaMailSenderImpl createMailSender() {
		JavaMailSenderImpl sender = new JavaMailSenderImpl();
		sender.setHost("smtp.qq.com");
		sender.setPort(465);
		sender.setUsername("895703375@qq.com");
		sender.setPassword("avelnbqzlscxbfci");
		Properties properties = new Properties();
		properties.setProperty("mail.host", "smtp.qq.com");
		properties.setProperty("mail.transport.protocol", "smtp");
		properties.setProperty("mail.smtp.auth", "true");
		properties.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		properties.setProperty("mail.smtp.port", "465");
		properties.setProperty("mail.smtp.socketFactory.port", "465");
		sender.setJavaMailProperties(properties);
		return sender;
	}

	public static void sendMail(String to, String subject, String html)
			throws MessagingException, UnsupportedEncodingException, javax.mail.MessagingException {
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
		messageHelper.setFrom("895703375@qq.com");
		messageHelper.setTo(to);
		messageHelper.setSubject(subject);
		messageHelper.setText(html, true);
		mailSender.send(mimeMessage);
	}

	public static int getRandomNum() {
		int randNum = 100000 + (int) (Math.random() * ((999999 - 100000) + 1));
		return randNum;
	}

	public static void main(String[] args)
			throws MessagingException, UnsupportedEncodingException, javax.mail.MessagingException {
		int i = getRandomNum();
		sendMail("895703375@qq.com", "congratulations to you", "congratulations to you, you are the number of " + i + "!!");
	}
}
