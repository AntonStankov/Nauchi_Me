package com.example.nauchiMe.controller;

import com.example.nauchiMe.dto.UserDto;
import com.example.nauchiMe.entity.UserDomain;
import com.example.nauchiMe.service.UserService;
import com.example.nauchiMe.dto.UserDto;
import com.example.nauchiMe.entity.UserDomain;
import com.example.nauchiMe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("register")
    public Boolean register(@RequestBody UserDto dto){
        return userService.register(dto);
    }

    //    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    //    UserDetails principal = (UserDetails) authentication.getPrincipal();
    //    UserDomain user =  userService.findByUsername(principal.getUsername());
}
