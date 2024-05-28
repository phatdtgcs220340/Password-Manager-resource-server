package com.phatdo.resource_server.Document.User;

import java.time.ZonedDateTime;
import org.springframework.data.annotation.Id;

import lombok.Data;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/***
 * User class entity that fetch from the access token
 */
@Data
@Document(collection = "user")
public class User {
    @Id
    private String id;
    private final String fullName;
    @Indexed(unique = true)
    private final String username;
    private ZonedDateTime createdAt;

    public String toString() {
        return String.format("User: %s-%s", username, fullName);
    }

    public boolean equals(User user) {
        return this.id.equals(user.getId()) &&
                this.username.equals(user.getUsername()) &&
                this.fullName.equals(user.getFullName()) &&
                this.createdAt.equals(user.getCreatedAt());
    }
}
