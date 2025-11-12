package services;

public class LoginService {
    private static String user = "admin";
    private static String pass = "admin";

    public boolean authenticate(String u, String p) {
        return user.equals(u) && pass.equals(p);
    }

    public void changePassword(String old, String nw) {
        if (!pass.equals(old)) throw new ValidationException("wrong");
        pass = nw;
    }
}