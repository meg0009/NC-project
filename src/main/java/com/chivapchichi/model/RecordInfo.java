package com.chivapchichi.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class RecordInfo {

    @Id
    private int id;

    private String member;

    private String tournament;
}
