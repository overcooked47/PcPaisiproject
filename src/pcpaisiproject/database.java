/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pcpaisiproject;


import java.sql.Connection;

/**
 *
 * @author Shayan
 */
public class database {
    public static Connection ConnectDB(){
    
        try{
        Class.forName("com.mysql.jdbc.Driver");
        Connection connect = DriverManager.getConnection("jdbc:mysql://localhost/pcpaisi", "root", "");
        return connect;
        
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    
    }
    
}


