package com.chivapchichi.controller;

import com.chivapchichi.model.Record;
import com.chivapchichi.model.Tournament;
import com.chivapchichi.repository.MembersRepository;
import com.chivapchichi.repository.RecordRepository;
import com.chivapchichi.repository.TournamentRepository;
import com.chivapchichi.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.SimpleDateFormat;
import java.util.Optional;

@Controller
@RequestMapping("tournament")
public class TournamentRegistrationController {

    @Autowired
    TournamentRepository tournamentRepository;

    @Autowired
    RecordRepository recordRepository;

    @Autowired
    MembersRepository membersRepository;

    @Autowired
    RegistrationService registrationService;

    @GetMapping("/registration")
    public String homepage(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentPrincipal = authentication.getName();
            model.addAttribute("principal", currentPrincipal);
        }
        model.addAttribute("tournament", tournamentRepository.findAll());
        return "homepage";
    }

    @GetMapping("/registration/{idTournament}")
    public String formRegistration(@PathVariable("idTournament") Integer id, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentPrincipal = authentication.getName();
            model.addAttribute("principal", currentPrincipal);
            if (recordRepository.findByUserNameAndTournament(currentPrincipal, id).isPresent()) {
                model.addAttribute("registration", "Удалить запись");
            } else {
                model.addAttribute("registration", "Записаться");
            }
        }
        Tournament tournament = tournamentRepository.getById(id);
        model.addAttribute("tournament", tournamentRepository.findAll());
        model.addAttribute("selectedTournament", tournament);
        model.addAttribute("record", recordRepository.findByTournament(id));
        model.addAttribute("dateFormatter", new SimpleDateFormat("dd.MM.yy HH:mm"));
        return "tournament-registration";
    }

    @PostMapping("/registration/{idTournament}")
    public String registered(@PathVariable("idTournament") Integer id, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipal = authentication.getName();

        Optional<Record> record = recordRepository.findByUserNameAndTournament(currentPrincipal, id);
        if (record.isPresent()) {
            try {
                Record r = record.orElseThrow(() -> new Exception("Error"));
                recordRepository.deleteById(r.getId());
            } catch (Exception e) {
                e.printStackTrace();
            }
            return "success-registration";
        }

        String fio = membersRepository.findByUserName(currentPrincipal).getFio();
        try {
            registrationService.register(id, currentPrincipal, fio);
            //System.out.println("hello");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "success-registration";
    }
}
