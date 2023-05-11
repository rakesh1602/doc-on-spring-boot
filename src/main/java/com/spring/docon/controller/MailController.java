package com.spring.docon.controller;

import com.sendgrid.Response;
import com.spring.docon.entity.EnrollmentEntity;
import com.spring.docon.entity.UserRegisterEntity;
import com.spring.docon.model.EmailRequest;
import com.spring.docon.service.MailService;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.UUID;

@RestController
@Log4j2
@ToString
@CrossOrigin(origins = "*")
public class MailController {

    @Value("${topic.name.consumer}")
    private String topicName;

    @Value("${base.url}")
    private String baseUrl;

    @Autowired
    private MailService mailService;

    private UserRegisterEntity userRegisterEntity = new UserRegisterEntity();

    @KafkaListener(topics = "${topic.name.consumer}", groupId = "group_id")
    public void consume(ConsumerRecord<String, EnrollmentEntity> payload) {
        log.info("Topic Name: {}", topicName);
        log.info("key: {}", payload.key());
        log.info("Headers: {}", payload.headers());
        log.info("Partition: {}", payload.partition());

        EnrollmentEntity enrollmentEntity = payload.value();
        log.info(" Entity received from topic docon {}", enrollmentEntity);

        String firstName = enrollmentEntity.getPatientEntity().getUserRegisterEntity().getFirstName();
        String lastName = enrollmentEntity.getPatientEntity().getUserRegisterEntity().getLastName();
        String emailId = enrollmentEntity.getPatientEntity().getUserRegisterEntity().getAccountEntity().getEmailId();
        Date dob = enrollmentEntity.getPatientEntity().getUserRegisterEntity().getDob();
        UUID uuid = enrollmentEntity.getEnrollmentId();

        String uniqueURL = baseUrl + uuid;

        log.info("Details received - \n firstName {}, \n lastName {}, \n dob {}, \n emailId {}, \n UUID {}", firstName, lastName, dob, emailId, uuid);

        EmailRequest emailRequest = new EmailRequest();
        log.info("Sending mail id to the mail service");
        emailRequest.setTo(emailId);
        log.info("Setting 'To' " + emailId);

        mailService.setUserDetails(firstName, lastName, uniqueURL);
        log.info("Mail content details firstName {}, lastName {}, uniqueURL {}", firstName, lastName, uniqueURL);

        Response response = mailService.sendEmail(emailRequest);

        if (response.getStatusCode() == 200 || response.getStatusCode() == 202) {
            log.info("Your mail has been sent successfully to the email id : {} ", emailId);
        } else {
            log.info("failed to send mail to the {}", emailId);
        }
    }
}
