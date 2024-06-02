package com.phatdo.resource_server.Document.Application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ApplicationService {
    private final ApplicationRepository repo;

    @Autowired
    public ApplicationService(ApplicationRepository repo) {
        this.repo = repo;
    }

    public Application saveApplication(Application application) {
        Optional<Application> optApplication = repo.findByApplicationName(application.getApplicationName());
        return optApplication.orElseGet(() -> repo.save(application));
    }
}
