package com.chivapchichi.controller;

import com.chivapchichi.model.Person;
import com.chivapchichi.service.RestApiService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class GetPersonController {

    @GetMapping("/get-persons")
    public String getPersons(Model model, RestApiService service) {
        List<Person> personList = service.getPersons();

        model.addAttribute("persons", personList);
        return "persons";
    }
}
