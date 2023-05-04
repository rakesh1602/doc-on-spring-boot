package com.spring.docon.utils;

import com.spring.docon.entity.AccountEntity;
<<<<<<< HEAD
import com.spring.docon.entity.EnrollmentEntity;
import com.spring.docon.entity.GenderEntity;
import com.spring.docon.entity.PatientEntity;
import com.spring.docon.entity.PrefixEntity;
import com.spring.docon.entity.UserRegisterEntity;

import java.time.Duration;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

=======
import com.spring.docon.entity.PatientEntity;
import com.spring.docon.entity.UserRegisterEntity;

>>>>>>> f5e8be4199568b1c8165cf53086ebe5c43b8cd83
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
<<<<<<< HEAD

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

=======
>>>>>>> f5e8be4199568b1c8165cf53086ebe5c43b8cd83
}
