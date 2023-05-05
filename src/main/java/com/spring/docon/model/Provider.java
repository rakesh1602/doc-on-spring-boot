package com.spring.docon.model;

import com.spring.docon.model.base.Base;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Provider extends Base {

    private String mciRegistrationNumber;

    private String experience;

    private UserRegister user;
}
