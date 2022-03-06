package com.chivapchichi.controller.admin;

import com.chivapchichi.model.Record;
import com.chivapchichi.repository.RecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("admin-panel")
public class RecordController {

    private final RecordRepository recordRepository;

    @Autowired
    public RecordController(RecordRepository recordRepository) {
        this.recordRepository = recordRepository;
    }

    @GetMapping("/get-all-records")
    public String getAllRecords(Model model) {
        model.addAttribute("records", recordRepository.findAll());
        return "admin-panel/all-records";
    }

    @GetMapping("/get-records-by-tournament/{id}")
    public String getRecordByIdTournament(@PathVariable("id") Integer idTournament, Model model) {
        model.addAttribute("records", recordRepository.findByTournament(idTournament));
        return "admin-panel/all-records";
    }

    @GetMapping("/get-record/{id}")
    public String getRecordById(@PathVariable("id") Integer id, Model model) {
        Optional<Record> record = recordRepository.findById(id);
        model.addAttribute("isPresent", record.isPresent());
        if (record.isPresent()) {
            model.addAttribute("record", record.get());
        }
        return "admin-panel/record";
    }

    @PostMapping("/delete-record")
    public String deleteRecord(@ModelAttribute Record record) {
        recordRepository.deleteById(record.getId());
        return "success-registration";
    }
}
