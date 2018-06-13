package com.crud.tasks.service;

import com.crud.tasks.domain.Mail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class SimpleEmailService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleMailMessage.class);

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private MailCreatorService mailCreatorService;

    public void send(final Mail mail, EmailType type) {
        LOGGER.info("Starging email preparation...");
        try {
           javaMailSender.send(createMimeMessage(mail, type));
            if (type == EmailType.TRELLO_CARD) {
                LOGGER.info("Email - new Trello Card has been sent successfully.");
            } else if (type == EmailType.SCHEDULED) {
                LOGGER.info("Email - Scheduled email has been sent successfully.");
            }

       } catch (MailException e) {
            LOGGER.error("Failed to process email sending: ", e.getMessage(), e);
       }
    }

    private SimpleMailMessage createMailMessage(final Mail mail) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(mail.getMailTo());
        mailMessage.setSubject(mail.getSubject());
        mailMessage.setText(mail.getMessage());
        if(mail.getToCc() != null) mailMessage.setCc(mail.getToCc());
        return mailMessage;
    }

    private MimeMessagePreparator createMimeMessage(final Mail mail, EmailType type) {
        return mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setTo(mail.getMailTo());
            messageHelper.setSubject(mail.getSubject());

            if (type == EmailType.TRELLO_CARD) {
                messageHelper.setText(mailCreatorService.buildTrelloCardEmail(mail.getMessage()), true);
            } else if (type == EmailType.SCHEDULED)  {
                messageHelper.setText(mailCreatorService.buildTaskQuantityEmail(mail.getMessage()), true);
            }
        };
    }
}
