package fs_project.mapping.dto.users;

import fs_project.model.dataEntity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;


/**
 * The type User response model. Used to get information on a specific user.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseModel {

    @NotNull
    @NotBlank
    private String userName;

    @NotNull
    @NotBlank
    private String role;

    @NotNull
    @Positive
    private long id;

    @NotNull
    @NotBlank
    private String email;


    /**
     * Instantiates a new User response model.
     *
     * @param user the user
     */
    public UserResponseModel(User user) {
        this.userName = user.getUserName();
        this.role = user.getRole();
        this.id = user.getId();
        this.email = user.getEmail();
    }
}
