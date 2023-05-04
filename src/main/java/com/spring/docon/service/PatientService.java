package com.spring.docon.service;

import com.spring.docon.entity.AccountEntity;
import com.spring.docon.entity.PatientEntity;
import com.spring.docon.entity.UserRegisterEntity;
import com.spring.docon.mapper.PatientMapper;
import com.spring.docon.model.Enrollment;
import com.spring.docon.model.Patient;
import com.spring.docon.repository.PatientRepository;
import com.spring.docon.response.PatientResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Log4j2
public class PatientService {
    private final PatientRepository patientRepository;

    private final PatientMapper patientMapper;

    private final EnrollmentService enrollmentService;

    private final Enrollment enrollment = new Enrollment();

    private PatientEntity patientEntity;

    @Autowired
    public PatientService(PatientRepository patientRepository, PatientMapper patientMapper, EnrollmentService enrollmentService) {
        this.patientMapper = patientMapper;
        this.patientRepository = patientRepository;
        this.enrollmentService = enrollmentService;
    }

    public PatientResponse addPatient(Patient patient) {

        log.info("Adding patient details.");

        patientEntity = patientMapper.modelToEntity(patient);
        patientRepository.save(patientEntity);
        log.info("Patient details saved successfully.");

        enrollmentService.createEnrollment(patientEntity.getPatientId(), enrollment);
        log.info("Create enrollment method has been called.");

        PatientResponse patientResponse = new PatientResponse();
        patientResponse.setPatientId(patientEntity.getPatientId());
        log.info("Response id : {}", patientResponse.getPatientId());

        return patientResponse;
    }

    public List<Patient> getAllPatients() {

        log.info("Retrieving all patients");

        List<PatientEntity> personEntity = patientRepository.findAll();

        if(!personEntity.isEmpty()) {
            List<Patient> patientRequest = patientMapper.entityToModels(personEntity);
            return patientRequest;
        } else {
            throw new RuntimeException("Patients not found.");
        }
    }

    public Patient getPatient(Long patientId) {

        log.info("Retrieving patient details for patient id {} ",patientId);

        Optional<PatientEntity> optionalPatientEntity = Optional.ofNullable(patientRepository.findById(patientId)
                .orElseThrow(() -> new RuntimeException("Patient id not found {}" + patientId)));

        Patient patient = patientMapper.entityToModel(optionalPatientEntity);
        return patient;
    }

    public void deletePatient(Long patientId) {

        log.info("Retrieving patient details for patient id {} ",patientId);

        patientRepository.findByPatientIdAndDeleteFalse(patientId);
    }

    public Patient updatePatient(Long patientId, Patient patient) {

        log.info("Retrieving patient details for patient id {} ",patientId);

       Optional<PatientEntity> oldPatientEntity = Optional.ofNullable(patientRepository.findById(patientId)
               .orElseThrow(() -> new RuntimeException("Patient id not found {}" + patientId)));

        UserRegisterEntity userRegisterEntity = oldPatientEntity.get().getUserRegisterEntity();
        Long userId = userRegisterEntity.getUserId();
        AccountEntity accountEntity = oldPatientEntity.get().getUserRegisterEntity().getAccount();
        Long accountId = accountEntity.getAccountId();

        PatientEntity newPatientEntity = patientMapper.modelToEntity(patient);
        newPatientEntity.setPatientId(patientId);
        newPatientEntity.getUserRegisterEntity().setUserId(userId);
        newPatientEntity.getUserRegisterEntity().getAccount().setAccountId(accountId);

        patientEntity= patientRepository.save(newPatientEntity);
        log.info("Patient details updated successfully.");

        return patientMapper.entityToModel(Optional.of(newPatientEntity));
    }
}

