package fs_project.service;

import fs_project.model.dataEntity.User;
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
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return null;
    }

    public UserResponseModel getUser(long id) {

        return null;
    }


    public User getThisUser() throws BadHttpRequest {
        return null;

    }

}
