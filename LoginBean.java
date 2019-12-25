package Faces;

import Handlers.LoginHandler;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author t_grid
 */
@ManagedBean
@SessionScoped
public class LoginBean implements Serializable{
    private LoginHandler lhandler;
    private String email;
    private String password;
    private boolean loggedIn;
    private String role = null;
    private String name;

    /**
     * Creates a new instance of UserBean
     */
    public LoginBean() {
    }
    
    public String authorize() {
        lhandler = new LoginHandler();
        int id;
        if ((id = lhandler.getUser(getEmail(), getPassword())) != -1)
        {
            setLoggedIn(true);
            HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
				.getExternalContext().getSession(false);
            session.setAttribute("name", lhandler.getName());
            session.setAttribute("id", id);
            role = lhandler.getRole();
            return "index";
        }
        else 
            return "login";
    }
    
    public String logout() {
        setLoggedIn(false);
        lhandler = null;
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
				.getExternalContext().getSession(false);
        session.invalidate();
        role = null;
        return "login";
    }
    
    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }
    
    public String getName() {
        return name;
    }
    
    public String getLastResponse() {
        if (lhandler != null)
            return lhandler.getResponse();
        else 
            return "noerror";
    }

    /**
     * @return the loggedIn
     */
    public boolean isLoggedIn() {
        return loggedIn;
    }

    /**
     * @param loggedIn the loggedIn to set
     */
    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    /**
     * @return the accessLevel
     */
    public String getRole() {
        return role;
    }
    
}
