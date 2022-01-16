package com.chivapchichi.controller;

import com.chivapchichi.model.Person;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;

@Controller
public class AddPersonController {

    @GetMapping("/add-person")
    public String personForm(Model model) {
        model.addAttribute("person", new Person());
        return "index";
    }

    @PostMapping("/add-person")
    public String personSubmit(@ModelAttribute Person person, RestTemplate rest) {
        ServletUriComponentsBuilder builder = ServletUriComponentsBuilder.fromCurrentContextPath();
        builder.path("/rest/persons");
        URI reqUri = builder.build().toUri();
        rest.postForEntity(reqUri, person, Person.class);

        return "submit-person";
    }
}
