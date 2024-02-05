package com.techsteed.TaskManager.service;

import com.techsteed.TaskManager.dto.UserRegistrationDto;

import com.techsteed.TaskManager.model.PasswordResetToken;
import com.techsteed.TaskManager.model.Role;
import com.techsteed.TaskManager.model.User;
import com.techsteed.TaskManager.repository.PasswordTokenRepository;
import com.techsteed.TaskManager.repository.RoleRepository;
import com.techsteed.TaskManager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private PasswordTokenRepository passwordTokenRepository;

    public void save(UserRegistrationDto dto){
        Date date = new Date();
        System.out.println(date);

        User user = new User(dto.getName(),date,dto.getName(),date,dto.getEmail(), dto.getName(),passwordEncoder.encode(dto.getPassword()), true);
        System.out.println(user.getEmail());
        Role role = roleRepository.findByName("ROLE_USER");
        if ( role == null){
            Role role1 = new Role();
            role1.setName("ROLE_ADMIN");
            role = roleRepository.save(role1);
        }
        System.out.println(user.getRoles());
        user.setRoles(Arrays.asList(role));
        System.out.println("Error at service layer");
        userRepository.save(user);
    }

    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public void createPasswordResetTokenForUser(User user, String token) {
        PasswordResetToken myToken = new PasswordResetToken(token, user);

        passwordTokenRepository.save(myToken);
    }
}
