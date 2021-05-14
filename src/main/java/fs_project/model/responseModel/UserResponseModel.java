package fs_project.model.responseModel;

import fs_project.model.dataEntity.User;

public class UserResponseModel {
    private String userName;
    private String role;

    public UserResponseModel(User user) {

        this.userName = user.getUserName();
        this.role = user.getRole();
    }



    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
