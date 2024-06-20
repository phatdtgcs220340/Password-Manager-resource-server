package com.phatdo.resourceserver.models;

import com.phatdo.resourceserver.classsify.ApplicationType;
import com.phatdo.resourceserver.dto.response.ApplicationDTO;
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

    public ApplicationDTO toDTO() {
        return new ApplicationDTO(id, applicationName, type);
    }
}
