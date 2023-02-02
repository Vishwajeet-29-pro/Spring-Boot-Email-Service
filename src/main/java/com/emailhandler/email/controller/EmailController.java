package com.emailhandler.email.controller;

import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.emailhandler.email.model.Email;
import com.emailhandler.email.service.EmailService;

@Controller
@RequestMapping("/email")

public class EmailController {

	private EmailService emailService;
	
	@Autowired
	public EmailController(EmailService emailService) {
		this.emailService=emailService;
	}
	
	@GetMapping("/home")
	public String homepage(Model model) {
		Email email = new Email();
		model.addAttribute("emailhome", email);
		return "home-page";
	}
	
	@GetMapping("/addmail")
	public String getForm(Model model) {
		Email email = new Email();
		model.addAttribute("email", email);
		
		return "email-form";
		
	}
	
	@GetMapping("/addmailattachment")
	public String getFormForAttachment(Model model) {
		model.addAttribute("attachment", new Email());
		return "email-attachment";
	}
	
	@GetMapping("/sendToMany")
	public String getFormForManyMailId(Model model) {
		model.addAttribute("sendMany", new Email());
		return "send-many";
	}
	
	@PostMapping("/save")
	public String save(
			@ModelAttribute("email") Email email,
			BindingResult bindingResult,
			RedirectAttributes redirectAttributes
			) throws MessagingException {
		
		if(bindingResult.hasErrors()) {
			return "email-form";
		} 
		boolean sent = emailService.sendEmail(email);
		if(sent) {
			redirectAttributes.addFlashAttribute("message","Email sent successfully");
		}
		else {
			redirectAttributes.addFlashAttribute("errormessage", "Error occurs while sending email");
			
		}
		return "redirect:/email/home";
	}
	
	@PostMapping("/savewithattachment")
	public String save(
			@ModelAttribute("attachment") Email email,
			BindingResult bindingResult,
			RedirectAttributes redirectAttributes,
			@RequestParam("attachments") MultipartFile file) throws MessagingException {
		if(bindingResult.hasErrors()) {
			System.err.println("Erro occur");
			List<FieldError> errors = bindingResult.getFieldErrors();
			   for (FieldError error : errors) {
			      System.out.println(error.getField() + ": " + error.getDefaultMessage());
			   }
			return "email-attachment";
		}
		
		boolean sent = emailService.sendEmailWithAttactment(email, file);
		if(sent) {
			System.err.println("success");
			redirectAttributes.addFlashAttribute("message", "Email send successfully");
		}else {
			System.err.println("Error in sent");
			redirectAttributes.addFlashAttribute("errormessage","Error occurs while sending email");
		}
		return "redirect:/email/home";
	}
	
	@PostMapping("/sendMany")
	public String saveMany(@ModelAttribute Email email,
							List<String> emails,
							BindingResult bindingResult,
							RedirectAttributes redirectAttributes) throws MessagingException {
		if(bindingResult.hasErrors()) {
			return "email-form";
		}
		boolean sent = emailService.sendEmailToMany(emails, email);
		if(sent) {
			redirectAttributes.addFlashAttribute("message", "Email Send Successfully to all");
		}else {
			redirectAttributes.addFlashAttribute("errormessage", "Error occurs while sending email");
		}
		
		return "redirect:/email/home";
	}
} 
