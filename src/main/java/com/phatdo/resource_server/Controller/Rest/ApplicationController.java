package com.phatdo.resource_server.Controller.Rest;

import com.phatdo.resource_server.CustomContext.UserContext.UserContext;
import com.phatdo.resource_server.Document.Account.AccountService;
import com.phatdo.resource_server.Document.Application.Application;
import com.phatdo.resource_server.Document.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/application", produces = "application/json")
public class ApplicationController {
    private final AccountService accountService;
    @Autowired
    public ApplicationController(AccountService accountService) {
        this.accountService = accountService;
    }
    @GetMapping()
    public ResponseEntity<List<Application>> getUserApplications(){
        User user = UserContext.getUser();
        List<Application> applications =
                    accountService.getApplications(user);
        return ResponseEntity.ok(applications);
    }
}
