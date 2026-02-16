package com.jingran.carpool_backend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "rides", uniqueConstraints = @UniqueConstraint(name = "uk_ride_user_date_time", columnNames = {
        "username", "date", "time" }))
public class Ride {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    private String brand;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String date;

    @Column(nullable = false)
    private String time;

    @Column(nullable = false)
    private String departure;

    @Column(nullable = false)
    private String destination;

    @Column(nullable = false)
    private int slot;

    @Column(nullable = false)
    private String phone;

    @Column(length = 2000)
    private String details;
}
