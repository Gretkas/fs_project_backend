package fs_project.mapping.user;

import fs_project.mapping.dto.UserDescription;
import fs_project.model.dataEntity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

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
}
