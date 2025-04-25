package com.project.codeinterviews.entity;

import java.security.Principal;

public class GuestUserPrincipal implements Principal {
    private final String email;
    private final String token;

    public GuestUserPrincipal(String email, String token) {
        this.email = email;
        this.token = token;
    }

    @Override
    public String getName() {
        return email;
    }

    public String getToken() {
        return token;
    }
}
