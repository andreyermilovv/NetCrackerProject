package com.netcracker.airlines.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AirportDto {

    @NotBlank
    @Min(value = 3)
    private String name;

    @NotBlank
    private String city;

    @NotBlank
    private String country;
}
