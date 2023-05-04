package com.spring.docon.controller;

import com.spring.docon.entity.PatientEntity;
import com.spring.docon.model.Patient;
import com.spring.docon.service.PatientService;
import com.spring.docon.utils.MockUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.io.IOException;

@ExtendWith(MockitoExtension.class)
class PatientControllerTest {

    private static final String POST_PATIENT_URL= "/patients";

    @InjectMocks
    private PatientController patientController;

    @Mock
    private PatientService patientService;
    private MockMvc mockMvc;

    private PatientEntity patientEntity;

    private Patient patient;

    @BeforeEach
    void setUp() throws IOException{
        mockMvc= MockMvcBuilders.standaloneSetup(patientController).build();
    }

    @Test
    void addPatient() {
        patientEntity= MockUtils.patientEntity();

        //Mockito.when(patientService.addPatient(patient)).thenReturn(1);
    }

    @Test
    void getPatient() {
    }
}