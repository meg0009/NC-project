package com.chivapchichi.controller.rest;

import com.chivapchichi.model.Tournament;
import com.chivapchichi.repository.TournamentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("rest/tournaments")
public class TournamentRestController {

    @Autowired
    private TournamentRepository repository;

    @GetMapping("/get-tournaments")
    public List<Tournament> getTournaments() {
        return repository.findAll();
    }

    @PostMapping("/add-tournament")
    public Tournament addTournament(@RequestBody Tournament tournament) {
        // не работает и возможно у даты не тот тип данных
        return repository.save(tournament);
    }
}
