package fs_project.controller;
import fs_project.mapping.dto.UserRequestModel;
import fs_project.mapping.dto.UserResponseModel;
import fs_project.mapping.dto.users.CreateUserDto;
import fs_project.model.dataEntity.Room;
import fs_project.service.UserService;
import javassist.tools.web.BadHttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    // todo admin only
    @GetMapping(value="/users/{id}", produces = APPLICATION_JSON_VALUE)
    @Validated
    public ResponseEntity<CreateUserDto> getUser(@PathVariable @NotNull Long id){
        return ResponseEntity.ok(userService.getUser(id));
    }

    @PostMapping("/users")
    public ResponseEntity<CreateUserDto> createUser
            (@RequestBody @NotNull @Validated CreateUserDto newUser) throws BadHttpRequest {
        return ResponseEntity.ok(userService.createUser(newUser));
    }

    @GetMapping(value="/users", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UserResponseModel>> getUsers(){
        return ResponseEntity.ok(userService.getUsers());
    }

    @PutMapping(value="/users/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<CreateUserDto> updateUser
            (@RequestBody @NotNull @Validated CreateUserDto userRequestModel,
             @PathVariable Long id) throws Exception {
        return ResponseEntity.ok(userService.updateUser(userRequestModel,id));
    }

//    @PutMapping(value="/users/{id}", produces = APPLICATION_JSON_VALUE)
//    public ResponseEntity<Object> changeUser(@RequestBody UserRequestModel userRequestModel, @PathVariable long id) throws Exception {
//        return ResponseEntity.ok(userService.changeUser(userRequestModel,id));
//    }

    @DeleteMapping(value="/users/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> deleteUser(@PathVariable long id){
        return ResponseEntity.ok(userService.deleteUser(id));
    }



}
