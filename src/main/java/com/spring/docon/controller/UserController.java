package com.spring.docon.controller;

import com.spring.docon.model.Account;
import com.spring.docon.model.UserRegister;
import com.spring.docon.response.UserResponse;
import com.spring.docon.service.UserRegisterService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "User", description = "User get, get-all, delete method")
@RequestMapping(path = "v1")
@RestController
@CrossOrigin(originPatterns = "*")
public class UserController {

    private final UserRegisterService userRegisterService;

    @Autowired
    public UserController(UserRegisterService userRegisterService) {
        this.userRegisterService = userRegisterService;
    }

    @PostMapping(path = "/users", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserResponse> addUser(@RequestBody @Valid UserRegister userRegister) {

        UserResponse userResponse = userRegisterService.addUser(userRegister);

        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }

    @ApiResponse(responseCode = "200", description = "Success")
    @ApiResponse(responseCode = "400", description = "Invalid request")
    @ApiResponse(responseCode = "404", description = "Not found")
    @ApiResponse(responseCode = "500", description = "System error")
    @GetMapping(value = "/accounts/{accountId}/users", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UserRegister>> getAllUsersByAccountId(@PathVariable Long accountId) {

        List<UserRegister> userRegister = userRegisterService.getAllUsersByAccountId(accountId);

        return new ResponseEntity<>(userRegister, HttpStatus.OK);
    }

    @ApiResponse(responseCode = "200", description = "Success")
    @ApiResponse(responseCode = "400", description = "Invalid request")
    @ApiResponse(responseCode = "404", description = "Not found")
    @ApiResponse(responseCode = "500", description = "System error")
    @GetMapping(path = "/accounts/{accountId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Account> getAccount(@PathVariable Long accountId) {

        Account account = userRegisterService.getAccounts(accountId);

        return new ResponseEntity<>(account, HttpStatus.OK);
    }

    @ApiResponse(responseCode = "200", description = "Success")
    @ApiResponse(responseCode = "400", description = "Invalid request")
    @ApiResponse(responseCode = "404", description = "Not found")
    @ApiResponse(responseCode = "500", description = "System error")
    @DeleteMapping(value = "/users/{userId}")
    public void deleteUser(@PathVariable Long userId) {
        userRegisterService.deleteUser(userId);
    }

    @ApiResponse(responseCode = "200", description = "Success")
    @ApiResponse(responseCode = "400", description = "Invalid request")
    @ApiResponse(responseCode = "404", description = "Not found")
    @ApiResponse(responseCode = "500", description = "System error")
    @GetMapping(value = "/users/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserRegister> getUserById(@PathVariable Long userId) {

        UserRegister userRegister = userRegisterService.getUserById(userId);

        return new ResponseEntity<>(userRegister, HttpStatus.OK);
    }

//    @DeleteMapping(value = "/users/{userId}")
//    public void deleteUser(@PathVariable Long userId)
//    {
//        userRegisterService.deleteUser(userId);
//    }

    @ApiResponse(responseCode = "200", description = "Success")
    @ApiResponse(responseCode = "400", description = "Invalid request")
    @ApiResponse(responseCode = "404", description = "Not found")
    @ApiResponse(responseCode = "500", description = "System error")
    @PostMapping(value = "/accounts/{accountId}/users", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserResponse> addUsersByAccountId(@RequestBody @Valid UserRegister userRegister, @PathVariable Long accountId) {

        UserResponse userResponse = userRegisterService.addUserByAccountId(userRegister, accountId);

        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }
}
