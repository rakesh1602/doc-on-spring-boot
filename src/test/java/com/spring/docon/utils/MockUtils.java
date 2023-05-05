package com.spring.docon.utils;

import com.spring.docon.entity.AccountEntity;
import com.spring.docon.entity.EnrollmentEntity;
import com.spring.docon.entity.GenderEntity;
import com.spring.docon.entity.PatientEntity;
import com.spring.docon.entity.PrefixEntity;
import com.spring.docon.entity.ProviderEntity;
import com.spring.docon.entity.UserRegisterEntity;
import com.spring.docon.model.Patient;
import com.spring.docon.model.Provider;
import com.spring.docon.response.PatientResponse;

import java.time.Duration;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class MockUtils {

    public static PatientEntity patientEntity(){
        PatientEntity patientEntity=new PatientEntity();
        patientEntity.setPatientId(1L);

        UserRegisterEntity userRegisterEntity=new UserRegisterEntity();
        userRegisterEntity.setFirstName("rajesh");
        userRegisterEntity.setLastName("chavan");

        AccountEntity accountEntity=new AccountEntity();
        accountEntity.setEmailId("rk@gmail.com");
        patientEntity.setUserRegisterEntity(userRegisterEntity);
        patientEntity.getUserRegisterEntity().setAccountEntity(accountEntity);
        return patientEntity;
    }

    public static EnrollmentEntity enrollmentEntity(){
        PatientEntity patientEntity=new PatientEntity();
        patientEntity.setPatientId(3L);

        EnrollmentEntity enrollmentEntity=new EnrollmentEntity();
        enrollmentEntity.setEnrollmentId(UUID.fromString("80a90253-b6bb-419b-bb95-ae7f54851a34"));
        enrollmentEntity.setExpiry(Duration.ofDays(15));
        enrollmentEntity.setPatientEntity(patientEntity);

        return enrollmentEntity;
    }

    public static List<GenderEntity> genderEntity(){
        GenderEntity genderEntity=new GenderEntity();
        genderEntity.setGender("Male");
        return Collections.singletonList(genderEntity);
    }

    public static List<PrefixEntity> prefixEntities(){
        PrefixEntity prefixEntity=new PrefixEntity();
        prefixEntity.setPrefix("Dr");
        return Collections.singletonList(prefixEntity);
    }

    public static Patient patient(){
        Patient patient = new Patient();
        patient.getPerson().setFirstName("Rakesh");
        patient.getPerson().getAccount().setEmailId("rk@example.com");
        patient.getPerson().setPhoneNumber("1234567890");

        PatientResponse patientResponse = new PatientResponse();
        patientResponse.setPatientId(1L);
        return patient;
    }

    public static Provider provider() {
        Provider provider = new Provider();
        provider.setExperience("1 Year");
        provider.setMciRegistrationNumber("1234");
        return provider;
    }

    public static  ProviderEntity providerEntity(){
        ProviderEntity providerEntity = new ProviderEntity();
        Provider provider=new Provider();

        providerEntity.setProviderId(1L);
        providerEntity.setMciRegistrationNumber(provider.getMciRegistrationNumber());
        providerEntity.setExperience(provider.getExperience());

        return providerEntity;

    }
}
