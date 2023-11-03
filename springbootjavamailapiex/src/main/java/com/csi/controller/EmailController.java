package com.csi.controller;

import com.csi.model.EmailModel;
import com.csi.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;

@RestController
@RequestMapping("/api")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/sendemail")
    public ResponseEntity<String> sendEmail(@RequestBody EmailModel emailModel) throws MessagingException {
        emailService.sendEmail(emailModel);
        return ResponseEntity.ok("Email Sent Successfully");
    }
}
