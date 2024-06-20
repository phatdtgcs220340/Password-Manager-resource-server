package com.phatdo.resourceserver.repositories;

import com.phatdo.resourceserver.models.Application;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ApplicationRepository extends MongoRepository<Application, String> {
    Optional<Application> findByApplicationName(String applicationName);
}
