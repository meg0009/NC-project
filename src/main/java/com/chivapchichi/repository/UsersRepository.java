package com.chivapchichi.repository;

import com.chivapchichi.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersRepository extends JpaRepository<Users, String> {

    @Query(value = "select * from users where user_name=?1", nativeQuery = true)
    Users findByLogin(String login);

    @Query(value = "select user_name from users", nativeQuery = true)
    List<String> findAllLogins();
}
