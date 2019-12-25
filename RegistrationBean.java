package Faces;

import Handlers.RegistrationHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class RegistrationBean {
    private RegistrationHandler rhandler;
    private String email;
    private String password;
    private String name;

    private Boolean lastRegResult;

    public RegistrationBean() {
        lastRegResult = false;
    }
    
    public Boolean getLastRegResult() { return lastRegResult; }
    
    public String getLastResponse() {
        if (rhandler != null)
            return rhandler.getLastResponse();
        return "noerror";
    }
    
    public String register() {
        rhandler = new RegistrationHandler();
        lastRegResult = rhandler.addUser(name, email, password);
        return "login";
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
