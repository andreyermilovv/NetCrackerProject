package com.netcracker.airlines.dto;

import com.netcracker.airlines.entities.enums.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketDto {

    private Long flight;

    private Category category;

    private Integer seats;

    private Integer cost;
}
