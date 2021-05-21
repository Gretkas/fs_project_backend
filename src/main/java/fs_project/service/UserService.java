package fs_project.service;

import fs_project.mapping.dto.users.CreateUserDto;
import fs_project.mapping.user.UserMapper;
import fs_project.model.dataEntity.Reservation;
import fs_project.model.dataEntity.User;
import fs_project.mapping.dto.UserRequestModel;
import fs_project.mapping.dto.UserResponseModel;
import fs_project.repo.ReservationRepo;
import fs_project.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import javassist.tools.web.BadHttpRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

    @Autowired
    private ReservationRepo reservationRepo;

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException  {
        User user = userRepo.getUserByName(username).orElse(null);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return user;
    }


    // todo NB! admin only!
    public CreateUserDto getUser(Long id) {
        @NotNull User user = userRepo.findUserById(id).orElse(null); // todo throw exception
        @Valid CreateUserDto getUserResponse = userMapper.userToCreateUser(user);
        return getUserResponse;
    }

    public CreateUserDto updateUser(@NotNull @Valid CreateUserDto newUserData, @NotNull Long id) {
        @Valid User newUser = userMapper.createUserToUser(newUserData);
        @Valid @NotNull User currentUser = Optional.of(userRepo.findUserById(id).get()).orElse(null);
        if (currentUser != null) {
            newUser.setId(currentUser.getId());
        }
        @Valid @NotNull CreateUserDto createUserResponse = userMapper.userToCreateUser(userRepo.save(newUser));

        return createUserResponse;
    }

    public CreateUserDto createUser(@NotNull @Valid CreateUserDto newUser) throws BadHttpRequest {
        User user = userMapper.createUserToUser(newUser);
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        userRepo.findUserByEmail(user.getEmail()).ifPresent(existingUser -> user.setId(existingUser.getId()));
        CreateUserDto createUserResponse = userMapper.userToCreateUser(userRepo.save(user));
        return createUserResponse;
    }

    public User getThisUser() {
        return loadUserByUsername(SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName());
    }

    public List<UserResponseModel> getUsers() {
        List<User> users = userRepo.findAll();
        return users.stream().map(user -> userMapper.userToUserResponseModel(user)).collect(Collectors.toList());
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
        return userMapper.userToUserResponseModel(currentUser);
    }

    public boolean deleteUser(long id) {
        User user = userRepo.findUserById(id).get();
        Set<Reservation> reservations = reservationRepo.getReservationsByUser(user);
        reservationRepo.deleteAll(reservations);
        userRepo.deleteById(id);
        return true;
    }
}
