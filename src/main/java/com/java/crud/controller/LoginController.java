package com.java.crud.controller;

import com.java.crud.co.SignUpCO;
import com.java.crud.co.UserCO;
import com.java.crud.dto.ResponseDTO;
import com.java.crud.entity.Person;
import com.java.crud.exception.InvalidResponseException;
import com.java.crud.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
@CrossOrigin(value = "http://localhost:4200")
public class LoginController {

    @Autowired
    UserServiceImpl userService;

    @PostMapping(value = "/sinUp", consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseDTO<Person> sinUp(@RequestBody SignUpCO signUpCO) throws InvalidResponseException {
        return userService.sinUp(signUpCO);
    }

    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseDTO<Person> login(@RequestBody UserCO userCO) throws InvalidResponseException {
        return userService.login(userCO);
    }












}
