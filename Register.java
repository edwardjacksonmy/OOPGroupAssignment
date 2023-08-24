
package groupassignment;

import java.sql.*;

public class Register {
    
    private String username, password, retypePass;
    private boolean authenticator = false, authenticator2 = true ,authenticator3 = false;
    
    public Register(){
        //default constructor
    }
    
    public Register(String username, String password, String retypePass){
        this.username   = username;
        this.password   = password;
        this.retypePass = retypePass;
    }
    
    //------------------get----------------------------//
    
    public String getUsername(){
        return username;
    }
    
    public String getPassword(){
        return password;
    }
    
    public String getRetypepass(){
        return retypePass;
    }
    
    public boolean getAuthenticator(){
        return authenticator;
    }
    
    public boolean getAuthenticator2(){
        return authenticator2;
    } 
    
    public boolean getAuthenticator3(){
        return authenticator3;
    } 

    //-------------------------------------------------//
    
    public boolean checkUsername(){
        
        DatabaseConnection newConnection = new DatabaseConnection();
        Connection connectDB             = newConnection.getConnection();
        
        
        String sqlLogin = "SELECT count(1) FROM useraccount WHERE username = '" + username + "'; ";
        
        try{
            Statement stmt = connectDB.createStatement();
            ResultSet result = stmt.executeQuery(sqlLogin);
            
            while(result.next()){
                if(result.getInt(1) == 1){ //check count(1) returns -> .getInt(1) = columnIndex 1
                    authenticator = true; //YES, found
                }else{
                    authenticator = false; //Not found
                }
            }         
            stmt.close();
            result.close();
            
        }catch(Exception e){
            e.printStackTrace();
        }
        return authenticator; //return authenticator
    }
    
    
    public boolean checkPassword(){
        if(!getPassword().equals(getRetypepass())){ //Password doesn't match
            authenticator2 = true; //Yes it doesn't match   
            System.out.println("equals: "+ getPassword().equals(getRetypepass()));
        }else{
            authenticator2 = false; //No it matches
        }
        return authenticator2; //return authenticator2
    }
    
    
    public void createAccount(){
        
        DatabaseConnection newConnection = new DatabaseConnection();
        Connection connectDB             = newConnection.getConnection();
        
        
        String sql = "INSERT INTO useraccount(username,password) VALUES ('" + username + "','" + password + "');";
      
        
        try{
            Statement stmt = connectDB.createStatement();
            stmt.executeUpdate(sql);
            System.out.println("User account has been created!");
            authenticator3 = true;         
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
