package com.phatdo.resource_server.dto.response;

import com.phatdo.resource_server.Document.Application.ApplicationType;

public record ApplicationDTO(String id, String applicationName, ApplicationType type) {
}
