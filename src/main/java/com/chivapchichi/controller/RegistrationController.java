package com.chivapchichi.controller;

import com.chivapchichi.model.Members;
import com.chivapchichi.model.Users;
import com.chivapchichi.repository.MembersRepository;
import com.chivapchichi.repository.UsersRepository;
import com.chivapchichi.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class RegistrationController {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private MembersRepository membersRepository;

    @Autowired
    private UsersService usersService;

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("users", new Users());
        model.addAttribute("members", new Members());

        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(@ModelAttribute @Valid Users users, @ModelAttribute Members members, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "registration";
        }
        if (!usersService.addNewUser(usersRepository, users)) {
            model.addAttribute("usernameError", "Пользователь с таким именем существует");
            return "registration";
        }

        Members member = new Members();
        member.setUserName(users.getUserName());
        member.setFio(members.getFio());
        membersRepository.save(member);

        return "redirect:/tournament/registration";
    }
}
