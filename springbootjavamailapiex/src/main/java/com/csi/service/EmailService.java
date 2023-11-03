package com.csi.service;

import com.csi.model.EmailModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Service
@Slf4j
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @EventListener(ApplicationReadyEvent.class)
    public void triggerMail() throws MessagingException {

    }

    public void sendEmail(EmailModel emailModel) throws MessagingException {

        log.info("*************TO EMAIL*********: " + emailModel.getToEmail());

        log.info("*************SUBJECT*********: " + emailModel.getEmailSubject());

        MimeMessage mimeMessage = mailSender.createMimeMessage();

        MimeMessageHelper mimeMessageHelper
                = new MimeMessageHelper(mimeMessage, true);

        mimeMessageHelper.setFrom("jadhavkiran2011@gmail.com");
        mimeMessageHelper.setTo(emailModel.getToEmail());
        mimeMessageHelper.setCc(emailModel.getCcEmail());


        mimeMessageHelper.setText(emailModel.getEmailBody());
        mimeMessageHelper.setSubject(emailModel.getEmailSubject());

        FileSystemResource fileSystem
                = new FileSystemResource(new File(emailModel.getEmailAttachment()));

        mimeMessageHelper.addAttachment(fileSystem.getFilename(),
                fileSystem);

        mailSender.send(mimeMessage);
        log.info("Mail Send Successfully...");

    }
}
