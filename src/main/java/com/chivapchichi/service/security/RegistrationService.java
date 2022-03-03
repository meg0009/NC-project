package com.chivapchichi.service.security;

import com.chivapchichi.model.Members;
import com.chivapchichi.model.Record;
import com.chivapchichi.model.Users;
import com.chivapchichi.repository.MembersRepository;
import com.chivapchichi.repository.RecordRepository;
import com.chivapchichi.repository.UsersRepository;
import com.chivapchichi.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {

    private final MembersRepository membersRepository;

    private final UsersRepository usersRepository;

    private final RecordRepository recordRepository;

    private final UsersService usersService;

    @Autowired
    public RegistrationService(MembersRepository membersRepository, UsersRepository usersRepository, RecordRepository recordRepository, UsersService usersService) {
        this.membersRepository = membersRepository;
        this.usersRepository = usersRepository;
        this.recordRepository = recordRepository;
        this.usersService = usersService;
    }

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

        if (!recordRepository.findByUserNameAndTournament(userName, tournament).isPresent()) {
            recordRepository.saveRecord(userName, tournament);
        }
        return recordRepository.findByUserNameAndTournament(userName, tournament)
                .orElseThrow(() -> new Exception("Not found"));
    }
}
