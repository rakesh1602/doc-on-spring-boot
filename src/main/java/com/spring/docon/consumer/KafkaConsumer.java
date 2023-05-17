package com.spring.docon.consumer;

import com.spring.docon.model.Request;
import com.spring.docon.service.OtpService;
import com.spring.docon.entity.EnrollmentEntity;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@Log4j2
@ToString
public class KafkaConsumer {

    @Value("${topic.name.consumer}")
    private String topicName;

    @Value("${base.url}")
    private String baseUrl;

    @Autowired
    private OtpService otpService;

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
        String phoneNumber = enrollmentEntity.getPatientEntity().getUserRegisterEntity().getPhoneNumber();
        UUID uuid = enrollmentEntity.getEnrollmentId();

        String uniqueURL = baseUrl + uuid;

        log.info("Details received - \n Phone Number {} \n UUID {}", phoneNumber, uuid);

        Request request = new Request();
        log.info("Sending phone number to the OTP service");
        request.setPhoneNumber(phoneNumber);
        log.info("Setting 'To' " + phoneNumber);

        otpService.setUserDetails(firstName, lastName, uniqueURL);
        log.info("OTP content details firstName {}, lastName {}, uniqueURL {}", firstName, lastName, uniqueURL);

        otpService.sendOTP(request);
        log.info("Send otp method called.");
    }

}
