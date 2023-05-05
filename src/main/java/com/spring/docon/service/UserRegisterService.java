package com.spring.docon.service;

import com.spring.docon.entity.AccountEntity;
import com.spring.docon.entity.UserRegisterEntity;
import com.spring.docon.mapper.AccountMapper;
import com.spring.docon.mapper.UserRegisterMapper;
import com.spring.docon.model.Account;
import com.spring.docon.model.UserRegister;
import com.spring.docon.repository.AccountRepository;
import com.spring.docon.repository.UserRepository;
import com.spring.docon.response.UserResponse;
import com.spring.docon.util.PasswordGenerator;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Log4j2
public class UserRegisterService {

    private final UserRegisterMapper userRegisterMapper;

    private final UserRepository userRepository;

    private final AccountRepository accountEntityRepository;

    private final AccountMapper accountMapper;

    private Account account;

    private UserRegisterEntity userRegisterEntity;

    @Value("${topic.name.producer}")
    private String topicName;

    @Autowired
    public UserRegisterService(UserRegisterMapper userRegisterMapper, UserRepository userRepository, AccountRepository accountEntityRepository, AccountMapper accountMapper) {
        this.userRegisterMapper = userRegisterMapper;
        this.userRepository = userRepository;
        this.accountEntityRepository = accountEntityRepository;
        this.accountMapper = accountMapper;
    }

    public Account getAccounts(Long accountId) {

        log.info("Retrieving accounts details for account id {}", accountId);

        Optional<AccountEntity> accountEntityOptional = Optional.ofNullable(accountEntityRepository.findById(userRegisterEntity.getAccountEntity().getAccountId())
                .orElseThrow(() -> new RuntimeException("account id not found {}" + accountId)));

        if (accountEntityOptional.isPresent()) {
            account = accountMapper.entityToModel(accountEntityOptional.get());
            log.info("Account details found");
        }
        return account;
    }

    public List<UserRegister> getAllUsersByAccountId(Long accountId) {

        log.info("Retrieving all users details for account id {}", accountId);

        List<UserRegisterEntity> userRegisterEntities = userRepository.findByAccountId(accountId);

        if (!userRegisterEntities.isEmpty()) {
            List<UserRegister> userRegisters = userRegisterMapper.entityToModels(userRegisterEntities);
            return userRegisters;
        } else {
            throw new RuntimeException("Account id not found");
        }
    }

    public UserRegister getUserById(Long userId) {

        log.info("Retrieving all users details for account id {}", userId);

        Optional<UserRegisterEntity> optionalUserRegisterEntity = Optional.ofNullable(userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Provider id not found {}" + userId)));

        UserRegister userRegister = userRegisterMapper.entityToModel(optionalUserRegisterEntity);
        return userRegister;

    }

    public void deleteUser(Long userId) {

        log.info("Retrieving all users details for account id {}", userId);

        userRepository.findByUserIdAndDeleteFalse(userId);
    }

    public UserResponse addUserByAccountId(UserRegister userRegister, Long accountId) {

        log.info("Retrieving all users details for account id {}", accountId);

        Optional<AccountEntity> accountEntity = Optional.ofNullable(accountEntityRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Provider id not found {}" + accountId)));

        if (accountEntity.isPresent()) {
            userRegisterEntity = userRegisterMapper.modelToEntity(userRegister);
            userRegisterEntity.setAccountEntity(accountEntity.get());
            userRepository.save(userRegisterEntity);
            log.info("User for account id {} saved successfully.", accountId);
        }

        UserResponse userResponse = new UserResponse();
        userResponse.setUserId(userRegisterEntity.getUserId());
        log.info("User response id {}", userRegisterEntity.getUserId());
        return userResponse;
    }

    public UserResponse addUser(UserRegister userRegister) {

        log.info("Adding user details");

        userRegisterEntity = userRegisterMapper.modelToEntity(userRegister);

        String randomPassword = PasswordGenerator.generateRandomPassword(8);
        log.info("Random password {}", randomPassword);

        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setPassword(randomPassword);
        accountEntity.setEmailId(userRegister.getAccount().getEmailId());

        userRegisterEntity.setAccountEntity(accountEntity);
        accountEntityRepository.save(accountEntity);

        userRepository.save(userRegisterEntity);
        log.info("user details saved successfully");

        UserResponse userResponse = new UserResponse();
        userResponse.setUserId(userRegisterEntity.getUserId());

        if (userRegisterEntity.getAccountEntity().getAccountId() != null) {
            Long accountId = userRegisterEntity.getAccountEntity().getAccountId();
            log.info(accountId + " account id");
            getAccounts(accountId);
        }
        return userResponse;
    }
}



