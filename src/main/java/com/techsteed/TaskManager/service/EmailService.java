package com.techsteed.TaskManager.service;

import com.techsteed.TaskManager.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class EmailService {
    @Value("${spring.mail.username}") private String sender;

    public SimpleMailMessage constructResetTokenEmail(
            String contextPath, Locale locale, String token, User user) {
        String url = contextPath + "/user/changePassword?token=" + token;
        String message = "Click to reset password  : ";
        return constructEmail("Reset Password", message + " \r\n" + url, user);
    }
    private SimpleMailMessage constructEmail(String subject, String body,
                                             User user) {
        SimpleMailMessage email = new SimpleMailMessage();
        email.setSubject(subject);
        email.setText(body);
        email.setTo(user.getEmail());
        email.setFrom(sender);
        return email;
    }
}
