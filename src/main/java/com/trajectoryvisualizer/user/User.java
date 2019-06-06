package com.trajectoryvisualizer.user;
import java.util.Base64;

/**
 * // TO DO
 */
public class User {

    private String username;
    private String password;

    public User() {
        super();
        this.username = "zavrsniRad";
        this.password = "zavrsnirad2019";
    }

    public User(String username, String password) {
        super();
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getBasicAuth() {
        String userpass = username + ":" + password;
        String basicAuth = "Basic " + new String(Base64.getEncoder().encode(userpass.getBytes()));

        return basicAuth;
    }
}