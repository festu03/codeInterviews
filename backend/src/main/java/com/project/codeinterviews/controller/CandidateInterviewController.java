package com.project.codeinterviews.controller;

import com.project.codeinterviews.service.InterviewService;
import com.project.codeinterviews.entity.GuestUserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/candidate/interview")
public class CandidateInterviewController {
    @Autowired
    private InterviewService interviewService;

    @GetMapping()
    public ResponseEntity<?> getAssignedInterview(@AuthenticationPrincipal GuestUserPrincipal principal) {
        return interviewService.getAssignedInterview(principal);
    }
}
