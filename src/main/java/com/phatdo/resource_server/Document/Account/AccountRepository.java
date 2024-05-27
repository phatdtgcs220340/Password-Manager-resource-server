package com.phatdo.resource_server.Document.Account;

import com.phatdo.resource_server.Document.Application.Application;
import com.phatdo.resource_server.Document.User.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;
import java.util.List;

public interface AccountRepository extends MongoRepository<Account, String> {
    @Query("{ 'user.id': ?0, 'application.id': ?1 }")
    Optional<Account> findByUserIdAndApplicationId(String userId, String applicationId);
    List<Account> findByUser(User user);
}
