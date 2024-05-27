package com.phatdo.resource_server.Document.Account;

import com.phatdo.resource_server.Document.Application.Application;
import com.phatdo.resource_server.Document.User.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;
import java.util.List;

public interface AccountRepository extends MongoRepository<Account, String> {
    Optional<Account> findByUserAndApplication(User user, Application application);

    List<Account> findByUser(User user);
}
