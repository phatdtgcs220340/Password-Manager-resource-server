package com.phatdo.resource_server.Document.Account;

import com.phatdo.resource_server.Document.Application.Application;
import com.phatdo.resource_server.Document.User.User;
import com.phatdo.resource_server.Exception.CustomError;
import com.phatdo.resource_server.Exception.CustomException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.List;

@Slf4j
@Service
public class AccountService {
    private final AccountRepository repo;

    @Autowired
    public AccountService(AccountRepository repo) {
        this.repo = repo;
    }

    public Account saveAccount(User user, Application application,
            String username, String password) throws CustomException {
        if (repo.findByUserAndApplication(user, application).isEmpty()) {
            ZonedDateTime now = ZonedDateTime.now(ZoneOffset.UTC);
            Account account = new Account(username, password);
            account.setCreatedAt(now);
            account.setUpdatedAt(now);
            account.setApplication(application);
            account.setUser(user);
            account.setApplication(application);
            log.info("Attempt to save new account:{} - {}", account.getUsername(), account.getApplication());
            return repo.save(account);
        } else {
            log.error("User {} already exists", username);
            throw new CustomException(CustomError.ACCOUNT_IS_EXISTED);
        }
    }

    public Account getAccount(User user, Application application) throws CustomException {
        return repo.findByUserAndApplication(user, application)
                .orElseThrow(() -> new CustomException(CustomError.ACCOUNT_NOT_FOUND));
    }

    public List<Account> getAccountList(User user) {
        return repo.findByUser(user);
    }
}
