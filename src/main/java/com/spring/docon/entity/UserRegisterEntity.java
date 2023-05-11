package com.spring.docon.entity;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserRegisterEntity {

    @NotBlank(message = "Prefix should not be empty or null")
    private String prefix;

    @NotBlank(message = "First name should not be empty or null.")
    private String firstName;

    @NotBlank(message = "Last name should not be empty or null.")
    private String lastName;

    @NotBlank(message = "Phone number should not be empty or null.")
    private String phoneNumber;

    @NotBlank(message = "Date of birth should not be empty or null.")
    private Date dob;

    @NotBlank(message = "Gender should not be empty or null.")
    private String gender;

    @NotBlank(message = "Role should not be empty or null.")
    private String role;

    private AccountEntity accountEntity;

}
