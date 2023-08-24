
package groupassignment;

import java.sql.*;

public class Login {
    
    private String username, password;
    private boolean authenticator = false;
    
    public Login(){
        //default constructor
    }
    
    public Login(String username, String password){
        this.username = username;
        this.password = password;
    }
    
    //------------------get----------------------------//
    
    public String getUsername(){
        return username;
    }
    
    public String getPassword(){
        return password;
    }
    
    public boolean getAuthenticator(){
        return authenticator;
    }
    
    //-------------------------------------------------//
    
    public void validateLogin(){
        
        DatabaseConnection newConnection = new DatabaseConnection();
        Connection connectDB             = newConnection.getConnection();
        

        String sql = "SELECT count(1) FROM useraccount WHERE username = '" + username + "' && password = '" + password + "'; ";
        
        try{
            Statement stmt = connectDB.createStatement();
            ResultSet result = stmt.executeQuery(sql);
            
            while(result.next()){
                if(result.getInt(1) == 1){ //check count(1) returns -> .getInt(1) = columnIndex 1
                    System.out.println("Login Successfully");
                    authenticator = true;
                    
                }else{
                    System.out.println("Username or Password is incorrect!");
                    authenticator = false;
                }
            }            
            stmt.close();
            result.close();
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
}
