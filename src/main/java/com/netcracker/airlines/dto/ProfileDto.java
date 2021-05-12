package com.netcracker.airlines.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
public class ProfileDto {

    @NotBlank(message = "passport data can't be blank")
    private String passportData;

    @NotBlank(message = "Date of birth can't be null")
    private String dateOfBirth;

    private Boolean usePersonalData;
}
