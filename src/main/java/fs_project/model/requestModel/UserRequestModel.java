package fs_project.model.requestModel;

import fs_project.model.dataEntity.User;
import javassist.tools.web.BadHttpRequest;

public class UserRequestModel {
    private String userName;
    private String password;
    private String role;

    public UserRequestModel() {}

    public User convert() throws BadHttpRequest {
        validate();
        return new User(userName,password,role);
    }

    private void validate() throws BadHttpRequest {
        if(userName == null || userName.isBlank() || password == null || password.isBlank() || role == null || role.isBlank()) throw new BadHttpRequest(new Exception("Invalid information"));
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

}
