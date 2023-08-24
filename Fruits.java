
package groupassignment;

import java.sql.*;
import java.time.LocalDate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Fruits {
    
    private String name, region, keycode, supplier, tempKey;
    private int stock, purchased, sold, rotten, id;
    private double costs;
    private LocalDate arrival;
    
    private boolean authenticator;
    
    private ObservableList<Fruits> data;
    private ObservableList<String> content = null;
    
    public Fruits(){
        //default constructor
    }
    
    public Fruits(String keycode){
        this.keycode = keycode;
    }

    public Fruits(String name, String keycode, String region, int purchased, int sold, int stock, int rotten, double costs, String supplier, LocalDate arrival){
        this.name      = name;    
        this.keycode   = keycode;
        this.region    = region;
        this.purchased = purchased;
        this.sold      = sold;
        this.stock     = stock;
        this.rotten    = rotten;
        this.costs     = costs;
        this.supplier  = supplier;
        this.arrival   = arrival;   
    }
    
    public Fruits(String name, String keycode, String region, int purchased, int sold, int stock, int rotten, double costs, String supplier, LocalDate arrival, String tempKey){
        this.name      = name;    
        this.keycode   = keycode;
        this.region    = region;
        this.purchased = purchased;
        this.sold      = sold;
        this.stock     = stock;
        this.rotten    = rotten;
        this.costs     = costs;
        this.supplier  = supplier;
        this.arrival   = arrival;   
        this.tempKey   = tempKey;
    }
    
    //------------------get----------------------------//
  
    public String getName(){
        return name;
    }
    
    public String getRegion(){
        return region;
    }
    
    public String getKeycode(){
        return keycode;
    }
    
    public double getCosts(){
        return costs;
    }
    
    public int getPurchased(){
        return purchased;
    }
    
    public int getSold(){
        return sold;
    }
    
    public String getSupplier(){
        return supplier;
    }
    
    public LocalDate getArrivalDate(){
        return arrival;
    }
    
    public int getRotten(){
        return rotten;
    } 
    
    public int getStock(){
        return stock;
    }
    
    public String getTempkey(){
        
        return tempKey;
        
    }
    
    public ObservableList getObservableList(){
        return data;
    }
    
    //------------------set----------------------------//
    
    public void setFruitName(String newFruitName){
        name = newFruitName;
    }
    
    public void setKeyCode(String newKeyCode){
        keycode = newKeyCode;
    }
    
    public void setRegion(String newRegion){
        region = newRegion;
    }
    
    public void setPurchased(int newPurchased){
        purchased = newPurchased;
    }
    
    public void setSold(int newSold){
        sold = newSold;
    }
    
    public void setStock(int newStock){
        stock = newStock;
    }
    
    public void setRotten(int newRotten){
        rotten = newRotten;
    }
    
    public void setCosts(double newCost){
        costs = newCost;
    }
    
    public void setSupplier(String newSupplier){
        supplier = newSupplier;
    }
    
    public void setDate(LocalDate newArrival){
        arrival = newArrival;
    }
    
    //-------------------------------------------------//
    
    
    //Insert
    public void InsertFruits(){
         
        DatabaseConnection newConnection = new DatabaseConnection();
        Connection connectDB             = newConnection.getConnection();
        
        
        String sql = "INSERT INTO fruits(name,keycode,region,stock,rotten,supplier,costs,sold,purchased,arrival_date) VALUES ('" 
                     + name  + "','" + keycode + "','"  + region    + "','" + stock   + "','" + rotten + "','" 
                     + supplier + "','" + costs   + "','"  + sold + "','" + purchased + "','"  + arrival + "');";
        
        try{
            Statement stmt = connectDB.createStatement();
            stmt.executeUpdate(sql);
            System.out.println("Data has been inserted!");
            stmt.close();
        }catch(Exception e){
            e.printStackTrace();
        }   

    }
    
    //Delete
    public void DeleteFruits(){
        
        DatabaseConnection newConnection = new DatabaseConnection();
        Connection connectDB             = newConnection.getConnection();
        
        
        String sql = "DELETE FROM fruits WHERE id = '" + getID() + "';";

        try{
            if(id != 0){
                Statement stmt = connectDB.createStatement();
                stmt.executeUpdate(sql);
                
                System.out.println("Data has been deleted.");
                stmt.close();
            }else{
                System.err.println("keycode not found!");
            }                  
        }catch(Exception e){
            e.printStackTrace();
        }   
    }
    
    //Search
    public void SearchFruits(){
        
        DatabaseConnection newConnection = new DatabaseConnection();
        Connection connectDB             = newConnection.getConnection();
        
        
        String sql = "SELECT * FROM fruits WHERE keycode = '" + keycode + "';";

        try{           
            if(checkKeycode() == true){
                Statement stmt = connectDB.createStatement();
                ResultSet result = stmt.executeQuery(sql);
            
                while (result.next()) {

                    System.out.println("FOUND: " + result.getInt(1));

                    setFruitName(result.getString("name"));
                    setKeyCode(result.getString("keycode"));
                    setRegion(result.getString("region"));
                    setPurchased(result.getInt("purchased"));
                    setSold(result.getInt("sold"));
                    setStock(result.getInt("stock"));
                    setRotten(result.getInt("rotten"));
                    setCosts(result.getDouble("costs"));
                    setSupplier(result.getString("supplier"));
                    Date d1 = result.getDate("arrival_date");
                    setDate(d1.toLocalDate());

                    break;                
                }                           
                stmt.close();
                result.close();
            
            }else{
                System.err.println("ID not found!");
            }                  
        }catch(Exception e){
            e.printStackTrace();
        }   
    }
    
    //Update
    public void UpdateFruits(){
        
        DatabaseConnection newConnection = new DatabaseConnection();
        Connection connectDB             = newConnection.getConnection();
               
        String sql = "UPDATE fruits SET name = '" + name + "' , keycode = '" + keycode + "' , " +
                     "region = '"  + region     + "' , " + "stock = '" + stock + "' , "  + "rotten = '" + rotten + "' , " +
                     "supplier = '"  + supplier   + "' , " + "costs = '" + costs + "' , "  + "sold = '" + sold + "' , " +
                     " purchased = '"  + purchased  + "' , " + "arrival_date = '" + arrival    + "' "   + 
                     "WHERE keycode = '" + tempKey    + "';";
        
        try{
            if(keycode == null && tempKey == null){
                System.err.println("Please search a key to delete first!");
            }else if(tempKey == null){
                System.err.println("Error! Keycode not found!");
            }else if(keycode == null){
                System.err.println("Please enter a Keycode!");
            }else{
                Statement stmt = connectDB.createStatement();
                stmt.executeUpdate(sql);
                stmt.close();
            }
             
        }catch(Exception e){
            e.printStackTrace();
        }   
    }
    
    //show
    public void ShowFruits() {

        DatabaseConnection newConnection = new DatabaseConnection();
        Connection connectDB = newConnection.getConnection();

        data = FXCollections.observableArrayList();
        
        String sql = "SELECT * FROM fruits;";

        try {
            Statement stmt = connectDB.createStatement();
            ResultSet result = stmt.executeQuery(sql);

            while (result.next()) {
                
                setFruitName(result.getString("name"));
                setKeyCode(result.getString("keycode"));
                setRegion(result.getString("region"));
                setPurchased(result.getInt("purchased"));
                setSold(result.getInt("sold"));
                setStock(result.getInt("stock"));
                setRotten(result.getInt("rotten"));
                setCosts(result.getDouble("costs"));
                setSupplier(result.getString("supplier"));
                Date d1 = result.getDate("arrival_date");
                setDate(d1.toLocalDate());
                System.out.println("content added " + content);
                
                data.add(new Fruits(getName(),getKeycode(),getRegion(),getPurchased(),getSold(),getStock(),getRotten(),getCosts(),getSupplier(),getArrivalDate()));

            }
            stmt.close();
            result.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public boolean checkKeycode(){
        
        DatabaseConnection newConnection = new DatabaseConnection();
        Connection connectDB             = newConnection.getConnection();
        
        
        String sql = "SELECT count(1) FROM fruits WHERE keycode = '" + getKeycode() + "'; ";
        
        try{
            Statement stmt = connectDB.createStatement();
            ResultSet result = stmt.executeQuery(sql);
            
            while(result.next()){
                
                if(result.getInt(1) == 1){ //check count(1) returns -> .getInt(1) = columnIndex 1 in database
                    authenticator = true;
                    tempKey = keycode;
                    System.out.println("Keycode Found: "+tempKey);
                    break;
                }else{
                    authenticator = false;
                    System.out.println("Keycode not found! Authenticator: "+ authenticator);
                }
            }        
        }catch(Exception e){
            e.printStackTrace();
        }
        return authenticator; //return authenticator
    }
    
    public int getID(){
        
        DatabaseConnection newConnection = new DatabaseConnection();
        Connection connectDB             = newConnection.getConnection();
        
        String sql = "SELECT id FROM fruits WHERE keycode = '" + keycode + "';";
      
        try{
            Statement stmt = connectDB.createStatement();
            ResultSet result = stmt.executeQuery(sql);
            
            while(result.next()){
                id = result.getInt(1);
            }
            stmt.close();
            result.close();
                 
        }catch(Exception e){
            e.printStackTrace();
        }   
        return id;
    }
    
}
