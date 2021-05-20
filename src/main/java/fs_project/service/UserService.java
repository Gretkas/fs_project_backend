package fs_project.service;

import fs_project.mapping.dto.users.CreateUserDto;
import fs_project.mapping.user.UserMapper;
import fs_project.model.dataEntity.User;
import fs_project.model.requestModel.UserRequestModel;
import fs_project.model.responseModel.UserResponseModel;
import fs_project.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import fs_project.model.responseModel.UserResponseModel;
import javassist.tools.web.BadHttpRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private UserMapper userMapper;

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException  {
        User user = userRepo.getUserByName(username).orElse(null);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return user;
    }

    public UserResponseModel getUser(long id) {
        return new UserResponseModel(userRepo.getOne(id));
    }

    public CreateUserDto createUser(@NotNull @Valid CreateUserDto newUser) throws BadHttpRequest {
        User user = userMapper.createUserToUser(newUser);
        userRepo.findUserByEmail(user.getEmail()).ifPresent(existingUser -> user.setId(existingUser.getId()));
        CreateUserDto createUserResponse = userMapper.userToCreateUser(userRepo.save(user));

        return createUserResponse;
//
//        if(userRepo.getUserByName(userRequestModel.getUserName()).orElse(null) != null) throw new BadHttpRequest(new Exception("User already exits"));
//
//        User user = userRequestModel.convert();
//        userRepo.save(user);
//
//        UserResponseModel userResponseModel = new UserResponseModel(user);
//        return userResponseModel;
    }

    public User getThisUser() {
        return loadUserByUsername(SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName());
    }

    public Set<UserResponseModel> getUsers() {
        Set<User> users = (Set<User>) userRepo.findAll();
        return users.stream().map(UserResponseModel::new).collect(Collectors.toSet());
    }

    public UserResponseModel changeUser(UserRequestModel userRequestModel, long id) throws Exception {
        User newUser = userRequestModel.convert();
        User currentUser = userRepo.findById(id).orElse(null);
        if(currentUser == null) throw new Exception("brukeren finnes ikke");

        //setter nye verdier på feltene
        currentUser.setName(newUser.getUsername());
        currentUser.setPassword(newUser.getPassword());
        currentUser.setRole(newUser.getRole());

        userRepo.save(currentUser);
        return new UserResponseModel(currentUser);
    }
}
