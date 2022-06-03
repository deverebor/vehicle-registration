public class User {
    private String userName;
    private String userPhone;
    
    public User(){}
    
    public User(String newUserName, String newUserPhone) {
        setUserName(newUserName);
        setUserPhone(newUserPhone);
    }
    
    public String getUserName() {
        return userName;
    }
    
    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    public String getUserPhone() {
        return userPhone;
    }
    
    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }
}
