package com.java.crud.service;

import com.java.crud.co.SignUpCO;
import com.java.crud.co.UserCO;
import com.java.crud.constant.PersonConstant;
import com.java.crud.dto.ResponseDTO;
import com.java.crud.entity.Person;
import com.java.crud.entity.User;
import com.java.crud.exception.InvalidResponseException;
import com.java.crud.repository.PersonRepository;
import com.java.crud.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PersonRepository personRepository;


    @Override
    public ResponseDTO<Person> login(UserCO userCO) throws InvalidResponseException {
        ResponseDTO<Person> responseDTO = new ResponseDTO<>();
        try {
            Optional<User> optionalUser = userRepository.findByPasswordAndEmail(userCO.password, userCO.email);
            Optional<Person> optional = personRepository.findByEmail(userCO.email);
            if (optionalUser.isPresent()) {
                responseDTO.setSuccessResponse(optional.get(), "");
            } else {
                responseDTO.setFailureResponse(null);
            }
        } catch (Exception e) {
            responseDTO.setErrorResponse(e, null);
            throw new InvalidResponseException(e.getMessage());
        }
        return responseDTO;
    }

    @Override
    public ResponseDTO<Person> sinUp(SignUpCO signUpCO) throws InvalidResponseException {
        ResponseDTO<Person> responseDTO = new ResponseDTO<>();
        try {
            Optional<Person> optional = personRepository.findByEmail(signUpCO.email);
            if (optional.isPresent()) {
                responseDTO.setFailureResponse(PersonConstant.EMAIL_EXIST);
            } else {
                User user = new User();
                user.email = signUpCO.email;
                user.password = signUpCO.password;
                userRepository.save(user);
                Person person = new Person();
                person.name = signUpCO.name;
                person.age = signUpCO.age;
                person.contact = signUpCO.contact;
                person.address = signUpCO.address;
                person.email = signUpCO.email;
                personRepository.save(person);
                responseDTO.setSuccessResponse(person, PersonConstant.CREATE_SUCCESS);
            }
        } catch (Exception e) {
            responseDTO.setErrorResponse(e, null);
            throw new InvalidResponseException(e.getMessage());
        }
        return responseDTO;
    }

    @Override
    public ResponseDTO<Person> userGetById(Long id) {
       ResponseDTO<Person> responseDTO= new ResponseDTO<>();
       Optional<User> optionalUser = userRepository.findById(String.valueOf(id));
       if(optionalUser.isPresent())
       {
           User user =optionalUser.get();
           String userEmail =user.getEmail();

        Person person =   personRepository.findByEmail(userEmail).get();
        responseDTO.setSuccessResponse(person, "");
       }
       return responseDTO;
    }
}
