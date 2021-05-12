package com.netcracker.airlines.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "profile")
@NoArgsConstructor
@AllArgsConstructor
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String name;

    private String surname;

    private String passportData;

    private LocalDate dateOfBirth;
        
    private boolean usePersonalData;

    private Integer money;

    public UserProfile(User user, String name, String surname) {
        this.user = user;
        this.name = name;
        this.surname = surname;
    }
}
