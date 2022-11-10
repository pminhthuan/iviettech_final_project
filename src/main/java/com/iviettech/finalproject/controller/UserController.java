package com.iviettech.finalproject.controller;

import com.iviettech.finalproject.entity.RoleEntity;
import com.iviettech.finalproject.entity.UserEntity;
import com.iviettech.finalproject.repository.UserRepository;
import com.iviettech.finalproject.service.ProductService;
import com.iviettech.finalproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductService productService;


    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String showLoginPage(Model model) {
        model.addAttribute("user",new UserEntity());
        return "login";
    }

    @RequestMapping(value = "/register",method = RequestMethod.GET)
    public String showRegisterPage(Model model) {
        model.addAttribute("accounts", new UserEntity());
        return "register";
    }

    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public String doRegister(UserEntity user, RoleEntity role, Model model) {
        boolean isEmailExisting = userService.isEmailExisting(user.getEmail());

        if (isEmailExisting){
            model.addAttribute("errorSignUp", "Email <" + user.getEmail() + "> already existing");
            user.setEmail(""); // reset email
            model.addAttribute("accounts", user); // keep entered data
            return "register";
        }
        userService.doRegistration(user, role);
        model.addAttribute("message", "Thanks for your registration. Please go to your mailbox to activate your account.");
        model.addAttribute("cssBootstrap", "alert-success");
        return "login";
    }

    @RequestMapping(value = "activateAccount", method = GET)
    public String activateAccount(@RequestParam(name = "email") String email,
                                  @RequestParam(name = "code") String code,
                                  Model model) {
        int result = userRepository.activateUser(email, code);
        if (result == 1) {
            model.addAttribute("message", "Your account has been activated. Now, you can login. Thank you.");
            model.addAttribute("cssBootstrap", "alert-success");
        } else {
            model.addAttribute("message", "Your activation code is not correct.");
            model.addAttribute("cssBootstrap", "alert-danger");
        }
        return "login";
    }

    @RequestMapping(value = "login", method = POST)
    public String doLogin(@RequestParam("email") String email,
                          @RequestParam("pass") String password,
                          @RequestParam(name = "remember", required = false) String remember,
                          Model model, HttpSession session, HttpServletResponse response) {
        int result = userService.doLogin(email, password);

        if (result == 0) {
            model.addAttribute("errorSignIn", "Login failed. Incorrect user name or password");
            return "login";
        } else {
            if (result == 1) {
                model.addAttribute("errorSignIn", "Login failed. You account not activated yet");
                return "login";
            } else if (result == 2) {
                userService.rememberMe(email, password, remember, response);
                //model.addAttribute("message", "Welcome " + email);
                //model.addAttribute("cssBootstrap", "alert-success");
                session.setAttribute("user", userService.findUser(email, password));
            }
        }

        return "redirect:/";
    }


    @RequestMapping(value = "/logout",method = RequestMethod.GET)
    public String doLogout(HttpSession session) {
        session.removeAttribute("user");
        return "redirect:/";
    }

    @RequestMapping(value = "/profile",method = RequestMethod.GET)
    public String viewProfile(Model model, HttpSession session) {
        UserEntity user = (UserEntity) session.getAttribute("user");
        Map<Integer, String> provinceMap = productService.getProvinces();
        model.addAttribute("user", user);
        model.addAttribute("province",provinceMap);
        return "profile";
    }


    @RequestMapping(value = "/updateProfile", method = POST, produces = "text/plain;charset=UTF-8")
    public String updateProfile(UserEntity user, Model model, HttpSession session) {
        userService.updateUser(user);
        Map<Integer, String> provinceMap = productService.getProvinces();
        model.addAttribute("user", user);
        model.addAttribute("province",provinceMap);
        session.setAttribute("user", user);
        return "profile";
    }

    @RequestMapping(value = "/purcharehistory",method = RequestMethod.GET)
    public String viewPurchaseHistory() {

        return "order_history";
    }

    @RequestMapping(value = "/changepass",method = RequestMethod.GET)
    public String viewChangePass() {

        return "change_pass";
    }


}
