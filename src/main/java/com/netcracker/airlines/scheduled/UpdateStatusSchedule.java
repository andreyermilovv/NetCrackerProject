package com.netcracker.airlines.scheduled;

import com.netcracker.airlines.service.FlightService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UpdateStatusSchedule {

    private final FlightService flightService;

    @Scheduled(cron = "0 0 * * * *")
    public void updateStatus(){
        flightService.updateStatus();
    }
}
