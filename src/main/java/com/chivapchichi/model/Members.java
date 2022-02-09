package com.chivapchichi.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "members")
@Data
public class Members {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "user_name", nullable = false)
    private String userName;

    @Column(nullable = false)
    private String fio;
}
