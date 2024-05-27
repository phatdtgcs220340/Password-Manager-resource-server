package com.phatdo.resource_server.Document.Application;

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
    private final String applicationName;
    private final ApplicationType type;
}
