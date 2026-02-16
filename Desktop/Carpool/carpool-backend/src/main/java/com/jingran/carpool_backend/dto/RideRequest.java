package com.jingran.carpool_backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public class RideRequest {
    @NotBlank
    public String date;

    @NotBlank
    public String time;

    @NotBlank
    public String title;

    @NotBlank
    public String departure;

    @NotBlank
    public String destination;

    @Positive
    public Integer slot;

    public String brand;
    public String model;

    @NotBlank
    public String phone;

    public String details;
}
