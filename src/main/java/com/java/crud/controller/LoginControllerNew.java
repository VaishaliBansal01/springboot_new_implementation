package com.java.crud.controller;

import com.java.crud.co.SignUpCO;
import com.java.crud.co.UserCO;
import com.java.crud.exception.InvalidResponseException;
import com.java.crud.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginControllerNew {
    @Autowired
    UserServiceImpl userService;


    @RequestMapping("/home")
    public String home()
    {
        System.out.println("this is home page");
        return "home";
    }
    @RequestMapping(value= "/new_signup",method = RequestMethod.GET)
    public String signUp(@ModelAttribute SignUpCO signUpCO) throws InvalidResponseException {
        userService.sinUp(signUpCO);
        return "signup";
    }

  @RequestMapping(value ="/login")
  public String showLoginPage(@ModelAttribute UserCO userCO) throws InvalidResponseException {
      userService.login(userCO);
      return "login";
  }
}
