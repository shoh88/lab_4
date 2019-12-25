package Handlers;

import java.io.Serializable;
import java.sql.*;

public class LoginHandler extends Handler implements Serializable{
    
    private String response;
    private String name;
    private String role;

    public String getResponse() {
        return response;
    }
    
    public String getName() {
        return name;
    }
    
    public String getRole() {
        return role;
    }
    
    public int getUser(String email, String password) {
        int id = -1;
        response = "";
        name = "";
        Connection conn = null;
        Statement stmt = null;
        try{
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT id, name, role FROM userss where mail='" + email +"' and password='" + password + "';";
            ResultSet rs = stmt.executeQuery(sql);
            if(rs.next()){
                id = rs.getInt("id");
                String first = rs.getString("name");
                role = rs.getString("role");
                name = first;
                response = "Добро пожаловать, " + first + "!";
           }
           rs.close();
           stmt.close();
           conn.close();
        }catch(SQLException se){
           response = "SQLException: " + se.getLocalizedMessage() + "      SELECT id, name, role FROM userss where mail='" + email +"' and password='" + password + "';";
        }catch(Exception e){
           response = "Exception: " + e.getLocalizedMessage();
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
        if (response.length() == 0)
            response = "Пользователь с указанными данными не найден!";
        return id;
    } 
}
