package com.phatdo.resource_server.Configuration.OAuth2;

import com.phatdo.resource_server.Document.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class AuthenticationHandler {
    @Autowired
    private UserService userService;

    @EventListener
    public void onAuthenticationSuccess(AuthenticationSuccessEvent event) {
        JwtAuthenticationToken token = (JwtAuthenticationToken) event.getAuthentication();
        log.info("Attempt to save user: {} - {}", token.getName(),
                token.getTokenAttributes().get("fullName").toString());
        log.info("Event save user: {} ", userService.processOAuth2PostLogin(token.getName(),
                token.getTokenAttributes().get("fullName").toString()));
        ;
    }

}
