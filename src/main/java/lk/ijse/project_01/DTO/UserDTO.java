package lk.ijse.project_01.DTO;

public class UserDTO {
    private String userName;
    private String password;
    private String phoneNo;
    private String role;

    public UserDTO(String userName, String password, String phoneNo, String role) {
        this.userName = userName;
        this.password = password;
        this.phoneNo = phoneNo;
        this.role = role;
    }

    public String getUserName() { return userName; }
    public String getPassword() { return password; }
    public String getPhoneNo() { return phoneNo; }
    public String getRole() { return role; }
}
