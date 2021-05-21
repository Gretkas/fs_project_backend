package fs_project.controller;
import fs_project.mapping.dto.users.UserResponseModel;
import fs_project.mapping.dto.users.CreateUserDto;
import fs_project.service.UserService;
import javassist.tools.web.BadHttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * The type User controller. Used for users.
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * Get user response entity.
     *
     * @param id the id
     * @return the response entity
     */
    @GetMapping(value="/users/{id}", produces = APPLICATION_JSON_VALUE)
    @Validated
    public ResponseEntity<CreateUserDto> getUser(@PathVariable @NotNull Long id){
        return ResponseEntity.ok(userService.getUser(id));
    }

    /**
     * Create user .
     *
     * @param newUser the new user
     * @return the response entity
     * @throws BadHttpRequest the bad http request
     */
    @PostMapping("/users")
    public ResponseEntity<CreateUserDto> createUser
            (@RequestBody @NotNull @Validated CreateUserDto newUser) throws BadHttpRequest {
        return ResponseEntity.ok(userService.createUser(newUser));
    }

    /**
     * Get users response entity.
     *
     * @return the response entity
     */
    @GetMapping(value="/users", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UserResponseModel>> getUsers(){
        return ResponseEntity.ok(userService.getUsers());
    }

    /**
     * Update user .
     *
     * @param userRequestModel the user request model
     * @param id               the id
     * @return the response entity
     * @throws Exception the exception
     */
    @PutMapping(value="/users/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<CreateUserDto> updateUser
            (@RequestBody @NotNull @Validated CreateUserDto userRequestModel,
             @PathVariable Long id) throws Exception {
        return ResponseEntity.ok(userService.updateUser(userRequestModel,id));
    }



    /**
     * Delete user.
     *
     * @param id the id
     * @return the response entity
     */
    @DeleteMapping(value="/users/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> deleteUser(@PathVariable long id){
        return ResponseEntity.ok(userService.deleteUser(id));
    }



}
