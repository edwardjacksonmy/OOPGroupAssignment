
package groupassignment;

import java.sql.*;

public class DatabaseConnection {
    
    public Connection connection;
    
    public Connection getConnection(){
    
        String dbName = "java2"; //Schema Name
        String dbUser = "root";
        String dbPass = "";
        String url = "jdbc:mysql://localhost:3306/" + dbName + "?autoReconnect=true&useSSL=false";
     
        try{
            Class.forName("com.mysql.jdbc.Driver"); //Libraries -> Add Library -> mySQL JDBC Driver || MySQL connector 8 use "com.mysql.cj.jdbc.Driver"
            connection = DriverManager.getConnection(url,dbUser,dbPass);
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return connection;
    }
    
/**
* (ERROR) java.sql.SQLException: java.lang.ClassCastException: java.math.BigInteger cannot be cast to java.lang.Long
* (MenuBar) Tools -> Libraries -> MySQL JDBC Driver -> Add JAR -> mysql-connector-java-5.1.47-bin.jar or higher
* (IMPORTANT)***Remove the old one*** else it would only load the old one
*/
    
/**
* (ERROR) Authentication plugin 'caching_sha2_password' 
* Windows Search -> MYSQL Command Line Client -> type in:
* mysql -u USERNAME -p PASSWORD
* ALTER USER 'YOUR_USERNAME_HERE'@'localhost' IDENTIFIED WITH mysql_native_password BY 'YOUR_PASSWORD_HERE';
* Example: ALTER USER 'root'@'localhost' IDENTIFIED WITH mysql_native_password BY '1234';
*/

}

