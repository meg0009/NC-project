package com.chivapchichi.controller;

import com.chivapchichi.model.RecordInfo;
import com.chivapchichi.repository.RecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("rest/records")
public class RecordRestController {

    @Autowired
    private RecordRepository repository;

    @GetMapping("/get-records")
    public List<RecordInfo> getAll() {
        return repository.findAllWithInfo();
    }
}
