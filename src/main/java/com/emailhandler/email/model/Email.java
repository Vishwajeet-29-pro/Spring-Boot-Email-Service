package com.emailhandler.email.model;

import org.springframework.web.multipart.MultipartFile;

public class Email {

	    private String to;
	    private String subject;
	    private String text;
	    private String cc;
	    private String bcc;
	    private MultipartFile[] attachments;
	    
	    
	  //getters and setters
	    
		public String getTo() {
			return to;
		}
		public void setTo(String to) {
			this.to = to;
		}
		public String getSubject() {
			return subject;
		}
		public void setSubject(String subject) {
			this.subject = subject;
		}
		public String getText() {
			return text;
		}
		public void setText(String text) {
			this.text = text;
		}
		public String getCc() {
			return cc;
		}
		public void setCc(String cc) {
			this.cc = cc;
		}
		public String getBcc() {
			return bcc;
		}
		public void setBcc(String bcc) {
			this.bcc = bcc;
		}
		public MultipartFile[] getAttachments() {
			return attachments;
		}
		public void setAttachments(MultipartFile[] attachments) {
			this.attachments = attachments;
		}

	    
	

}
