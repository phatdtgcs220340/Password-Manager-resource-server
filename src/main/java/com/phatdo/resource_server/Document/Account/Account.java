package com.phatdo.resource_server.Document.Account;

import com.phatdo.resource_server.Document.Application.Application;
import com.phatdo.resource_server.Document.User.User;
import lombok.Data;

import java.time.ZonedDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "account")
public class Account {

    @Id
    private String id;
    private final String username;
    private final String password;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;
    @DBRef
    private User user;
    @DBRef
    private Application application;

}
