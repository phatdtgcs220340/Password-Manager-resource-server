package com.phatdo.resource_server.Document.Application;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "application")
public class Application {
    @Id
    private String id;
    @Indexed(unique = true)
    @NotBlank(message = "Application name must not be blank")
    private final String applicationName;
    @NotNull(message = "Type must not be null")
    private final ApplicationType type;
}
