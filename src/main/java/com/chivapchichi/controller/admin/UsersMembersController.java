package com.chivapchichi.controller.admin;

import com.chivapchichi.model.Members;
import com.chivapchichi.model.Users;
import com.chivapchichi.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("admin-panel")
public class UsersMembersController {

    private final UsersService usersService;

    @Autowired
    public UsersMembersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping("/get-user")
    public String deleteUser(Model model) {
        model.addAttribute("users", usersService.getAllUsers().stream().filter((x) -> !x.getRole().equals("ROLE_ADMIN")).collect(Collectors.toList()));
        return "admin-panel/get-user";
    }

    @GetMapping("/change-user/{username}")
    public String changeUserId(@PathVariable("username") String username, Model model) {
        Optional<Users> user = usersService.getUserByUserName(username);
        if (user.isPresent()) {
            model.addAttribute("user", user.get());
            model.addAttribute("member", usersService.getMemberByUserName(username).orElse(new Members()));
            return "admin-panel/change-user-id";
        }
        return "admin-panel/user-error";
    }

    @PostMapping("/delete-user")
    public String deleteUser(@ModelAttribute Users user) {
        usersService.deleteUserWithMemberByUserName(user.getUserName());
        return "success-registration";
    }

    @PostMapping("/change-user")
    public String postChangeUser(@ModelAttribute Users user, @ModelAttribute Members member) {
        usersService.updateUserMember(user.getUserName(), user.getRole(), member.getFio());

        return "success-registration";
    }
}
