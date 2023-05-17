package com.spring.docon.service;

import com.spring.docon.config.TwilioConfig;
import com.spring.docon.model.Request;
import com.spring.docon.response.OtpResponse;
import com.spring.docon.response.OtpStatus;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
@Log4j2
public class OtpService {

    private OtpResponse otpResponse;

    Map<String, String> otpMap = new HashMap<>();

    private final TwilioConfig twilioConfig;

    @Autowired
    public OtpService(TwilioConfig twilioConfig) {
        this.twilioConfig = twilioConfig;
    }

    private String firstName;
    private String lastName;
    private String uniqueURL;

    public void setUserDetails(String firstName, String lastName, String uniqueURL) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.uniqueURL = uniqueURL;
    }

    public OtpResponse sendOTP(Request request){

        try{
            PhoneNumber to=new PhoneNumber(request.getPhoneNumber());
            log.info("To phone number {}",to);

            PhoneNumber from=new PhoneNumber(twilioConfig.getTrialNumber());
            log.info("From phone number {}",from);

            String otp=generateOTP();
            String otpPayload= "Dear "+" "+firstName+" "+lastName+" your OTP is "+otp + " Please click on this link to confirm you password "+"\n"+uniqueURL;

            Message message= Message.creator(to,from,otpPayload).create();
            otpMap.put(request.getUserName(),otp);
            otpResponse=new OtpResponse(OtpStatus.DELIVERED, otpPayload);
            log.info("OTP delivered successfully.");
        } catch (Exception ex){
            otpResponse=new OtpResponse(OtpStatus.FAILED, ex.getMessage());
            log.info("Failed to delivered OTP.");
        }
        return otpResponse;

    }

    private String generateOTP() {
        return new DecimalFormat("000000")
                .format(new Random().nextInt(999999));
    }
}


