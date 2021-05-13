package fs_project.model.requestModel;

import fs_project.model.dataEntity.User;
import javassist.tools.web.BadHttpRequest;

public class UserRequestModel {
    private String userName;
    private String password;

    public UserRequestModel() {}

    public User convert() throws BadHttpRequest {
        validate();
        return new User(userName,password);
    }

    private void validate() throws BadHttpRequest {
        if(userName == null || userName.isBlank() || password == null || password.isBlank()) throw new BadHttpRequest(new Exception("Unvalid information"));
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
}
