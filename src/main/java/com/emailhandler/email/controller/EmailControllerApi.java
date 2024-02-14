package com.emailhandler.email.controller;

import com.emailhandler.email.dto.MailConfigDto;
import com.emailhandler.email.model.Email;
import com.emailhandler.email.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import javax.mail.Multipart;
import java.util.List;

@RestController
@RequestMapping("/api/v1/email-handler")
public class EmailControllerApi {

    @Autowired
    private EmailService emailService;

    @PostMapping("/send/{email}")
    public ResponseEntity<Boolean> sendMail(@RequestBody Email email) throws MessagingException {
        return ResponseEntity.ok(emailService.sendEmail(email));
    }

    @PostMapping("/send-with-attachment")
    public ResponseEntity<Boolean> sendMailWithAttachment(@RequestBody Email email,
                                                          @RequestParam("attachment") MultipartFile attachment)
            throws MessagingException {
        return ResponseEntity.ok(emailService.sendEmailWithAttachment(email,attachment));
    }

    @PostMapping("/send-to-many-recipients")
    public ResponseEntity<Boolean> sendMailToMultiple(@RequestBody List<String> emails, Email email) throws MessagingException {
        return ResponseEntity.ok(emailService.sendEmailToMany(emails,email));
    }

    @PostMapping("/sendAsyncMail")
    public ResponseEntity<?> sendAsyncMail(@RequestBody Email email){
        return ResponseEntity.ok(emailService.sendAsyncEmail(email));
    }

    @GetMapping("/mail-config")
    public ResponseEntity<MailConfigDto> getMailConfig(){
        return ResponseEntity.ok(emailService.getMailConfig());
    }

}
