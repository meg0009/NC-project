package com.chivapchichi.service;

import com.chivapchichi.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Service
public class RestApiService {

    public static final String adminName = "ServerAdmin";
    public static final String adminPassword = "ServerAdmin";

    /*@Autowired
    private RestTemplate rest;*/

    private static final RestTemplate rest = new RestTemplate();

    public ResponseEntity<Person> addPerson(Person person) {
        URI reqUri = getRestUri();
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(adminName, adminPassword);
        return rest.exchange(
                reqUri,
                HttpMethod.POST,
                new HttpEntity<>(person, headers),
                Person.class
        );
    }

    public List<Person> getPersons() {
        URI reqUri = getRestUri();
        return rest.getForObject(reqUri, List.class);
    }

    private URI getRestUri() {
        ServletUriComponentsBuilder builder = ServletUriComponentsBuilder.fromCurrentContextPath();
        builder.path("/rest/persons");
        return builder.build().toUri();
    }

    /*@Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }*/
}
