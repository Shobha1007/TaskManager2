package com.techsteed.TaskManager.controller;

import com.techsteed.TaskManager.dto.GenericResponse;
import com.techsteed.TaskManager.dto.UserLoginDto;
import com.techsteed.TaskManager.dto.UserRegistrationDto;
import com.techsteed.TaskManager.model.PasswordResetToken;
import com.techsteed.TaskManager.model.User;
import com.techsteed.TaskManager.repository.PasswordTokenRepository;
import com.techsteed.TaskManager.service.CustomUserDetailsService;
import com.techsteed.TaskManager.service.EmailService;
import com.techsteed.TaskManager.service.UserService;
import jakarta.mail.Message;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;
import java.util.UUID;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private EmailService emailService;
    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @GetMapping("/index")
    public String home(){
        return "index";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @PostMapping("/register/save")
    public ResponseEntity<String> registerUserAccount(@RequestBody @Valid UserRegistrationDto userRegistrationDto){
        if (userRegistrationDto == null){
            System.out.println("dto is null");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        User user = userService.findUserByEmail(userRegistrationDto.getEmail());
        if ( user != null && user.getEmail() != null && !user.getEmail().isEmpty()){
            return new ResponseEntity<String>("There is already an account with the same email",HttpStatus.BAD_REQUEST);
        }
        System.out.println("Valid username going to save");
        try{

            userService.save(userRegistrationDto);
            return new ResponseEntity<String>(HttpStatus.OK);
        }
        catch (Exception e){
            System.out.println("Error at controller");
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/login/authenticate")
    public String loginUser(@Valid @RequestBody UserLoginDto userDetailDto){
        try{
            UserDetails userDetails = customUserDetailsService.loadUserByUsername(userDetailDto.getEmail());
            if (userDetails.getPassword() != null && userDetails.getPassword().equals(userDetailDto.getPassword())) {
                System.out.println("Login successfull");
                return "index";
            }
            else {
                System.out.println("Unsuccessfull login");
                return "login";
            }

        }
        catch (Exception e){
            return "login";
        }
    }

    //Reset password ------------------------------------------------------------------------

    @PostMapping("/user/resetPassword/{email}")
    public String resetPassword(@PathVariable("email") String userEmail) {
        System.out.println("REset Password");
        System.out.println("email : " + userEmail);
        User user = userService.findUserByEmail(userEmail);
        if (user == null) {
            System.out.println("username not found");
            throw new UsernameNotFoundException("The given username is invalid");
        }
        String token = UUID.randomUUID().toString();
        System.out.println("token : " + token);
        userService.createPasswordResetTokenForUser(user, token);

//        mailSender.send(emailService.constructResetTokenEmail(request.getContextPath(),
//                request.getLocale(), token, user));
        return "index";
    }

}
