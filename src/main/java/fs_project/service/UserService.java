package fs_project.service;

import fs_project.mapping.dto.users.CreateUserDto;
import fs_project.mapping.mappers.UserMapper;
import fs_project.model.dataEntity.Reservation;
import fs_project.model.dataEntity.User;
import fs_project.mapping.dto.users.UserRequestModel;
import fs_project.mapping.dto.users.UserResponseModel;
import fs_project.repo.ReservationRepo;
import fs_project.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * The type User service.
 */
@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ReservationRepo reservationRepo;

    /**
     * used by spring security to fetch users by username
     * @param username the username
     * @return the user, if found
     * @throws UsernameNotFoundException if the user was not found
     */
    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException  {
        User user = userRepo.getUserByName(username).orElse(null);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return user;
    }


    /**
     * Gets user by id.
     *
     * @param id the id
     * @return the user
     */
// todo NB! admin only!
    public CreateUserDto getUser(Long id) {
        @NotNull User user = userRepo.findUserById(id).orElse(null); // todo throw exception
        @Valid CreateUserDto getUserResponse = userMapper.userToCreateUser(user);
        return getUserResponse;
    }

    /**
     * Updates user.
     *
     * @param newUserData the new user data
     * @param id          the id
     * @return the user
     */
    public CreateUserDto updateUser(@NotNull @Valid CreateUserDto newUserData, @NotNull Long id) {
        @Valid User newUser = userMapper.createUserToUser(newUserData);
        newUser.setPassword(new BCryptPasswordEncoder().encode(newUser.getPassword()));
        @Valid @NotNull User currentUser = Optional.of(userRepo.findUserById(id).get()).orElse(null);
        if (currentUser != null) {
            newUser.setId(currentUser.getId());
        }
        @Valid @NotNull CreateUserDto createUserResponse = userMapper.userToCreateUser(userRepo.save(newUser));

        return createUserResponse;
    }

    /**
     * Creates user
     *
     * @param newUser the new user
     * @return the user
     */
    public CreateUserDto createUser(@NotNull @Valid CreateUserDto newUser) {
        User user = userMapper.createUserToUser(newUser);
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        userRepo.findUserByEmail(user.getEmail()).ifPresent(existingUser -> user.setId(existingUser.getId()));
        CreateUserDto createUserResponse = userMapper.userToCreateUser(userRepo.save(user));
        return createUserResponse;
    }

    /**
     * Gets the user which made the request using security context.
     *
     * @return  this user
     */
    public User getThisUser() {
        return loadUserByUsername(SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName());
    }

    /**
     * Gets users.
     *
     * @return the users
     */
    public List<UserResponseModel> getUsers() {
        List<User> users = userRepo.findAll();
        return users.stream().map(user -> userMapper.userToUserResponseModel(user)).collect(Collectors.toList());
    }

    /**
     * Changes user .
     *
     * @param userRequestModel the user request model
     * @param id               the id
     * @return the user response model
     * @throws Exception the exception
     */
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

    /**
     * Deletes user .
     *
     * @param id the id
     * @return the boolean
     */
    public boolean deleteUser(long id) {
        User user = userRepo.findUserById(id).get();
        Set<Reservation> reservations = reservationRepo.getReservationsByUser(user);
        reservationRepo.deleteAll(reservations);
        userRepo.deleteById(id);
        return true;
    }
}
