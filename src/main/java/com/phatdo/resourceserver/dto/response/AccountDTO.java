package com.phatdo.resourceserver.dto.response;

import com.phatdo.resourceserver.classsify.ApplicationType;

public record AccountDTO(String id, String username, String password, String applicationName, ApplicationType type,
        String dateAudit) {
}
