package com.phatdo.resource_server.Controller.Rest;

import com.phatdo.resource_server.dto.response.UserDTO;
import com.phatdo.resource_server.CustomContext.UserContext.UserContext;
import com.phatdo.resource_server.Document.User.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/api/v1/user", produces = "application/json")
public class UserController {
    @GetMapping()
    public ResponseEntity<UserDTO> getUser() {
        User user = UserContext.getUser();
        return ResponseEntity.ok(user.toDTO());
    }
}
