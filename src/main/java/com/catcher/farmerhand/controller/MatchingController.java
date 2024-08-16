package com.catcher.farmerhand.controller;

import com.catcher.farmerhand.domain.*;
import com.catcher.farmerhand.service.MatchingService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/mentor-matching")
public class MatchingController {

    private final MatchingService matchingService;

    public MatchingController(MatchingService matchingService) {
        this.matchingService = matchingService;
    }

    @GetMapping()
    public String showMentors(Model model) {
        model.addAttribute("mentors", matchingService.findAllMentors());
        return "mentor-list"; // mentor-list.html
    }

    @PostMapping("/add-mentor")
    public String addMentor(Mentor mentor) {
        matchingService.addMentor(mentor);
        return "redirect:/mentor-matching";
    }

    @PostMapping("/request-matching/{mentorId}")
    public String requestMatching(@PathVariable Long mentorId, Mentee mentee) {
        matchingService.requestMatching(mentorId, mentee);
        return "redirect:/mentor-matching";
    }

    @GetMapping("/requests/{mentorId}")
    public String viewRequests(@PathVariable Long mentorId, Model model) {
        model.addAttribute("requests", matchingService.findPendingRequests(mentorId));
        return "matching-requests"; // matching-requests.html
    }

    @PostMapping("/accept-request/{matchingId}")
    public String acceptRequest(@PathVariable Long matchingId) {
        matchingService.acceptRequest(matchingId);
        return "redirect:/mentor-matching";
    }
}
