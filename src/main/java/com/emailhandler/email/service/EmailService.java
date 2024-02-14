package com.emailhandler.email.service;

import java.util.List;

import javax.mail.MessagingException;

import com.emailhandler.email.dto.MailConfigDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.emailhandler.email.model.Email;

import jakarta.mail.internet.MimeMessage;


@Service
public class EmailService {

	 private static final Logger log = LoggerFactory.getLogger(EmailService.class);
	 private final JavaMailSender mailSender;
	 private final JavaMailSenderImpl javaMailSender;
	 private MimeMessage message;
	 private MimeMessageHelper helper;

	    @Autowired
	    public EmailService(JavaMailSender mailSender, JavaMailSenderImpl javaMailSender) {
	        this.mailSender = mailSender;
			this.javaMailSender = javaMailSender;
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
	     
	    public boolean sendEmailWithAttachment(Email email, MultipartFile multipartFile) throws MessagingException{
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

		@Async
		public Boolean sendAsyncEmail(Email email){
			try {
				message = mailSender.createMimeMessage();
				helper = new MimeMessageHelper(message);

				helper.setTo(email.getTo());
				helper.setSubject(email.getSubject());
				helper.setText(email.getText());
				mailSender.send(message);
				return true;
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}

		public MailConfigDto getMailConfig(){
			int port = javaMailSender.getPort();
			String username = javaMailSender.getUsername();
			String password = javaMailSender.getPassword();
			String protocol = javaMailSender.getProtocol();
			String host = javaMailSender.getHost();
			var properties = javaMailSender.getJavaMailProperties();

			return new MailConfigDto(port,host,username,password, protocol,properties);
		}

}
