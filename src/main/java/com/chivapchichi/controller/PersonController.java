package com.chivapchichi.controller;

import com.chivapchichi.model.Person;
import com.chivapchichi.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("rest")
public class PersonController {

    @Autowired
    private PersonRepository repository;

    @GetMapping("/persons")
    public List<Person> getAllPersons() {
        return repository.findAll();
    }

    @PostMapping("/persons")
    public Person createPerson(@RequestBody Person person) {
        return repository.save(person);
    }
}
