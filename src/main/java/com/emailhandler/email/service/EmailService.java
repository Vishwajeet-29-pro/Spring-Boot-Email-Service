package com.emailhandler.email.service;

import java.util.List;

import javax.mail.MessagingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.emailhandler.email.model.Email;

import jakarta.mail.internet.MimeMessage;


@Service
public class EmailService {

//	@Autowired
//	private JavaMailSender javaMailSender;
//
//	
//	public boolean sendEmail(String to, String subject, String text) throws MessagingException, IOException {
//
//		SimpleMailMessage msg = new SimpleMailMessage();
//		msg.setTo(to);
//
//		msg.setSubject(subject);
//		msg.setText(text);
//
//		javaMailSender.send(msg);
//		return "";
//
//	}
	 private static final Logger log = LoggerFactory.getLogger(EmailService.class);
	 private final JavaMailSender mailSender;
	 private MimeMessage message;
	 private MimeMessageHelper helper;

	    @Autowired
	    public EmailService(JavaMailSender mailSender) {
	        this.mailSender = mailSender;
	    }

	   
	    
	    public boolean sendEmail(Email email) throws MessagingException {
	        try {
	        	message = mailSender.createMimeMessage();
	            helper = new MimeMessageHelper(message, true);
	            helper.setTo(email.getTo());
	            helper.setSubject(email.getSubject());
	            helper.setText(email.getText());
	            mailSender.send(message);
	            return true;
	        } catch (Exception e) {
	        	log.error("Error occurs "+e);
	            return false;
	        }
	    }
	    
	    public boolean sendEmailToMany(List<String> emails, Email email) throws MessagingException {
	        try {
	            message = mailSender.createMimeMessage();
	            helper = new MimeMessageHelper(message, true);
	            helper.setTo(email.getTo());
	            helper.setBcc(emails.toArray(new String[emails.size()]));
	            helper.setSubject(email.getSubject());
	            helper.setText(email.getText());
	            mailSender.send(message);
	            return true;
	        } catch (Exception e) {
	            return false;
	        }
	    }
	     
	    public boolean sendEmailWithAttactment(Email email, MultipartFile multipartFile) throws MessagingException{
	    	try {
	    		message = mailSender.createMimeMessage();
	    		helper = new MimeMessageHelper(message,true);
	    	
	    		helper.setTo(email.getTo());
	    		helper.setSubject(email.getSubject());
	    		helper.setText(email.getText());
	    		helper.addAttachment(multipartFile.getOriginalFilename(), multipartFile);
	    		mailSender.send(message);
	    		return true;
	    	}catch(Exception e) {
	    		e.printStackTrace();
	    		return false;
	    	}
	    }

}
