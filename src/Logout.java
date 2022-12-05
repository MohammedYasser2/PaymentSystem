public class Logout {
    public Logout(LoginUser login) {
        login.setUser(null);
        System.out.println("Logout Success");
    }
}
