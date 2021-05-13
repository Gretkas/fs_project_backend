package fs_project.controller;

import fs_project.model.requestModel.UserRequestModel;
import fs_project.model.responseModel.UserResponseModel;
import fs_project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value="/users/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<UserResponseModel> getUser(@PathVariable long id){
        return ResponseEntity.ok(userService.getUser(id));
    }

    @PostMapping("/user")
    public ResponseEntity<UserResponseModel> createUser(@RequestBody UserRequestModel userRequestModel){
        return ResponseEntity.ok(userService.createUser(userRequestModel));
    }



}
