import java.io.Serializable;

public class Akun implements Serializable {
    private String username;
    private String password;

    public Akun(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}