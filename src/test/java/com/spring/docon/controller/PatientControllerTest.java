package com.spring.docon.controller;

import com.spring.docon.entity.PatientEntity;
import com.spring.docon.model.Patient;
import com.spring.docon.service.PatientService;
import com.spring.docon.utils.MockUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PatientControllerTest {

    private PatientController sut;

    private PatientService patientServiceMock;

    private Patient patient;

    private long patientResponse;

    private PatientEntity patientEntity;

    @BeforeEach
    public void setup() {
        patientServiceMock = mock(PatientService.class);
        sut = new PatientController(patientServiceMock);
    }

    @Test
    public void testGetAllPatients() {

        patient = MockUtils.patient();

        when(patientServiceMock.getAllPatients()).thenReturn(Collections.singletonList((patient)));

        ResponseEntity<List<Patient>> responseEntity = sut.getAllPatients();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(Collections.singletonList(patient), responseEntity.getBody());
    }

    @Test
    public void testUpdatePatient() {
        Long patientId = 1L;

        patient = MockUtils.patient();

        Patient updatedPatient = new Patient();
        updatedPatient.getPerson().setFirstName("updated rakesh");
        updatedPatient.getPerson().getAccount().setEmailId("newemail@gmail.com");
        updatedPatient.getPerson().setPhoneNumber("1122");

        when(patientServiceMock.updatePatient(patientId, patient)).thenReturn(updatedPatient);

        ResponseEntity<Patient> responseEntity = sut.updatePatient(patientId, patient);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
       // assertEquals(updatedPatient, responseEntity.getBody());
    }

    @Test
    public void testGetPatient() {
        Long patientId = 1L;

        patient = MockUtils.patient();

        when(patientServiceMock.getPatient(patientId)).thenReturn(patient);

        ResponseEntity<Patient> responseEntity = sut.getPatient(patientId);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(patient, responseEntity.getBody());
    }

    @Test
    public void testDeleteById() {
        Long patientId = 1L;
    }
}