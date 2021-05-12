package com.netcracker.airlines.repository;

import com.netcracker.airlines.entities.User;
import com.netcracker.airlines.entities.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserProfileRepo extends JpaRepository<UserProfile, Long> {

    UserProfile findByUser(User user);
}
