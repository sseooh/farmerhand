package com.catcher.farmerhand.repository;

import com.catcher.farmerhand.domain.Mentee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenteeRepository extends JpaRepository<Mentee, Long> {
}