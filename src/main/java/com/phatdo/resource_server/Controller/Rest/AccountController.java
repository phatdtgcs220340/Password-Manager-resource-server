package com.phatdo.resource_server.Controller.Rest;

import com.phatdo.resource_server.Controller.dto.CreateAccountDTO;
import com.phatdo.resource_server.Document.Account.Account;
import com.phatdo.resource_server.Document.Account.AccountService;
import com.phatdo.resource_server.Document.Application.Application;
import com.phatdo.resource_server.Document.Application.ApplicationService;
import com.phatdo.resource_server.Document.User.User;
import com.phatdo.resource_server.Document.User.UserService;
import com.phatdo.resource_server.Exception.CustomException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(path = "/api/v1/account", produces = "application/json")
public class AccountController {
    private final AccountService accountService;
    private final ApplicationService applicationService;
    private final UserService userService;

    @Autowired
    public AccountController(AccountService accountService,
            ApplicationService applicationService,
            UserService userService) {
        this.accountService = accountService;
        this.applicationService = applicationService;
        this.userService = userService;
    }

    @GetMapping()
    public ResponseEntity<List<Account>> getAccounts(JwtAuthenticationToken authentication) {
        String fullName = authentication.getTokenAttributes().get("fullName").toString();
        User u = userService.processOAuth2PostLogin(authentication.getName(), fullName);
        return ResponseEntity.ok(accountService.getAccountList(u));
    }

    @PostMapping
    public ResponseEntity<Account> saveAccount(@RequestBody CreateAccountDTO form,
            JwtAuthenticationToken authentication) {
        try {
            String fullName = authentication.getTokenAttributes().get("fullName").toString();
            User u = userService.processOAuth2PostLogin(authentication.getName(), fullName);
            log.info("Username: {} ", u.getUsername());
            Application application = applicationService
                    .saveApplication(new Application(form.applicationName(), form.type()));

            Account account = accountService.saveAccount(u, application,
                    form.username(), form.password());
            return ResponseEntity.ok(account);
        } catch (CustomException e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatusCode.valueOf(400));
        }
    }

}
