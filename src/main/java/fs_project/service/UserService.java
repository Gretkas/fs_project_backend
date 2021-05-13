package fs_project.service;

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

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return null;
    }

    public UserResponseModel getUser(long id) {
        return UserResponseModel.convert(userRepo.getUser(id));
    }

    public UserResponseModel createUser(UserRequestModel userRequestModel) throws BadHttpRequest {
        //første bør vi sjekke om den finnes i db fra før av?

        User user = userRequestModel.convert();

        userRepo.save(user);

        UserResponseModel userResponseModel = new UserResponseModel(userRequestModel.getUserName());
        return userResponseModel;
    }

    public User getThisUser() throws BadHttpRequest {
        return null;

    }

    public Set<UserResponseModel> getUsers() {
        Set<User> users = userRepo.getUsers();
        return users.stream().map(UserResponseModel::convert).collect(Collectors.toSet());
    }

    public UserResponseModel changeUser(UserRequestModel userRequestModel, long id) throws Exception {
        User newUser = userRequestModel.convert();
        User currentUser = userRepo.findById(id).orElse(null);
        if(currentUser == null) throw new Exception("brukeren finnes ikke");

        //setter nye verdier på feltene
        currentUser.setName(newUser.getUsername());
        currentUser.setPassword(newUser.getPassword());
        currentUser.setAuthorities((List<GrantedAuthority>) newUser.getAuthorities());

        userRepo.save(currentUser);
    }
}
