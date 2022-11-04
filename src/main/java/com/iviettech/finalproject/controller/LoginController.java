package com.iviettech.finalproject.controller;

import com.iviettech.finalproject.entity.UserEntity;
import com.iviettech.finalproject.repository.UserRepository;
import com.iviettech.finalproject.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;

    @Autowired
    UserRepository userRepository;




    @RequestMapping(value = "/login")
    public String viewLogin(Model model) {


        return "login/login";
    }
    @RequestMapping(value = "/sign-up", method = GET)
    public String viewRegister(Model model) {
        model.addAttribute("user", new UserEntity());
        model.addAttribute("action", "sign-up");

        return "login/register";
    }

    @RequestMapping(value = "/sign-up", method = POST)
    public String doRegistration(UserEntity user, Model model) {
        boolean isEmailExisting = loginService.isEmailExisting(user.getEmail());
        if (isEmailExisting) {
            model.addAttribute("errorSignUp", "Email <" + user.getEmail() + "> already existing");
            user.setEmail(""); // reset email
            model.addAttribute("user", user); // keep entered data
            return "login/register";
        }

        loginService.doRegistration(user);
        model.addAttribute("message", "Thanks for your registration. Please go to your mailbox to activate your account.");
        model.addAttribute("cssBootstrap", "alert-success");
        return "login/login";
    }

    @RequestMapping(value = "/login", method = POST)
    public String doLogin(@RequestParam("email") String email,
                          @RequestParam("pass") String password,
                          Model model) {
        int result = loginService.doLogin(email, password);

        model.addAttribute("user", new UserEntity());
        if (result == 0) {
            model.addAttribute("errorSignIn", "Login failed. Incorrect user name or password");
            return "login/login";
        } else {
            if (result == 1) {
                model.addAttribute("errorSignIn", "Login failed. You account not activated yet");
                return "login/login";
            } else if (result == 2) {
                model.addAttribute("message", "Welcome " + email);
                model.addAttribute("cssBootstrap", "alert-success");
            }
        }

        return "redirect:/admin";
    }

    @RequestMapping(value = "/activateAccount", method = GET)
    public String activateAccount(@RequestParam(name = "email") String email,
                                  @RequestParam(name = "code") String code,
                                  Model model) {
        int result = userRepository.activateUser(email, code);
        if (result == 1) {
            model.addAttribute("message", "Your account has ben activated. Now, you can login. Thank you.");
            model.addAttribute("cssBootstrap", "alert-success");
        } else {
            model.addAttribute("message", "Your activation code is not correct.");
            model.addAttribute("cssBootstrap", "alert-danger");
        }
        return "index";
    }
}
