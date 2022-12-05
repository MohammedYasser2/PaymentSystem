public class User {
    private String  userName;
    private String password;
    private String email;
    private String name;
    private double wallet;
    private boolean firstTime;

    public User(){

    }

    public User(String userName, String password, String email, String name, double wallet,boolean firstTime) {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.name = name;
        this.wallet = wallet;
        this.firstTime=firstTime;
    }

    public boolean isFirstTime() {
        return firstTime;
    }

    public void setFirstTime(boolean firstTime) {
        this.firstTime = firstTime;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getWallet() {
        return wallet;
    }

    public void setWallet(double wallet) {
        this.wallet = wallet;
    }
}
