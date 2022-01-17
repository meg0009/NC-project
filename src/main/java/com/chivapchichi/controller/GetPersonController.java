package com.chivapchichi.controller;

import com.chivapchichi.model.Person;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Controller
public class GetPersonController {

    @GetMapping("/get-persons")
    public String getPersons(Model model, RestTemplate rest) {
        ServletUriComponentsBuilder builder = ServletUriComponentsBuilder.fromCurrentContextPath();
        builder.path("/rest/persons");
        URI reqUri = builder.build().toUri();
        List<Person> personList = rest.getForObject(reqUri, List.class);

        model.addAttribute("persons", personList);
        return "persons";
    }
}
