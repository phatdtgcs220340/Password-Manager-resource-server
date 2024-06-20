package com.phatdo.resourceserver.dto.response;

import com.phatdo.resourceserver.classsify.ApplicationType;

public record ApplicationDTO(String id, String applicationName, ApplicationType type) {
}
