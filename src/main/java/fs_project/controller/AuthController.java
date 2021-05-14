package fs_project.controller;



import fs_project.model.dataEntity.User;
import fs_project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


import java.security.Principal;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
public class AuthController {

    @Autowired
    private UserService userService;

    /**
     * Invoked by user to check if an active session cookie is present.
     * @param principal The principle of this session. Contains key-information, such as the username, i.e. email.
     * @return The current user which extends "UserDetails".
     */

    @GetMapping(value = "/auth", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<User> authenticate(Principal principal){
        return ResponseEntity.ok(userService.loadUserByUsername(principal.getName()));
    }
}

