package com.phatdo.resource_server.dto.request;

import com.phatdo.resource_server.Document.Application.ApplicationType;

public record CreateAccountDTO (String username, String password, String applicationName, ApplicationType type) {
}
