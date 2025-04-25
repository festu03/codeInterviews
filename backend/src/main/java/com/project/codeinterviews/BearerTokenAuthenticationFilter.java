package com.project.codeinterviews;

import com.project.codeinterviews.entity.GuestUserPrincipal;
import com.project.codeinterviews.service.GuestUserPrincipalService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class BearerTokenAuthenticationFilter extends OncePerRequestFilter {
    private final GuestUserPrincipalService guestUserPrincipalService;

    public BearerTokenAuthenticationFilter(GuestUserPrincipalService guestUserPrincipalService) {
        this.guestUserPrincipalService = guestUserPrincipalService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("Authorization");
        if (token != null) {
            token = token.substring(7);
        }

        try {
            GuestUserPrincipal guestUserPrincipal = guestUserPrincipalService.findValidToken(token);
            AnonymousAuthenticationToken authentication = new AnonymousAuthenticationToken(
                    guestUserPrincipal.getName(),
                    guestUserPrincipal,
                    // Viene impostato il ruolo di "CANDIDATE"
                    AuthorityUtils.createAuthorityList("ROLE_CANDIDATE")
            );
            // Nel SecurityContext viene salvato l'oggetto di AnonymousAuthenticationToken
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
        filterChain.doFilter(request, response);
    }
}
