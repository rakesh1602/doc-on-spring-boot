package com.spring.docon.controller;

import com.spring.docon.model.Account;
import com.spring.docon.model.patch.Password;
import com.spring.docon.response.AccountResponse;
import com.spring.docon.response.AccountValidateResponse;
import com.spring.docon.response.PasswordResponse;
import com.spring.docon.service.AccountService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.print.attribute.standard.Media;
import java.sql.Date;
import java.util.UUID;

@Tag(name = "Account Controller", description = "Add account and create password")
@RequestMapping(path = "v1")
@RestController
@CrossOrigin(originPatterns = "*")
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @ApiResponse(responseCode = "200", description = "Success")
    @ApiResponse(responseCode = "400", description = "Invalid request")
    @ApiResponse(responseCode = "404", description = "Not found")
    @ApiResponse(responseCode = "500", description = "System error")
    @PatchMapping(value = "/password/{enrollmentId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PasswordResponse> createPassword(@PathVariable UUID enrollmentId, @RequestBody @Valid Password password, String emailId, Date dob) {

        PasswordResponse passwordResponse = accountService.createPassword(enrollmentId, password, emailId, dob);

        return new ResponseEntity<>(passwordResponse, HttpStatus.OK);
    }

    @ApiResponse(responseCode = "200", description = "Success")
    @ApiResponse(responseCode = "400", description = "Invalid request")
    @ApiResponse(responseCode = "404", description = "Not found")
    @ApiResponse(responseCode = "500", description = "System error")
    @PostMapping(value = "/accounts", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AccountResponse> addAccount(@RequestBody @Valid Account account) {

        AccountResponse accountResponse = accountService.addAccount(account);

        return new ResponseEntity<>(accountResponse, HttpStatus.OK);
    }

    @ApiResponse(responseCode = "200", description = "Success")
    @ApiResponse(responseCode = "400", description = "Invalid request")
    @ApiResponse(responseCode = "404", description = "Not found")
    @ApiResponse(responseCode = "500", description = "System error")
    @PostMapping(value = "/passwordvalidate", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AccountValidateResponse> validatePassword(@RequestBody Account account) {

        AccountValidateResponse accountValidateResponse = accountService.passwordValidate(account);

        return new ResponseEntity<>(accountValidateResponse, HttpStatus.OK);
    }
}




