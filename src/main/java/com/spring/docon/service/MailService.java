package com.spring.docon.service;

import com.spring.docon.model.EmailRequest;

import com.sendgrid.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@Log4j2
public class MailService {

    @Autowired
    private final SendGrid sendGrid;

    public MailService(SendGrid sendGrid) {
        this.sendGrid = sendGrid;
    }

    private String firstName;
    private String lastName;
    private String uniqueURL;

    public void setUserDetails(String firstName, String lastName, String uniqueURL) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.uniqueURL = uniqueURL;
    }

    public Response sendEmail(EmailRequest emailRequest) {

        String content = "Dear " + this.firstName + " " + this.lastName + " " + "Please confirm you account by click below link \n" + uniqueURL;

        Mail mail = new Mail(new Email("rakesh.chavan776@gmail.com"),
                "Confirm your account.",
                new Email(emailRequest.getTo()),
                new Content("text/plain", content));
        log.info(this.firstName + " " + this.lastName + "" + this.uniqueURL);
        mail.setReplyTo(new Email("rac16021999@gmail.com"));
        Request request = new Request();
        Response response = new Response();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            response = this.sendGrid.api(request);
        } catch (IOException exception) {
            log.info(exception.getMessage());
        }
        return response;
    }
}
