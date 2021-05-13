package fs_project.model.responseModel;

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
}
