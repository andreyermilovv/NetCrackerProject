package com.netcracker.airlines.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AirportDto {

    @NotBlank(message = "Name can't be empty")
    @Size(min = 3, message = "Name is too short")
    private String name;

    @NotBlank(message = "City can't be empty")
    private String city;

    @NotBlank(message = "Country can't be empty")
    private String country;
}
