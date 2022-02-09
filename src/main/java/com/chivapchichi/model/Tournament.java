package com.chivapchichi.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Calendar;

@Entity
@Table(name = "tournament")
@Data
public class Tournament {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private Calendar date;

    @Column(name = "rating_range")
    private int ratingRange;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String phone;

    @Column(name = "organizer_name")
    private String organizerName;

    @Column(nullable = false)
    private double cost;

    @Column(nullable = false)
    private int max;
}
