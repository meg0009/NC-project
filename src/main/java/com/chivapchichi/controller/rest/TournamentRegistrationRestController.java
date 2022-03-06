package com.chivapchichi.controller.rest;

import com.chivapchichi.model.Tournament;
import com.chivapchichi.repository.RecordRepository;
import com.chivapchichi.repository.TournamentRepository;
import com.chivapchichi.service.TournamentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("rest/tournament")
public class TournamentRegistrationRestController {

    private final TournamentService tournamentService;

    @Autowired
    public TournamentRegistrationRestController(TournamentService tournamentService) {
        this.tournamentService = tournamentService;
    }

    @GetMapping("/get-all-tournaments")
    public List<Tournament> getAllTournaments() {
        return tournamentService.getAllTournaments();
    }

    @GetMapping("/get-tournament/{id}")
    public Optional<Tournament> getTournamentById(@PathVariable("id") Integer id) {
        return tournamentService.getTournamentById(id);
    }

    @PostMapping("/register/{id}")
    public Map<String, String> registerUnregister(@PathVariable("id") Integer id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipal = authentication.getName();

        boolean reg = tournamentService.makeRegistration(currentPrincipal, id);

        Map<String, String> res = new HashMap<>();
        if (reg) {
            res.put("registered", "now");
        } else {
            res.put("registered", "already");
        }
        return res;
    }

    @PostMapping("/unregister/{id}")
    public Map<String, String> unregisterUnregister(@PathVariable("id") Integer id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipal = authentication.getName();

        boolean unreg = tournamentService.makeUnregistration(currentPrincipal, id);

        Map<String, String> res = new HashMap<>();
        if (unreg) {
            res.put("unregistered", "now");
        } else {
            res.put("unregistered", "already");
        }
        return res;
    }
}
