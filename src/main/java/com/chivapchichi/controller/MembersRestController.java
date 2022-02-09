package com.chivapchichi.controller;

import com.chivapchichi.model.Members;
import com.chivapchichi.repository.MembersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("rest/members")
public class MembersRestController {

    @Autowired
    private MembersRepository repository;

    @GetMapping("/get-members")
    public List<Members> getAllMembers() {
        return repository.findAll();
    }

    @PostMapping("/add-member")
    public Members addMember(@RequestBody Members member) {
        return repository.save(member);
    }
}
