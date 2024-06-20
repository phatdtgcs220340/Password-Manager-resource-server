package com.phatdo.resourceserver.dto.request;

import com.phatdo.resourceserver.classsify.ApplicationType;

public record CreateAccountDTO (String username, String password, String applicationName, ApplicationType type) {
}
