/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rental;

import Essence.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author egaom
 */
public class NewUsersHelper {
    Session session = null;
    public NewUsersHelper() {
        this.session = HibernateUtil.getSessionFactory().getCurrentSession();
    }

    public List getUsersTitles(int startID, int endID) {
    List<NewUsersHelper> usersList = null;
    try {
        org.hibernate.Transaction tx = session.beginTransaction();
        Query q = session.createQuery ("from Userss as users where user.id between '"+startID+"' and '"+endID+"'");
        usersList = (List<NewUsersHelper>) q.list();
    } catch (Exception e) {
        e.printStackTrace();
    }
    return usersList;
}

    public User getUserByID(int userId){
        User user = null;

        try {
            org.hibernate.Transaction tx = session.beginTransaction();
            Query q = session.createQuery("from Userss as users where users.id=" + userId);
            user = (User) q.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }
    
}
