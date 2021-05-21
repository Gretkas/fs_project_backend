package fs_project.mapping.mappers;

import fs_project.mapping.dto.users.UserDescription;
import fs_project.mapping.dto.users.UserResponseModel;
import fs_project.mapping.dto.users.CreateUserDto;
import fs_project.model.dataEntity.User;
import lombok.NonNull;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import javax.validation.Valid;

/**
 * The type User mapper. Used to map the the user data-entity to the correct user dto
 */
@Mapper(
        /*
         * Policy for each unmapped target's field(s) in any of
         * the class's methods.
         */
        unmappedTargetPolicy = ReportingPolicy.WARN // todo change to ignore in production stage
)
public abstract class UserMapper {

    /**
     * User to user description user description.
     *
     * @param user the user
     * @return the user description
     */
    public abstract UserDescription userToUserDescription(User user);

    /**
     * User to user response model user response model.
     *
     * @param user the user
     * @return the user response model
     */
    public abstract UserResponseModel userToUserResponseModel(User user);

    /**
     * User description to user user.
     *
     * @param userDescription the user description
     * @return the user
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "password", ignore = true)
    public abstract User userDescriptionToUser(UserDescription userDescription);

    /**
     * Create user to user user.
     *
     * @param createUser the create user
     * @return the user
     */
    @Mapping(target = "id", ignore = true)
    public abstract User createUserToUser(@NonNull @Valid CreateUserDto createUser);

    /**
     * User to create user create user dto.
     *
     * @param user the user
     * @return the create user dto
     */
    public abstract CreateUserDto userToCreateUser(@NonNull @Valid User user);
}
