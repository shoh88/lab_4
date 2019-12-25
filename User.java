package Essence;

import java.io.Serializable;

public class User implements Serializable{
    private int id;
    private String name;
    private String email;
    private String password;
    private String role;
    
    public User(int id) {
        this.id = id;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @return the firstname
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the firstname to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the usermail
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the mail to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the role
     */
    public String getRole() {
        return role;
    }

    /**
     * @param role the role to set
     */
    public void setRole(String role) {
        this.role = role;
    }
    
    
    @Override
    public User clone() {
        User usr = new User(this.id);
        usr.setName(name);
        usr.setEmail(email);
        usr.setPassword(password);
        usr.setRole(role);
        return usr;
    }
    
    public void restore(User usr) {
        this.id = usr.getId();
        this.name = usr.getName();
        this.email = usr.getEmail();
        this.password = usr.getPassword();
        this.role = usr.getRole();
    }
    
}
