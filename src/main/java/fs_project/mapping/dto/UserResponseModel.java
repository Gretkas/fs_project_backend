package fs_project.mapping.dto;

import fs_project.model.dataEntity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;


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


    public UserResponseModel(User user) {
        this.userName = user.getUserName();
        this.role = user.getRole();
        this.id = user.getId();
    }


}
