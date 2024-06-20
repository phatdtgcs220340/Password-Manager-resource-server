package com.phatdo.resourceserver.services;

import com.phatdo.resourceserver.models.Application;
import com.phatdo.resourceserver.repositories.ApplicationRepository;
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
