package com.phatdo.resource_server.Document.User;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;

@Slf4j
@Service
public class UserService {
    private final UserRepository repo;

    @Autowired
    public UserService(UserRepository repo) {
        this.repo = repo;
    }

    public User getUser(String username) throws UsernameNotFoundException {
        return repo.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Not found username " + username));
    }

    public User processOAuth2PostLogin(String fullName, String username) {
        try {
            return this.getUser(username);
        } catch (UsernameNotFoundException e) {
            User newUser = new User(fullName, username);
            newUser.setCreatedAt(ZonedDateTime.now(ZoneOffset.UTC));
            log.info("User successfully register!: {}", newUser.getUsername());
            return repo.save(newUser);
        }
    }
}