package com.chivapchichi.repository;

import com.chivapchichi.model.Record;
import com.chivapchichi.model.RecordInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecordRepository extends JpaRepository<Record, Integer> {

    @Query(value = "select r.* from record as r, members as m, tournament as t where r.member = m.id and r.tournament = t.id;", nativeQuery = true)
    List<RecordInfo> findAllWithInfo();
}
