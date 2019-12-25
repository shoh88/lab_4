package Handlers;

import Essence.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserHandler extends Handler {
    
    public void updateUserInfo(User usr)
    {
        Connection conn = null;
        Statement stmt = null;
        System.out.print("qqqqqqqqqqqqqqq");
        try{
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            String sql;
            sql = "UPDATE userss set name='" + usr.getName() + "',"
                    + "mail='" + usr.getEmail() + "' WHERE id=" + usr.getId() + ";";
            stmt.executeUpdate(sql);
            stmt.close();
            conn.close();
        }catch(SQLException se){
           se.printStackTrace();
        }catch(Exception e){
           e.printStackTrace();
        }finally{
           try{
              if(stmt!=null)
                 stmt.close();
           }catch(SQLException se){
               se.printStackTrace();
           }
           try{
              if(conn!=null)
                 conn.close();
           }catch(SQLException se){
              se.printStackTrace();
           }
        }
    }
      
    public void removeUserFromTable(User usr)
    {
        Connection conn = null;
        Statement stmt = null;
        try{
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            String sql;
            sql = "DELETE FROM userss WHERE id=" + usr.getId() + ";";
            stmt.executeUpdate(sql);
            stmt.close();
            conn.close();
        }catch(SQLException se){
           se.printStackTrace();
        }catch(Exception e){
           e.printStackTrace();
        }finally{
           try{
              if(stmt!=null)
                 stmt.close();
           }catch(SQLException se){
           }
           try{
              if(conn!=null)
                 conn.close();
           }catch(SQLException se){
              se.printStackTrace();
           }
        }
    }
    
    public List<User> buildResponseTable() {
        ArrayList<User> p = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;
        try{
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT * FROM userss;";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                User x = new User(rs.getInt("id"));
                x.setName(rs.getString("name"));
                x.setEmail(rs.getString("mail"));
                x.setPassword(rs.getString("password"));
                x.setRole(rs.getString("role"));
                p.add(x);
            }
            rs.close();
            stmt.close();
            conn.close();
        }catch(SQLException se){
           se.printStackTrace();
        }catch(Exception e){
           e.printStackTrace();
        }finally{
           try{
              if(stmt!=null)
                 stmt.close();
           }catch(SQLException se){
           }
           try{
              if(conn!=null)
                 conn.close();
           }catch(SQLException se){
              se.printStackTrace();
           }
        }
        return p;
    }

}
