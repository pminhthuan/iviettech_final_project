package com.iviettech.finalproject.service;

import com.iviettech.finalproject.entity.RoleEntity;
import com.iviettech.finalproject.entity.UserEntity;
import com.iviettech.finalproject.helper.GmailSender;
import com.iviettech.finalproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

import static com.iviettech.finalproject.helper.PasswordEncoder.createHash;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public boolean isEmailExisting(String email){
        UserEntity user = userRepository.findByEmail(email);
        return user != null ? true:false;
    }

    public void doRegistration(UserEntity user, RoleEntity role){
        String hashPassword = createHash(user.getPassword());
        user.setPassword(hashPassword);

        String activationCode = createHash(user.getEmail()+user.getPassword());
        user.setActivationCode(activationCode);
        role.setId(1);
        user.setRole(role);

        userRepository.save(user);

        sendActivationEmail(user);
    }

    private void sendActivationEmail(UserEntity user)  {
        String subject = "Activate Registration";
        String activationUrl = "http://localhost:8080/activateAccount?email=" + user.getEmail() + "&code=" + user.getActivationCode();
        String mailBody = "<h1> Dear " + user.getFirstName()+ user.getLastName() + ",<h1>"
                + "<h4>You've registered successfully to our website. Enjoy with us</h4>"
                + "<br/>Please click on the following link to activate your account. Once activated, you can sign-in"
                + "<br/>" + activationUrl;

        try {
            GmailSender.send(user.getEmail(), subject, mailBody, true);
        } catch (MessagingException | UnsupportedEncodingException e) {
            System.out.println(e);
        }
    }

    public int doLogin(String email, String password){
        UserEntity findUser = findUser(email,password);
        if(findUser == null){
            return 0; // incorrect user name or password
        } else {
            if (findUser.getActivationCode() != null){
                return 1; // account not activated yet.
            }
            return 2; // login ok
        }
    }

    public UserEntity findUser (String email, String password){
        String hashPassword = createHash(password);
        UserEntity findUser = userRepository.findByEmailAndPassword(email, hashPassword);
        return findUser;
    }

    public void updateUser(UserEntity user) {
        userRepository.updateUser(user.getFirstName(), user.getLastName(), user.getPhoneNumber(), user.getAddressDetail(), user.getProvince().getId(), user.getDistrict().getId(), user.getWard().getId(), user.getId());
    }

}
