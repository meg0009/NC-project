package com.chivapchichi.controller.rest;

import com.chivapchichi.model.Users;
import com.chivapchichi.repository.UsersRepository;
import com.chivapchichi.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("rest/users")
public class UsersRestController {

    @Autowired
    private UsersRepository repository;

    @GetMapping("/get-logins")
    public List<String> getLogins() {
        return repository.findAllLogins();
    }

    @PostMapping("/create-user")
    public String createNewUser(@RequestBody Users user, UsersService service) {
        return service.addNewUser(repository, user);
    }
}
