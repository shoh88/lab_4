/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rental;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

/**
 *
 * @author egaom
 */
@ManagedBean(name="userController")
@SessionScoped
public class UserController {

    int startId;
    int endId;
    NewUsersHelper helper;
    private NewUsers current;
    DataModel userTitles;
    
    public UserController() {
        helper = new NewUsersHelper();
        startId = 1;
        endId = 10;
    }
    
    public UserController(int startId, int endId) {
        helper = new NewUsersHelper();
        this.startId = startId;
        this.endId = endId;
    }
        
    public NewUsers getSelected() {
        if (current == null) {
            current = new NewUsers();  
        }
        return current;
    }
    
    public DataModel getUserTitles() {
        if (userTitles == null) {
            userTitles = new ListDataModel(helper.getUsersTitles(startId, endId));
        }
        return userTitles;
    }

}
