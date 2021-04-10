package com.netcracker.airlines.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TicketEditDto {

    private Integer seats;

    private Integer cost;
}
