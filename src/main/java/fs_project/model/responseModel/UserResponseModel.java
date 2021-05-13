package fs_project.model.responseModel;

import fs_project.model.dataEntity.User;

public class UserResponseModel {
    private String userName;

    public UserResponseModel(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public static UserResponseModel convert(User user){
        return new UserResponseModel(user.getUsername());
    }
}
