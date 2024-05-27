package com.phatdo.resource_server.Controller.RequestBody;

import com.phatdo.resource_server.Document.Application.ApplicationType;

public record CreateAccountForm(String username, String password, String applicationName, ApplicationType type) {
}
