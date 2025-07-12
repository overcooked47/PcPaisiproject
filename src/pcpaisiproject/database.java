package pcpaisiproject;

import java.sql.Connection;
import java.sql.DriverManager;


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


