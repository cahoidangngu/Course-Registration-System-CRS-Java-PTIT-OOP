public abstract class User {
    private String username;
    private String password;
    private String firstName;
    private String midName;
    private String lastName;

    public User() {
    }

    public User(String username, String password, String firstName, String midName, String lastName) {
        this.username = username.trim();
        this.password = password.trim();
        this.firstName = firstName.trim();
        this.midName = midName.trim();
        this.lastName = lastName.trim();
    }

    
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setMidName(String midName) {
        this.midName = midName;
    }

    public String getMidName() {
        return midName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
    
    public String getName() {
        return firstName+" "+midName+" "+lastName;
    }
    
    @Override
    public String toString() {
        // LastName, MidName, LastName, UserName, Password
        return getFirstName()+","+getMidName()+","+getLastName()+","+getUsername()+","+getPassword();
    }
    
}
