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

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;

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

    public UserResponseModel createUser(UserRequestModel userRequestModel) throws BadHttpRequest {
        if(userRepo.getUserByName(userRequestModel.getUserName()).orElse(null) != null) throw new BadHttpRequest(new Exception("User already exits"));

        User user = userRequestModel.convert();
        userRepo.save(user);

        UserResponseModel userResponseModel = new UserResponseModel(user);
        return userResponseModel;
    }

    public User getThisUser() {
        return loadUserByUsername(SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName());
    }

    public List<UserResponseModel> getUsers() {
        List<User> users = userRepo.findAll();
        return users.stream().map(UserResponseModel::new).collect(Collectors.toList());
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

    public boolean deleteUser(long id) {
        userRepo.deleteById(id);
        return true;
    }
}
