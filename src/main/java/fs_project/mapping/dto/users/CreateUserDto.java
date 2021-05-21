package fs_project.mapping.dto.users;

import fs_project.model.Attributes.UserRole;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.time.LocalDate;

/**
 * The type Create user dto. Used to create user. checks for field validity
 */
@Data
@NoArgsConstructor
public class CreateUserDto {

    @NotNull(message = "Username must be provided")
    @NotBlank(message = "Username must be provided")
    private String userName;

    @NotNull(message = "Password must be provided")
    @NotBlank(message = "Password must be provided")
    @Pattern(
            regexp = "((?=.*[a-z])(?=.*[0-9])(?=.*[A-Z]).{8,100})",
            message = "Password must contain one digit, one lowercase letter, one uppercase letter, and be at least 8 characters long"
    )
    private String password;

    @NotNull(message = "Role must be provided")
    private UserRole role;

    @NotNull(message = "First name must be provided")
    @NotBlank(message = "First name must be provided")
    private String firstName;

    @NotNull(message = "Last name must be provided")
    @NotBlank(message = "Last name must be provided")
    private String lastName;

    @Email(message = "Email should be valid")
    private String email;

    @Pattern(regexp="^\\d{8}$", message = "Phone number must contain 8 digits")
    private String phoneNumber;

    @NotNull(message = "Account expiration date must be provided")
    @Future(message = "Account expiration date must be in future")
    private LocalDate validUntil;
}
