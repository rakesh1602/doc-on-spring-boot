package com.spring.docon.service;

import com.spring.docon.entity.AccountEntity;
import com.spring.docon.entity.EnrollmentEntity;
import com.spring.docon.mapper.AccountMapper;
import com.spring.docon.model.Account;
import com.spring.docon.model.patch.Password;
import com.spring.docon.repository.AccountRepository;
import com.spring.docon.repository.EnrollmentRepository;
import com.spring.docon.response.AccountResponse;
import com.spring.docon.response.AccountValidateResponse;
import com.spring.docon.response.PasswordResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Base64;
import java.util.Optional;
import java.util.UUID;

@Service
@Log4j2
public class AccountService {

    private final AccountRepository accountRepository;

    private final EnrollmentRepository enrollmentRepository;

    private final AccountMapper accountMapper;

    private final AccountEntity accountEntity = new AccountEntity();

    private final PasswordResponse passwordResponse = new PasswordResponse();

    private final AccountValidateResponse accountValidateResponse = new AccountValidateResponse();

    @Autowired
    public AccountService(AccountRepository accountRepository, EnrollmentRepository enrollmentRepository, AccountMapper accountMapper) {
        this.accountRepository = accountRepository;
        this.enrollmentRepository = enrollmentRepository;
        this.accountMapper = accountMapper;
    }

    public PasswordResponse createPassword(UUID enrollmentId, Password password, String emailId, Date dob) {

        log.info("Retrieving enrollment id.");

        Optional<EnrollmentEntity> enrollmentEntity = Optional.ofNullable(Optional.ofNullable(enrollmentRepository.findByUUID(enrollmentId))
                .orElseThrow(() -> new RuntimeException("Enrollment id not found " + enrollmentId)));

        String email = enrollmentEntity.get().getPatientEntity().getUserRegisterEntity().getAccountEntity().getEmailId();
        Date dateOfBirth = enrollmentEntity.get().getPatientEntity().getUserRegisterEntity().getDob();
        log.info("Email id {} and date of birth {} for enrollment id {} is", email, dateOfBirth, enrollmentId);

        if (email.equals(emailId) && dob.equals(dateOfBirth)) {
            log.info("Inside the create password method");
            enrollmentEntity.get().getPatientEntity().getUserRegisterEntity().getAccountEntity().setPassword(Base64.getEncoder().encodeToString(password.getPassword().getBytes()));
            accountRepository.save(accountEntity);
            log.info("Password saved successfully.");
        } else {
            throw new RuntimeException("Email id or password does not valid.");
        }
        passwordResponse.setMessage("Your password has been created successfully!");

        return passwordResponse;
    }

    public AccountResponse addAccount(Account account) {

        log.info("Adding account details.");

        AccountEntity accountEntity = accountMapper.modelToEntity(account);
        accountRepository.save(accountEntity);
        log.info("Account details saved successfully.");

        AccountResponse accountResponse = new AccountResponse();
        accountResponse.setAccountId(accountEntity.getAccountId());
        log.info("Response id : {}", accountEntity.getAccountId());

        return accountResponse;
    }

    public AccountValidateResponse passwordValidate(Account account) {

        Optional<AccountEntity> optionalAccountEntity = Optional.ofNullable(accountRepository.findByEmailId(account.getEmailId()).orElseThrow(() -> new RuntimeException("email id is not valid")));

        String email = optionalAccountEntity.get().getEmailId();
        String decodedPassword = new String(Base64.getDecoder().decode(optionalAccountEntity.get().getPassword()));

        if (email.equals(account.getEmailId()) && decodedPassword.equals(account.getPassword())) {
            accountValidateResponse.setMessage("Your login successfully!");
            accountValidateResponse.setId(optionalAccountEntity.get().getAccountId());
        } else {
            accountValidateResponse.setMessage("Email id or Password are not valid.");
            throw new RuntimeException("Email id or Password does not valid.");
        }

        return accountValidateResponse;
    }
}
