package com.phatdo.resource_server.Controller.Rest;

import com.phatdo.resource_server.Controller.dto.AccountDTO;
import com.phatdo.resource_server.CustomContext.UserContext.UserContext;
import com.phatdo.resource_server.Document.Account.Account;
import com.phatdo.resource_server.Document.Account.AccountService;
import com.phatdo.resource_server.Document.Application.Application;
import com.phatdo.resource_server.Document.Application.ApplicationService;
import com.phatdo.resource_server.Document.User.User;
import com.phatdo.resource_server.Exception.CustomException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequestMapping(path = "/api/v1/account", produces = "application/json")
public class AccountController {
    private final AccountService accountService;
    private final ApplicationService applicationService;

    @Autowired
    public AccountController(AccountService accountService,
                             ApplicationService applicationService) {
        this.accountService = accountService;
        this.applicationService = applicationService;
    }

    @GetMapping()
    public ResponseEntity<AccountDTO> getAccountById(@RequestParam(name = "applicationId") String application) {
        try {
            User u = UserContext.getUser();
            Account account = accountService.getAccount(u.getId(), application);
            return ResponseEntity.ok(account.toDTO());
        } catch (CustomException e) {
            return new ResponseEntity<>(e.getError().getCode());
        }
    }

    @PostMapping
    public ResponseEntity<AccountDTO> saveAccount(@RequestBody AccountDTO form){
        try {
            User u = UserContext.getUser();
            log.info("Username: {} ", u.getUsername());
            Application application = applicationService
                    .saveApplication(new Application(form.applicationName(), form.type()));

            Account account = accountService.saveAccount(u, application,
                    form.username(), form.password());
            return ResponseEntity.ok(account.toDTO());
        } catch (CustomException e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(e.getError().getCode());
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatusCode.valueOf(500));
        }
    }
    @PatchMapping
    public ResponseEntity<AccountDTO> updateAccount(@RequestBody AccountDTO form){
        try {
            User user = UserContext.getUser();
            Account account = accountService.updateAccount(form.id(), form.password(), user);
            return ResponseEntity.ok(account.toDTO());
        }
        catch (CustomException e) {
            return new ResponseEntity<>(e.getError().getCode());
        }
    }
    @DeleteMapping
    public ResponseEntity<String> deleteAccount(@RequestParam(name = "accountId") String id) {
        try {
            User user = UserContext.getUser();
            accountService.deleteAccount(id, user);
            return ResponseEntity.noContent().build();
        }
        catch (CustomException e) {
            return new ResponseEntity<>(e.getError().getCode());
        }
    }
}
