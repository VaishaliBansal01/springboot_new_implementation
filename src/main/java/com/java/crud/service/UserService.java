package com.java.crud.service;

import com.java.crud.co.SignUpCO;
import com.java.crud.co.UserCO;
import com.java.crud.dto.ResponseDTO;
import com.java.crud.entity.Person;
import com.java.crud.exception.InvalidResponseException;

public interface UserService {

    ResponseDTO<Person> login(UserCO userCO) throws InvalidResponseException;

    ResponseDTO<Person> sinUp(SignUpCO signUpCO) throws InvalidResponseException;

   ResponseDTO<Person> userGetById(Long id);
}
