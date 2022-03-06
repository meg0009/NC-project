package com.chivapchichi.model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

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
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
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

    public Tournament() {
    }

    public Tournament(Calendar date, int ratingRange, String address, String phone, String organizerName, double cost, int max) {
        this.date = date;
        this.ratingRange = ratingRange;
        this.address = address;
        this.phone = phone;
        this.organizerName = organizerName;
        this.cost = cost;
        this.max = max;
    }
}
