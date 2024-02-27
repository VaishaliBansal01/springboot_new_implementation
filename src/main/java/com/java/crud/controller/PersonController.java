package com.java.crud.controller;

import com.java.crud.co.PersonCO;
import com.java.crud.co.SignUpCO;
import com.java.crud.dto.PersonRequestDTO;
import com.java.crud.dto.ResponseDTO;
import com.java.crud.entity.Person;
import com.java.crud.exception.InvalidResponseException;
import com.java.crud.service.PersonServiceImpl;
import com.java.crud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "person")
@CrossOrigin(value = "http://localhost:4200")
public class PersonController {

    @Autowired
    PersonServiceImpl personService;
    @Autowired
    UserService userService;

    @PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseDTO<Person> saveInformation(@RequestBody SignUpCO signUpCO) throws InvalidResponseException {
        return personService.saveInformation(signUpCO);
    }

    @GetMapping(value = "/list")
    ResponseDTO<List<Person>> fetchListOfPerson() throws InvalidResponseException {
        return personService.getAllRecord();
    }

    @GetMapping(value = "/findById/{id}")
    ResponseDTO<Person> findPersonById(@PathVariable("id") String id) throws InvalidResponseException {
        return personService.findPersonById(id);
    }

    @PutMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseDTO<Person> updatePerson(@RequestBody PersonCO personCO) throws InvalidResponseException {
        return personService.updateInformation(personCO);
    }

    @DeleteMapping(value = "/deleteById/{id}")
    ResponseDTO<Person> deletePerson(@PathVariable("id") String id) throws InvalidResponseException {
        return personService.deletePersonRecordById(id);
    }
    @GetMapping(value="/getPerson")
    ResponseDTO<List<PersonRequestDTO>> fetchListOfPersonContainsEmailAddress()
    {
        return personService.getFilteredRecord();
    }

    @GetMapping("/getuserbyid/{id}")
    ResponseDTO<Person> userGetById(@PathVariable("id") Long id)
    {
        return userService.userGetById(id);
    }






}
