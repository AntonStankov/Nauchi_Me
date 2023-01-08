package com.example.nauchiMe.service;

import com.example.nauchiMe.dto.UserDto;
import com.example.nauchiMe.entity.UserDomain;
import com.example.nauchiMe.dto.UserDto;
import com.example.nauchiMe.entity.UserDomain;

public interface UserService {
    Boolean save(UserDomain entity);
    Boolean register(UserDto dto);
    Boolean existsByUsername(String username);
    UserDomain findByUsername(String username);

}
