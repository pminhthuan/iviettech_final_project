//package com.iviettech.finalproject.service;
//
//import com.iviettech.finalproject.entity.UserEntity;
//import com.iviettech.finalproject.helper.GmailSender;
//import com.iviettech.finalproject.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import javax.mail.MessagingException;
//import java.io.UnsupportedEncodingException;
//
//import static com.iviettech.finalproject.helper.PasswordEncoder.createHash;
//
//@Service
//public class LoginService {
//    @Autowired
//    UserRepository userRepository;
//
//    public boolean isEmailExisting(String email) {
//        UserEntity user = userRepository.findByEmail(email);
//        return user != null ? true : false;
//    }
//
//    public void doRegistration(UserEntity user){
//        String hashPassword = createHash(user.getPassword());
//        user.setPassword(hashPassword);
//
//        String activationCode = createHash(user.getEmail() + user.getPassword());
//        user.setActivationCode(activationCode);
//
//        userRepository.save(user);
//
//        sendActivationEmail(user);
//    }
//
//    public int doLogin(String email, String password){
//        String hashPassword = createHash(password);
//        UserEntity foundUser = userRepository.findByEmailAndPassword(email, hashPassword);
//        if (foundUser == null) {
//            return 0; // incorrect user name or password
//        } else {
//            if (foundUser.getActivationCode() != null){
//                return 1; // account not activated yet.
//            }
//            return 2; // login ok
//        }
//    }
//
//    private void sendActivationEmail(UserEntity user)  {
//        String subject = "Registration successfully";
//        String activationUrl = "http://localhost:8080/activateAccount?email=" + user.getEmail() + "&code=" + user.getActivationCode();
//        String mailBody = "<h1> Dear " + user.getLastName() + ",<h1>"
//                + "<h2>You've registered successfully to our website. Enjoy with us</h2>"
//                + "<br/>Please click on the following link to activate your account. Once activated, you can sign-in"
//                + "<br/>" + activationUrl;
//
//        try {
//            GmailSender.send(user.getEmail(), subject, mailBody, true);
//        } catch (MessagingException | UnsupportedEncodingException e) {
//            System.out.println(e);
//        }
//    }
//}
