package com.netcracker.airlines.repository;

import com.netcracker.airlines.entities.Flight;
import com.netcracker.airlines.entities.Ticket;
import com.netcracker.airlines.entities.enums.Status;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FlightSpecificationsUtils {

    public static Specification<Flight> flightDepartureCountry(String country) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(root.get("departure").get("country"), country + "%");

    }

    public static Specification<Flight> flightDestinationCountry(String country) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(root.get("destination").get("country"), country + "%");
    }

    public static Specification<Flight> flightDepartureCity(String city) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(root.get("departure").get("city"), city + "%");

    }

    public static Specification<Flight> flightDestinationCity(String city) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(root.get("destination").get("city"), city + "%");
    }

    public static Specification<Flight> flightMinCost(Integer cost) {
        return (Specification<Flight>) (root, query, cb) -> cb.lessThan(root.get("ticket").get("cost"), cost);
    }

    public static Specification<Flight> flightDate(LocalDate date) {
        return (Specification<Flight>) (root, query, cb) -> {
            Path<LocalDateTime> flightDate = root.get("timeDeparture");
            return cb.and(
                    cb.equal(cb.function("YEAR", Integer.class, flightDate), date.getYear()),
                    cb.equal(cb.function("MONTH", Integer.class, flightDate), date.getMonth().getValue()),
                    cb.equal(cb.function("DAY", Integer.class, flightDate), date.getDayOfMonth()));
        };
    }

    public static Specification<Flight> flightStatus(Status status) {
        return (Specification<Flight>) (root, query, cb) -> cb.equal(root.get("status"), status);
    }
}
