import Utils.UserException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UserTest {
    
    @Test
    public void shouldCreateUser() {
        User user = new User();
        user.setUserName("Adeobaldo");
        user.setUserPhone("(00)00000-0000");
        assertEquals("Adeobaldo", user.getUserName());
        assertEquals("(00)00000-0000", user.getUserPhone());
    }
    
    @Test
    public void shouldCreateUserWithNamePhone() throws UserException {
        User userFully = new User("Adeobaldo", "(00)00000-0000");
        assertEquals("Adeobaldo", userFully.getUserName());
        assertEquals("(00)00000-0000", userFully.getUserPhone());
    }
    
    @Test
    public void shouldThrowUserException() {
        UserException userAllNullException = assertThrows(UserException.class, () -> {
            User userNull = new User(null, null);
        });
        
        assertEquals("O nome do usuário e o telefone são obrigatórios!", userAllNullException.getMessage());
        
        UserException userUpperCaseException = assertThrows(UserException.class, () -> {
            User userUpperCase = new User("adeobaldo", "(00)00000-0000");
        });
        
        assertEquals("O nome do usuário deve começar com uma letra maiúscula!", userUpperCaseException.getMessage());
        
        UserException userPhoneRegexException = assertThrows(UserException.class, () -> {
            User userPhoneRegex = new User("Adeobaldo", "0000000000");
        });
        
        assertEquals("O telefone deve conter o padrão (00)00000-0000", userPhoneRegexException.getMessage());
    }
}
