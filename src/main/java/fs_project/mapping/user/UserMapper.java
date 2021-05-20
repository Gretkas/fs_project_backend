package fs_project.mapping.user;

import fs_project.mapping.dto.UserDescription;
import fs_project.mapping.dto.users.CreateUserDto;
import fs_project.model.dataEntity.User;
import lombok.NonNull;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import javax.validation.Valid;

@Mapper(
        /*
         * Policy for each unmapped target's field(s) in any of
         * the class's methods.
         */
        unmappedTargetPolicy = ReportingPolicy.WARN // todo change to ignore in production stage
)
public abstract class UserMapper {

    public abstract UserDescription userToUserDescription(User user);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "password", ignore = true)
    public abstract User userDescriptionToUser(UserDescription userDescription);

    @Mapping(target = "id", ignore = true)
    public abstract User createUserToUser(@NonNull @Valid CreateUserDto createUser);

    public abstract CreateUserDto userToCreateUser(@NonNull @Valid User user);
}
