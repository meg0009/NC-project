package com.chivapchichi.controller;

import com.chivapchichi.model.Person;
import com.chivapchichi.service.RestApiService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AddPersonController {

    @GetMapping("/add-person")
    public String personForm(Model model) {
        model.addAttribute("person", new Person());
        return "add-person-form";
    }

    @PostMapping("/add-person")
    public String personSubmit(@ModelAttribute Person person, RestApiService service, Authentication authentication) {
        service.addPerson(person);

        return "submit-person";
    }
}
