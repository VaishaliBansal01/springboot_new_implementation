package com.java.crud.service;

import com.java.crud.co.PersonCO;
import com.java.crud.co.SignUpCO;
import com.java.crud.constant.PersonConstant;
import com.java.crud.dto.PersonRequestDTO;
import com.java.crud.dto.ResponseDTO;
import com.java.crud.entity.Person;
import com.java.crud.exception.InvalidResponseException;
import com.java.crud.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class PersonServiceImpl implements PersonService {

    @Autowired
    PersonRepository personRepository;

    @Autowired
    UserServiceImpl userService;

    @Override
    public ResponseDTO<Person> saveInformation(SignUpCO signUpCO) throws InvalidResponseException {
        ResponseDTO<Person> responseDTO = new ResponseDTO<>();
        try {
            ResponseDTO<Person> response = userService.sinUp(signUpCO);
            if (response.status) {
                responseDTO.setSuccessResponse(response.data, PersonConstant.ADD_SUCCESS);
            } else {
                responseDTO.setFailureResponse(PersonConstant.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            responseDTO.setFailureResponse(PersonConstant.ERROR_MESSAGE);
            throw new InvalidResponseException(e.getMessage());
        }
        return responseDTO;
    }

    @Override
    public ResponseDTO<List<Person>> getAllRecord() throws InvalidResponseException {
        ResponseDTO<List<Person>> responseDTO = new ResponseDTO<>();
        try {
            List<Person> personList = personRepository.findAll();
            if (!personList.isEmpty()) {
                responseDTO.setSuccessResponse(personList, null);
            } else {
                responseDTO.setFailureResponse(PersonConstant.NO_RECORD_EXIST);
            }
        } catch (Exception e) {
            responseDTO.setFailureResponse(null);
            throw new InvalidResponseException(e.getMessage());
        }
        return responseDTO;
    }

    @Override
    public ResponseDTO<Person> findPersonById(String id) throws InvalidResponseException {
        ResponseDTO<Person> responseDTO = new ResponseDTO<>();
        try {
            Optional<Person> optionalPerson = personRepository.findById(Long.parseLong(id));
            if (optionalPerson.isPresent()) {
                Person person = optionalPerson.get();
                responseDTO.setSuccessResponse(person, "");
            } else {
                responseDTO.setFailureResponse(PersonConstant.NO_RECORD_EXIST);
            }
        } catch (Exception e) {
            responseDTO.setFailureResponse(null);
            throw new InvalidResponseException(e.getMessage());
        }
        return responseDTO;
    }

    @Override
    public ResponseDTO<Person> updateInformation(PersonCO personCO) throws InvalidResponseException {
        ResponseDTO<Person> responseDTO = new ResponseDTO<>();
        try {
            Optional<Person> optionalPerson = personRepository.findById(personCO.id);
            if (optionalPerson.isPresent()) {
                Person person = optionalPerson.get();
                person.name = personCO.name;
                person.age = personCO.age;
                person.contact = personCO.contact;
                person.address = personCO.address;
                person.email = personCO.email;
                Person updatePerson = personRepository.save(person);
                responseDTO.setSuccessResponse(updatePerson, PersonConstant.UPDATE_SUCCESS);
            } else {
                responseDTO.setFailureResponse(PersonConstant.PERSON_NOT_EXIST);
            }
        } catch (Exception e) {
            responseDTO.setFailureResponse(null);
            throw new InvalidResponseException(e.getMessage());
        }
        return responseDTO;
    }

    @Override
    public ResponseDTO<Person> deletePersonRecordById(String id) throws InvalidResponseException {
        ResponseDTO<Person> responseDTO = new ResponseDTO<>();
        try {
            Long personId = Long.parseLong(id);
            Optional<Person> person = personRepository.findById(personId);
            if (person.isPresent()) {
                personRepository.deleteById(personId);
                responseDTO.setSuccessResponse(null, PersonConstant.DATA_DELETE);
            } else {
                responseDTO.setFailureResponse(PersonConstant.DATA_CAN_NOT_DELETE);
            }
        } catch (Exception e) {
            responseDTO.setFailureResponse(null);
            throw new InvalidResponseException(e.getMessage());
        }
        return responseDTO;
    }

    @Override
    public ResponseDTO<List<PersonRequestDTO>> getFilteredRecord() {

        ResponseDTO<List<PersonRequestDTO>> responseDTO = new ResponseDTO<>();

        List<Person> personList = personRepository.findAll();
             List<PersonRequestDTO> personRequestDTOS = new ArrayList<>();
        for (Person person : personList) {
            personRequestDTOS.add(new PersonRequestDTO(person.getEmail(), person.getAddress()));
        }
        if (!personRequestDTOS.isEmpty()) {
            responseDTO.setSuccessResponse(personRequestDTOS, null);
        }
        return (ResponseDTO<List<PersonRequestDTO>>) personRequestDTOS;
    }

}
