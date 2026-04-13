package javafiles.User;

public class User {
    private String name;
    private String email;
    private String password;
    private boolean verified;
    private boolean hasVoted;

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.verified = false;
        this.hasVoted = false;
    }

    public User(String name, String email, String password, boolean verified, boolean hasVoted) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.verified = verified;
        this.hasVoted = hasVoted;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public boolean isHasVoted() {
        return hasVoted;
    }

    public void setHasVoted(boolean hasVoted) {
        this.hasVoted = hasVoted;
    }

    public String toFileString() {
        return name + "," + email + "," + password + "," + verified + "," + hasVoted;
    }
}