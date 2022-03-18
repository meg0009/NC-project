package com.chivapchichi.controller;

import com.chivapchichi.model.AuthenticationRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Controller
public class LoginController {

    @Value("${jwt.header}")
    private String authorizationCookie;

    private final RestTemplate restTemplate;

    @Autowired
    public LoginController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String makeLogin(@ModelAttribute AuthenticationRequestDTO request, HttpServletResponse response) {
        ServletUriComponentsBuilder builder = ServletUriComponentsBuilder.fromCurrentContextPath();
        builder.path("/login-api/auth/login");

        //ResponseEntity<Map> post = restTemplate.postForEntity(builder.toUriString(), request, Map.class);

        //HttpHeaders headers = new HttpHeaders();
        String uri = builder.toUriString();
        ResponseEntity<Map> post = restTemplate.exchange(
                /*builder.toUriString()*/ uri,
                HttpMethod.POST,
                new HttpEntity<>(request),
                Map.class
        );

        Cookie jwtCookie = new Cookie(authorizationCookie, (String) post.getBody().get("token"));
        response.addCookie(jwtCookie);
        return "redirect:/tournament/registration/";
    }

    @PostMapping("/logout")
    public String logout(HttpServletResponse response) {
        Cookie delete = new Cookie(authorizationCookie, null);
        delete.setMaxAge(0);

        ServletUriComponentsBuilder builder = ServletUriComponentsBuilder.fromCurrentContextPath();
        builder.path("/login-api/auth/logout");

        restTemplate.postForObject(builder.toUriString(), null, Void.class);

        response.addCookie(delete);
        return "redirect:/tournament/registration/";
    }

    @Bean
    public static RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}
