/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package authentication;

/**
 *
 * @author Mikael
 */
public class Authentication {
    private String login;
    private String password;
    private int role;

    public Authentication(String login, String password, int role) {
        this.login = login;
        this.password = password;
        this.role = role;
    }

    public Authentication(String login, String password) {
        this(login, password, 0);
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }
    
    @Override
    public String toString() {
        return "Authentication{" + "login=" + login + ", password=" + password + '}';
    }
}
