package Handlers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author tgrid
 */
public class RegistrationHandler extends Handler{

    private String response;
    
    public String getLastResponse() {
        return response;
    }
    
    /**
     *
     * @param name
     * @return
     */
    public Boolean addUser(String name, String email, String password)
    {
        Boolean ret = false;
        response = "";
        Connection conn = null;
        Statement stmt = null;
        try{
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT id, name FROM userss where mail='" + email +"';";
            ResultSet rs = stmt.executeQuery(sql);
            if(rs.next()){
                System.out.println("Пользователь с таким адресом электронной почты уже зарегистрирован!");
                response = "Пользователь с таким адресом электронной почты уже зарегистрирован!";
                
            } else {
                sql =   "INSERT INTO userss (name, mail, password, role) VALUES ('" + 
                        name +"','"  + email +"','" +
                        password +"',1);";
                System.out.println(sql);
                stmt.executeUpdate(sql);
                response = "Регистрация успешно завершена, " + name + "!";
                ret = true;

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
        if (response.length() == 0)
            response = "Произошла ошибка регистрации!";
        return ret;
    }

}
