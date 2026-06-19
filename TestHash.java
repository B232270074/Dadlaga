import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class TestHash {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String hash = "$2a$10$pOw0EeGztRkPn4sWqOFtFOEoCOzUYwVo2yCPgCQY1sKzYpZJk2HpS";
        String[] passwords = {"admin", "password", "Password", "123456", "admin123"};
        for (String p : passwords) {
            System.out.println(p + ": " + encoder.matches(p, hash));
        }
    }
}
