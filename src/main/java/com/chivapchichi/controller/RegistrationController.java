package com.chivapchichi.controller;

import com.chivapchichi.model.Members;
import com.chivapchichi.model.UserAndMember;
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
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.util.Map;

@Controller
public class RegistrationController {

    private final UsersRepository usersRepository;

    private final MembersRepository membersRepository;

    private final UsersService usersService;

    private final RestTemplate restTemplate;

    @Autowired
    public RegistrationController(UsersRepository usersRepository, MembersRepository membersRepository, UsersService usersService, RestTemplate restTemplate) {
        this.usersRepository = usersRepository;
        this.membersRepository = membersRepository;
        this.usersService = usersService;
        this.restTemplate = restTemplate;
    }

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("users", new Users());
        model.addAttribute("members", new Members());

        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(@ModelAttribute @Valid Users users, BindingResult result, @ModelAttribute Members members, Model model) {
        if (result.hasErrors()) {
            return "registration";
        }

        ServletUriComponentsBuilder builder = ServletUriComponentsBuilder.fromCurrentContextPath();
        builder.path("/registration/api");

        UserAndMember u = new UserAndMember();
        u.setUsers(users);
        u.setMembers(members);

        Map<String, String> post = restTemplate.postForObject(builder.toUriString(), u, Map.class);

        if (post != null) {
            String r = post.get("result");
            if ("user exists".equals(r)) {
                model.addAttribute("usernameError", "Пользователь с таким именем существует");
                return "registration";
            }
        }

        /*if (!usersService.addNewUserWithMember(users, members)) {
            model.addAttribute("usernameError", "Пользователь с таким именем существует");
            return "registration";
        }*/

        return "redirect:/tournament/registration";
    }
}
