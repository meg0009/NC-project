package com.chivapchichi.controller.admin;

import com.chivapchichi.model.Tournament;
import com.chivapchichi.service.TournamentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("admin-panel")
public class TournamentController {

    private final TournamentService tournamentService;

    @Autowired
    public TournamentController(TournamentService tournamentService) {
        this.tournamentService = tournamentService;
    }

    @GetMapping("/add-tournament")
    public String addTournament(Model model) {
        model.addAttribute("tournament", new Tournament());
        return "admin-panel/add-tournament";
    }

    @PostMapping("/add-tournament")
    public String postAddTournament(@ModelAttribute Tournament tournament) {
        tournamentService.saveTournament(tournament);
        return "success-registration";
    }

    @GetMapping("/delete-tournament")
    public String deleteTournament(Model model) {
        model.addAttribute("tournaments", tournamentService.getAllTournaments());
        return "admin-panel/delete-tournament";
    }

    @PostMapping("/delete-tournament")
    public String postDeleteTournament(@RequestParam(value = "id") Integer id) {
        tournamentService.deleteTournament(id);
        return "success-registration";
    }

    @GetMapping("/change-tournament")
    public String changeTournament(Model model) {
        model.addAttribute("tournaments", tournamentService.getAllTournaments());
        return "admin-panel/change-tournament";
    }

    @GetMapping("/change-tournament/{id}")
    public String changeTournamentId(@PathVariable(value = "id") Integer id, Model model) {
        Optional<Tournament> tournament = tournamentService.getTournamentById(id);
        if (tournament.isPresent()) {
            model.addAttribute("tournament", tournament.get());
            return "admin-panel/change-tournament-id";
        }
        return "admin-panel/tournament-error";
    }

    @PostMapping("/change-tournament")
    public String postChangeTournament(@ModelAttribute Tournament tournament) {
        tournamentService.saveTournament(tournament);
        return "success-registration";
    }
}
