package com.spring.docon.controller;

import com.spring.docon.model.Enrollment;
import com.spring.docon.response.EnrollmentResponse;
import com.spring.docon.service.EnrollmentService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Tag(name = "Enrollment", description = "Create and get enrollment")
@RequestMapping(path = "v1")
@Tag(name = "Create enrollment", description = "Create enrollment")
@RestController
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    @Autowired
    public EnrollmentController(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    @ApiResponse(responseCode = "200", description = "Success")
    @ApiResponse(responseCode = "400", description = "Invalid request")
    @ApiResponse(responseCode = "404", description = "Not found")
    @ApiResponse(responseCode = "500", description = "System error")
    @PostMapping(path = "patients/{patientId}/enrollment", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EnrollmentResponse> createEnrollment(@PathVariable Long patientId, @RequestBody @Valid Enrollment enrollment){

        EnrollmentResponse enrollmentResponse=enrollmentService.createEnrollment(patientId, enrollment);

        return new ResponseEntity<>(enrollmentResponse, HttpStatus.OK);
    }

    @ApiResponse(responseCode = "200", description = "Success")
    @ApiResponse(responseCode = "400", description = "Invalid request")
    @ApiResponse(responseCode = "404", description = "Not found")
    @ApiResponse(responseCode = "500", description = "System error")
    @GetMapping(path = "enrollments/{enrollmentId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EnrollmentResponse> getEnrollment(@PathVariable UUID enrollmentId){

        EnrollmentResponse enrollment=enrollmentService.getEnrollment(enrollmentId);

        return new ResponseEntity<>(enrollment, HttpStatus.OK);
    }
}
