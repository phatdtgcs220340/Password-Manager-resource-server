package com.phatdo.resourceserver.controllers;

import com.phatdo.resourceserver.authentication.UserContext;
import com.phatdo.resourceserver.services.AccountService;
import com.phatdo.resourceserver.models.Application;
import com.phatdo.resourceserver.models.User;
import com.phatdo.resourceserver.dto.response.ApplicationDTO;
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
    public ResponseEntity<List<ApplicationDTO>> getUserApplications(){
        User user = UserContext.getUser();
        List<ApplicationDTO> applicationsDto =
                    accountService.getApplications(user)
                            .stream()
                            .map(Application::toDTO)
                            .toList();
        return ResponseEntity.ok(applicationsDto);
    }
}
