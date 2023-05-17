package com.spring.docon.controller;

import com.spring.docon.model.Request;
import com.spring.docon.response.OtpResponse;
import com.spring.docon.service.OtpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TwilioMailController {

    @Autowired
    private final OtpService otpService;

    public TwilioMailController(OtpService otpService) {
        this.otpService = otpService;
    }

    @PostMapping(path = "/otp", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OtpResponse> sendOTP(@RequestBody Request request){
        OtpResponse otpResponse=otpService.sendOTP(request);
        return new ResponseEntity<>(otpResponse, HttpStatus.OK);
    }
}
