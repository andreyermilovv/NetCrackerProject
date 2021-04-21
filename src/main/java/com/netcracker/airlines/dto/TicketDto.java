package com.netcracker.airlines.dto;

import com.netcracker.airlines.entities.enums.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketDto {

    @NotNull(message = "Flight can't be null")
    private Long flight;

    @NotNull(message = "Category can't be null")
    private Category category;

    @NotNull(message = "Seats can't be null")
    private Integer seats;

    @NotNull(message = "Cost can't be null")
    private Integer cost;
}
