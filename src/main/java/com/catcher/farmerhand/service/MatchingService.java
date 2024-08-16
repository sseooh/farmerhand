package com.catcher.farmerhand.service;

import com.catcher.farmerhand.domain.*;
import com.catcher.farmerhand.repository.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MatchingService {

    private final MentorRepository mentorRepository;
    private final MenteeRepository menteeRepository;
    private final MatchingRepository matchingRepository;

    public MatchingService(MentorRepository mentorRepository, MenteeRepository menteeRepository, MatchingRepository matchingRepository) {
        this.mentorRepository = mentorRepository;
        this.menteeRepository = menteeRepository;
        this.matchingRepository = matchingRepository;
    }

    public List<Mentor> findAllMentors() {
        return mentorRepository.findAll();
    }

    public Mentor addMentor(Mentor mentor) {
        return mentorRepository.save(mentor);
    }

    public Matching requestMatching(Long mentorId, Mentee mentee) {
        Mentor mentor = mentorRepository.findById(mentorId).orElseThrow(() -> new IllegalArgumentException("Invalid mentor ID"));
        mentee = menteeRepository.save(mentee);
        Matching matching = new Matching(mentor, mentee);
        return matchingRepository.save(matching);
    }

    public List<Matching> findPendingRequests(Long mentorId) {
        return matchingRepository.findByMentorIdAndAcceptedFalse(mentorId);
    }

    public void acceptRequest(Long matchingId) {
        Matching matching = matchingRepository.findById(matchingId).orElseThrow(() -> new IllegalArgumentException("Invalid matching ID"));
        matching.setAccepted(true);
        matchingRepository.save(matching);
    }
}
