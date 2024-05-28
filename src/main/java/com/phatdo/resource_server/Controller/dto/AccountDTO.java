package com.phatdo.resource_server.Controller.dto;

import com.phatdo.resource_server.Document.Application.ApplicationType;

import java.time.ZonedDateTime;

public record AccountDTO(String id, String username, String password, String applicationName, ApplicationType type, String dateAudit) {
}
