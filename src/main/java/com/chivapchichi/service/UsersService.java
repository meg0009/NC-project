package com.chivapchichi.service;

import com.chivapchichi.model.Users;
import com.chivapchichi.repository.UsersRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsersService {

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public String addNewUser(UsersRepository repository, Users user) {
        //PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        Users newUser = new Users();
        newUser.setUserName(user.getUserName());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        if (user.getRole() == null || user.getRole().equals("")) {
            newUser.setRole("ROLE_USER");
        } else {
            newUser.setRole(user.getRole());
        }
        repository.save(newUser);
        return newUser.getUserName();
    }

    public String deleteUser(UsersRepository repository, Users user) {
        repository.delete(user);
        return "deleted";
    }

    /*@Bean
    private PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }*/
}
