import Utils.UserException;

public class User {
    private String userName;
    private String userPhone;
    protected final String phoneRegex = "^\\([0-9]{2}\\)[0-9]{5}-[0-9]{4}$";
    
    public User(){}
    
    public User(String newUserName, String newUserPhone) throws UserException {
        if(newUserName == null || newUserPhone == null) {
            throw new UserException("O nome do usuário e o telefone são obrigatórios!");
        }else if (!Character.isUpperCase(newUserName.charAt(0))) {
            throw new UserException("O nome do usuário deve começar com uma letra maiúscula!");
        }else if (!newUserPhone.matches(phoneRegex)) {
            throw new UserException("O telefone deve conter o padrão (00)00000-0000");
        } else {
            setUserName(newUserName);
            setUserPhone(newUserPhone);
        }
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
