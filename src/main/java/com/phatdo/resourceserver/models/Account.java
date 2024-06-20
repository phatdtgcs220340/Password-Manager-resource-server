package com.phatdo.resourceserver.models;

import com.phatdo.resourceserver.dto.response.AccountDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "account")
public class Account {

    @Id
    private String id;
    private final String username;
    private String password;
    private final ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;
    @DBRef
    private User user;
    @DBRef
    private Application application;

    public AccountDTO toDTO() {
        ZonedDateTime updatedAtInUTC7 = this.updatedAt.withZoneSameInstant(ZoneId.of("Asia/Ho_Chi_Minh"));
        String dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy - hh:mm a").format(updatedAtInUTC7);
        return new AccountDTO(
                id,
                username,
                password,
                application.getApplicationName(),
                application.getType(),
                dateFormatter);
    }
}
