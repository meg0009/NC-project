package com.chivapchichi.controller;

import com.chivapchichi.model.Members;
import com.chivapchichi.model.Tournament;
import com.chivapchichi.repository.RecordRepository;
import com.chivapchichi.repository.TournamentRepository;
import com.chivapchichi.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import javax.validation.Valid;
import java.text.SimpleDateFormat;

@Controller
public class FormRegistrationController {

    @Autowired
    TournamentRepository tournamentRepository;

    @Autowired
    RecordRepository recordRepository;

    @Autowired
    RegistrationService registrationService;

    @GetMapping("/registration")
    public String homepage(Model model) {
        model.addAttribute("tournaments", tournamentRepository.findAll());
        return "homepage";
    }

    @GetMapping("/registration/{id}")
    public String formRegistration(@PathVariable("id") Integer id, Model model) {
        Tournament tournament = tournamentRepository.getById(id);
        model.addAttribute("tournaments", tournamentRepository.findAll());
        model.addAttribute("selectedTournament", tournament);
        model.addAttribute("records", recordRepository.findByTournament(id));
        model.addAttribute("dateFormatter", new SimpleDateFormat("dd.MM.yy HH:mm"));
        model.addAttribute("member", new Members());
        return "registration-form";
    }

    @GetMapping("/registration/{idTournament}/member")
    public String registered(@PathVariable("idTournament") Integer id, Model model, @ModelAttribute @Valid Members member, BindingResult result) {
        if (result.hasErrors()) {
            Tournament tournament = tournamentRepository.getById(id);
            model.addAttribute("tournaments", tournamentRepository.findAll());
            model.addAttribute("selectedTournament", tournament);
            model.addAttribute("records", recordRepository.findByTournament(id));
            model.addAttribute("dateFormatter", new SimpleDateFormat("dd.MM.yy HH:mm"));
            model.addAttribute("member", member);
            //model.addAllAttributes(result.getModel());
            //result.getFieldErrors().stream().forEach(System.out::println);
            //model.addAllAttributes(result.getFieldErrors());
            //result.rejectValue("userName", "member.userName", "Неправильный формат почты");
            return "registration-form";
        }

        try {
            registrationService.register(id, member.getUserName(), member.getFio());
            //System.out.println("hello");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "success-registration";
    }
}
