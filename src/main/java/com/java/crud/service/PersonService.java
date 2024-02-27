package com.java.crud.service;

import com.java.crud.co.PersonCO;
import com.java.crud.co.SignUpCO;
import com.java.crud.dto.PersonRequestDTO;
import com.java.crud.dto.ResponseDTO;
import com.java.crud.entity.Person;
import com.java.crud.exception.InvalidResponseException;

import java.util.List;

public interface PersonService {

    ResponseDTO<Person> saveInformation(SignUpCO signUpCO) throws InvalidResponseException;

    ResponseDTO<List<Person>> getAllRecord() throws InvalidResponseException;

    ResponseDTO<Person> findPersonById(String id) throws InvalidResponseException;

    ResponseDTO<Person> updateInformation(PersonCO personCO) throws InvalidResponseException;

    ResponseDTO<Person> deletePersonRecordById(String id) throws InvalidResponseException;

    ResponseDTO<List<PersonRequestDTO>> getFilteredRecord();
}
