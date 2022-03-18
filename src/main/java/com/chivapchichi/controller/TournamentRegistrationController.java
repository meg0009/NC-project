package com.chivapchichi.controller;

import com.chivapchichi.model.Tournament;
import com.chivapchichi.repository.MembersRepository;
import com.chivapchichi.repository.RecordRepository;
import com.chivapchichi.repository.TournamentRepository;
import com.chivapchichi.service.TournamentService;
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

@Controller
@RequestMapping("tournament")
public class TournamentRegistrationController {

    private final RecordRepository recordRepository;

    private final TournamentService tournamentService;

    @Autowired
    public TournamentRegistrationController(RecordRepository recordRepository, TournamentService tournamentService) {
        this.recordRepository = recordRepository;
        this.tournamentService = tournamentService;
    }

    @GetMapping("/registration")
    public String homepage(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentPrincipal = authentication.getName();
            model.addAttribute("principal", currentPrincipal);
        }
        model.addAttribute("tournament", tournamentService.getAllTournaments());
        return "homepage";
    }

    @GetMapping("/registration/{idTournament}")
    public String formRegistration(@PathVariable("idTournament") Integer id, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentPrincipal = authentication.getName();
            model.addAttribute("principal", currentPrincipal);

            model.addAttribute("tournamentsRecords", tournamentService.getByUserName(currentPrincipal));

            if (recordRepository.findByUserNameAndTournament(currentPrincipal, id).isPresent()) {
                model.addAttribute("registration", "Удалить запись");
            } else {
                model.addAttribute("registration", "Записаться");
            }
        }
        Tournament tournament = tournamentService.getTournamentById(id).get();
        model.addAttribute("tournament", tournamentService.getAllTournaments());
        model.addAttribute("selectedTournament", tournament);
        model.addAttribute("recordMain", recordRepository.findByTournamentMainTeam(id));
        model.addAttribute("recordReserve", recordRepository.findByTournamentReserve(id));
        model.addAttribute("dateFormatter", new SimpleDateFormat("dd.MM.yy HH:mm"));
        return "tournament-registration";
    }

    @PostMapping("/registration/{idTournament}")
    public String registered(@PathVariable("idTournament") Integer id, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipal = authentication.getName();

        tournamentService.makeRegistrationOrUnregister(currentPrincipal, id);
        return "success-registration";
    }
}
