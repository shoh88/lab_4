/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Faces;

import Essence.User;
import Handlers.UserHandler;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;


@ManagedBean
@RequestScoped
public class UsersBean {
    private UserHandler handler;
    private List<User> users;
    private User user;
    private boolean edit;
    private User beforeEdit = null;

    /**
     * Creates a new instance of UsersBean
     */
    public UsersBean() {
    }
    
    @PostConstruct
    public void init() {
        handler = new UserHandler();
        users = handler.buildResponseTable();
    }
    
    public void edit(User usr) {
        beforeEdit = usr.clone();
        this.user = usr;
        edit = true;
    }
    
    public void remove(User usr) {
        System.out.print("remove");
        handler.removeUserFromTable(usr);
        users.remove(usr);
    }
    
    public void cancelEdit() {
       this.getUser().restore(beforeEdit);
       this.user = null;
       edit = false;
    }
    
    public void saveEdit() {
        System.out.print("save");
        handler.updateUserInfo(user);
        this.user = null;
        edit = false;
    }

    /**
     * @return the users
     */
    public List<User> getUsers() {
        return users;
    }

    /**
     * @return the edit
     */
    public boolean isEdit() {
        return edit;
    }

    /**
     * @return the user
     */
    public User getUser() {
        return user;
    }
    
}
