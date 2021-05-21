package fs_project.mapping.dto.users;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class UserDescription {

    @NotNull
    @NotBlank
    private String userName;

    @NotNull
    @NotBlank
    private String role;
}
