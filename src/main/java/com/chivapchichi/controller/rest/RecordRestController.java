package com.chivapchichi.controller.rest;

import com.chivapchichi.model.Record;
import com.chivapchichi.repository.RecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("rest/records")
public class RecordRestController {

    @Autowired
    private RecordRepository repository;

    @GetMapping("/get-records")
    public List<Record> getAll() {
        return repository.findAll();
    }

    @PostMapping("/add-record")
    public int addRecord(@RequestBody Record record) {
        return repository.saveRecord(record.getMember(), record.getTournament());
    }
}
