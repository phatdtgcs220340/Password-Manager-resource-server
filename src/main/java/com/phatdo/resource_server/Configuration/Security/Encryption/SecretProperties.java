package com.phatdo.resource_server.Configuration.Security.Encryption;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "encrypt")
@Data
public class SecretProperties {
    private String passphrase;
    private String salt;
}
