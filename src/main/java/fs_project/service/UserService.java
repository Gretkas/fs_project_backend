package fs_project.service;

import fs_project.model.dataEntity.User;
import fs_project.model.requestModel.UserRequestModel;
import fs_project.model.responseModel.UserResponseModel;
import fs_project.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
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

import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return null;
    }

    public UserResponseModel getUser(long id) {

        return null;
    }

    public UserResponseModel createUser(UserRequestModel userRequestModel) {
        //første bør vi sjekke om den finnes i db fra før av?

        User user = new User();
        user.setName(userRequestModel.getUserName());
        user.setPassword(new BCryptPasswordEncoder().encode(userRequestModel.getPassword()));

        userRepo.save(user);

        UserResponseModel userResponseModel = new UserResponseModel(userRequestModel.getUserName());
        return userResponseModel;
    }

    public User getThisUser() throws BadHttpRequest {
        return null;

    }

}
