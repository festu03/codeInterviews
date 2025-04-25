package com.project.codeinterviews.controller;

import com.project.codeinterviews.entity.GuestUserPrincipal;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/candidate")
public class GuestUserPrincipalController {

    @GetMapping("/user-info")
    public GuestUserPrincipal userInfo(@AuthenticationPrincipal GuestUserPrincipal principal) {
        return principal;
    }
}
