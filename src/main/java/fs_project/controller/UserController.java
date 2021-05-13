package fs_project.controller;

import fs_project.model.requestModel.UserRequestModel;
import fs_project.model.responseModel.UserResponseModel;
import fs_project.service.UserService;
import javassist.tools.web.BadHttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value="/users/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<UserResponseModel> getUser(@PathVariable long id){
        return ResponseEntity.ok(userService.getUser(id));
    }

    @PostMapping("/users")
    public ResponseEntity<UserResponseModel> createUser(@RequestBody UserRequestModel userRequestModel) throws BadHttpRequest {
        return ResponseEntity.ok(userService.createUser(userRequestModel));
    }

    @GetMapping(value="/users", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<UserResponseModel>> getUsers(){
        return ResponseEntity.ok(userService.getUsers());
    }

    @PutMapping(value="/users/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> changeUser(@RequestBody UserRequestModel userRequestModel, @PathVariable long id){
        return ResponseEntity.ok(userService.changeUser(userRequestModel,id));
    }



}
