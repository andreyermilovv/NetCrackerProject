package com.netcracker.airlines.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TicketEditDto {

    @NotNull(message = "Seats can't be null")
    private Integer seats;

    @NotNull(message = "Cost can't be null")
    private Integer cost;
}
