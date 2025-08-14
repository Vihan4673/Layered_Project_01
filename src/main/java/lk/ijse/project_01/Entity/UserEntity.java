package lk.ijse.project_01.Entity;

public class UserEntity {
    private String userName;
    private String password;
    private String phoneNo;
    private String role;

    public UserEntity(String userName, String password, String phoneNo, String role) {
        this.userName = userName;
        this.password = password;
        this.phoneNo = phoneNo;
        this.role = role;
    }

    // Getters & setters
    public String getUserName() { return userName; }
    public String getPassword() { return password; }
    public String getPhoneNo() { return phoneNo; }
    public String getRole() { return role; }

    public void setUserName(String userName) { this.userName = userName; }
    public void setPassword(String password) { this.password = password; }
    public void setPhoneNo(String phoneNo) { this.phoneNo = phoneNo; }
    public void setRole(String role) { this.role = role; }
}
