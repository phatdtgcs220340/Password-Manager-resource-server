package com.phatdo.resource_server.Controller.dto;

import com.phatdo.resource_server.Document.Application.ApplicationType;

public record AccountDTO(String id, String username, String password, String applicationName, ApplicationType type,
        String dateAudit) {
}
