package com.spring.docon.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.spring.docon.model.base.Base;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Validated
public class Account extends Base {

    @NotBlank(message = "Email id should not be empty or null.")
    private String emailId;

    @JsonIgnore
    private String password;
}
