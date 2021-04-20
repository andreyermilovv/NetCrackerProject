package com.netcracker.airlines.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AirplaneDto {

    @NotBlank(message = "Name can't be empty")
    @Size(min = 3, message = "Name is too short")
    private String name;

    @NotNull(message = "economic can't be empty")
    private Integer economic;

    @NotNull(message = "busyness can't be empty")
    private Integer business;

    @NotNull(message = "first can't be empty")
    private Integer first;
}