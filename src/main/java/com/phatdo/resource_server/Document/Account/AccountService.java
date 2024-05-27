package com.phatdo.resource_server.Document.Account;

import com.phatdo.resource_server.Configuration.Security.Encryption.EncryptionService;
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
    private final EncryptionService encryptionService;
    public AccountService(AccountRepository repo, EncryptionService encryptionService) {
        this.repo = repo;
        this.encryptionService = encryptionService;
    }

    public Account saveAccount(User user, Application application,
            String username, String password) throws Exception {
        if (repo.findByUserIdAndApplicationId(user.getId(), application.getId()).isEmpty()) {
            ZonedDateTime now = ZonedDateTime.now(ZoneOffset.UTC);
            Account account = new Account(username, now);
            account.setPassword(encryptionService.encrypt(password));
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

    public Account getAccount(String userId, String applicationId) throws Exception {
        return repo.findByUserIdAndApplicationId(userId, applicationId)
                .map( (account) -> {
                    try {
                        log.info(account.getPassword());
                        account.setPassword(encryptionService.decrypt(account.getPassword()));
                        return account;
                    }
                    catch (Exception e) {
                        log.error(e.getMessage());
                        return account;
                    }
                }
                )
                .orElseThrow(() -> new CustomException(CustomError.ACCOUNT_NOT_FOUND));
    }

    public List<Account> getAccountList(User user) throws Exception {
        return repo.findByUser(user).stream().map(account -> {
            try {
                account.setPassword(encryptionService.decrypt(account.getPassword()));
                return account;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }).toList();
    }
}
