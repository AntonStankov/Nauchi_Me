package com.example.nauchiMe.dto;

import com.example.nauchiMe.enums.UserTypeEnum;
import com.example.nauchiMe.enums.UserTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private String username;
    private String password;
    private UserTypeEnum type;

}
