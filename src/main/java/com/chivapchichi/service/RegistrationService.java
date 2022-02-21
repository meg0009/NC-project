package com.chivapchichi.service;

import com.chivapchichi.model.Members;
import com.chivapchichi.model.Record;
import com.chivapchichi.model.Users;
import com.chivapchichi.repository.MembersRepository;
import com.chivapchichi.repository.RecordRepository;
import com.chivapchichi.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {

    @Autowired
    MembersRepository membersRepository;

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    RecordRepository recordRepository;

    @Autowired
    UsersService usersService;

    public Record register(Integer tournament, String userName, String fio) throws Exception {
        if (!usersRepository.findById(userName).isPresent()) {
            Users user = new Users();
            user.setUserName(userName);
            // переделать
            user.setPassword("123");
            user.setRole("ROLE_USER");
            usersService.addNewUser(usersRepository, user);

            Members member = new Members();
            member.setUserName(userName);
            member.setFio(fio);
            membersRepository.save(member);
        }

        recordRepository.saveRecord(userName, tournament);
        return recordRepository.findByUserNameAndTournament(userName, tournament)
                .orElseThrow(() -> new Exception("Not found"));
    }
}
