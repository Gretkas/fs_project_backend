package fs_project.mapping.dto.users;

import fs_project.model.dataEntity.User;
import javassist.tools.web.BadHttpRequest;

/**
 * The type User request model. Used it requests pertaining to users
 */
public class UserRequestModel {
    private String userName;
    private String password;
    private String role;

    /**
     * Instantiates a new User request model.
     */
    public UserRequestModel() {}

    /**
     * Convert user.
     *
     * @return the user
     * @throws BadHttpRequest the bad http request
     */
    public User convert() throws BadHttpRequest {
        validate();
        return new User(userName,password,role);
    }

    private void validate() throws BadHttpRequest {
        if(userName == null || userName.isBlank() || password == null || password.isBlank() || role == null || role.isBlank()) throw new BadHttpRequest(new Exception("Invalid information"));
    }

    /**
     * Gets user name.
     *
     * @return the user name
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Sets user name.
     *
     * @param userName the user name
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Gets password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets password.
     *
     * @param password the password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets role.
     *
     * @return the role
     */
    public String getRole() {
        return role;
    }

    /**
     * Sets role.
     *
     * @param role the role
     */
    public void setRole(String role) {
        this.role = role;
    }

}
