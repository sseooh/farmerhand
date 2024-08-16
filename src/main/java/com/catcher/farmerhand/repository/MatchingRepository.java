package com.catcher.farmerhand.repository;

import com.catcher.farmerhand.domain.Matching;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MatchingRepository extends JpaRepository<Matching, Long> {
    List<Matching> findByMentorIdAndAcceptedFalse(Long mentorId);
}