
package groupassignment;



import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Duration;
import javafx.util.StringConverter;

public class InventoryFX extends Application{
    
    private final String datePattern = "yyyy-MM-dd";
    //private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(datePattern);
     
    private Stage stage, stage2; 
    private Scene LoginPage, MainPage, Register;
    
    private String name, keycode, region, supplier;
    private int purchased, sold, stock, rotten;
    private double costs;
    private LocalDate arrival;
       
    // -------------------- Login ----------------------------
    
    Label lbUser = new Label("Username:");
    Label lbPass = new Label("Password:");
    Label lbMsg  = new Label();
    
    TextField tfUser     = new TextField();
    PasswordField tfPass = new PasswordField();
    
    Button btnLogin = new Button("Login");
    
    Hyperlink hRegister = new Hyperlink("Register");
    
   // -------------------- Register -------------------------
    
    Label lbUser2   = new Label("Username:");
    Label lbPass2   = new Label("Password:");
    Label lbRePass  = new Label("Retype-Password:");
    Label lbErrMsg  = new Label();
    Label lbErrMsg2 = new Label();
    
    TextField tfUser2      = new TextField();
    PasswordField tfPass2  = new PasswordField();
    PasswordField tfRePass = new PasswordField();
    
    Button btnRegister = new Button("Register");
    
   // -------------------- Main Page ------------------------
    
    Label lbWelcome = new Label();
    Label lbSearch  = new Label("Search by Keycode:");
    
    TextField tfSearch = new TextField();
    
    Button btnInsert = new Button("Add ...");
    Button btnUpdate = new Button("Update");
    Button btnDelete = new Button("Delete");
    Button btnSearch = new Button("Search");
    Button btnUnlock = new Button("Unlock");
    Button btnLock   = new Button("Lock");
    Button btnShow   = new Button("Show");
    
    Hyperlink hLogout = new Hyperlink("Logout");
    
    TableView tblView = new TableView();
    
    TableColumn<Fruits, String>  column1  = new TableColumn<>("Key-Code");
    TableColumn<Fruits, String>  column2  = new TableColumn<>("Name");
    TableColumn<Fruits, String>  column3  = new TableColumn<>("Region");
    TableColumn<Fruits, Integer> column4  = new TableColumn<>("Purchased");
    TableColumn<Fruits, Integer> column5  = new TableColumn<>("Sold");
    TableColumn<Fruits, Integer> column6  = new TableColumn<>("Stock");
    TableColumn<Fruits, Integer> column7  = new TableColumn<>("Rotten");
    TableColumn<Fruits, String>  column8  = new TableColumn<>("Costs(RM)");
    TableColumn<Fruits, String>  column9  = new TableColumn<>("Supplier");
    TableColumn<Fruits, String>  column10 = new TableColumn<>("Date Arrival");
    
   // --------------------- Fruits --------------------------
    
    Label lbFruitName = new Label("Fruit Name:");
    Label lbKeycode   = new Label("Key-Code:");
    Label lbRegion    = new Label("Region:");
    Label lbPurchased = new Label("Bought:");
    Label lbSold      = new Label("Sold:");
    Label lbStock     = new Label("Stock Left:");
    Label lbRotten    = new Label("Rotten:");
    Label lbCosts     = new Label("Costs:");
    Label lbSupplier  = new Label("Supplier:");
    Label lbArrival   = new Label("Arrival Date:");
    
    TextField tfFruitName = new TextField();
    TextField tfKeycode   = new TextField();
    TextField tfRegion    = new TextField();
    TextField tfPurchased = new TextField();
    TextField tfSold      = new TextField();
    TextField tfStock     = new TextField();
    TextField tfRotten    = new TextField();
    TextField tfCosts     = new TextField();
    TextField tfSupplier  = new TextField();
    
    DatePicker tfArrival  = new DatePicker();
    
   // --------------------- Pane ----------------------------
    
    BorderPane borderPane = new BorderPane();
    
    GridPane gridPane  = new GridPane();
    GridPane gridPane2 = new GridPane();
    GridPane gridPane3 = new GridPane();
    GridPane gridPane4 = new GridPane();
    GridPane gridPane5 = new GridPane(); 
    
    HBox hbox  = new HBox();
    HBox hbox2 = new HBox(); 
    HBox hbox3 = new HBox(); 
    HBox hbox4 = new HBox(); 
    HBox hbox5 = new HBox(); 
    VBox vbox  = new VBox();
    VBox vbox2 = new VBox();
    VBox vbox3 = new VBox(); 
    
    @Override
    public void start(Stage primaryStage) throws Exception{
        
        lbMsg.setStyle("-fx-text-fill: red"); //Error Message color
        lbErrMsg.setStyle("-fx-text-fill: red");
        
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setPadding(new Insets(15,15,15,15));
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        
        gridPane.add(lbUser, 0, 0);
        gridPane.add(lbPass, 0, 1);
        gridPane.add(tfUser, 1, 0);
        gridPane.add(tfPass, 1, 1);

        hbox.getChildren().addAll(hRegister, btnLogin);
        hbox.setAlignment(Pos.CENTER);
        hbox.setPadding(new Insets(10,10,10,10));
        hbox.setSpacing(150);
        
        vbox.getChildren().addAll(lbMsg, gridPane, hbox);
        vbox.setAlignment(Pos.CENTER);

        //Action
        btnLogin.setOnAction(e->login());
        hRegister.setOnAction(e->stage2.show());
        
        //Scene       
        LoginPage = LoginPage();      
        MainPage  = MainPage();
        
        //Stage
        stage = primaryStage;
        stage2 = RegisterStage();
        
        stage.setTitle("Inventory Managementment System");
        stage.setResizable(false);
        stage.setScene(LoginPage);
        stage.show();        
    
    }
    
    //---------------- Stages -------------------------//
    
    private Stage RegisterStage(){ //Pop-out window
        stage2 = new Stage();
        
        stage2.setTitle("Register Page");
        stage2.setX(800); //window position
        stage2.setY(200);
        stage2.initOwner(stage); 
        stage2.initModality(Modality.APPLICATION_MODAL); //Prevent actions to Owner stage;
        stage2.setResizable(false);
        Register = Register();
        stage2.setScene(Register);

        return stage2;
    } 
    
    //---------------- Scenes -------------------------//
   
    private Scene LoginPage(){
        LoginPage = new Scene(vbox, 500, 350);
        return LoginPage;
    }
    
    @SuppressWarnings("unchecked")
    private Scene MainPage(){
        //SearchBar
        gridPane2.setAlignment(Pos.TOP_LEFT);
        gridPane2.setPadding(new Insets(15,15,15,15));
        gridPane2.setHgap(10);
        gridPane2.setVgap(10);
        
        gridPane2.add(lbSearch,  0, 0);
        gridPane2.add(tfSearch,  0, 1);
        gridPane2.add(btnSearch, 1, 1);
        
        //Functions
        gridPane3.setAlignment(Pos.CENTER_LEFT);
        gridPane3.setPadding(new Insets(15,15,15,15));
        gridPane3.setHgap(10);
        gridPane3.setVgap(10);

        gridPane3.add(lbFruitName, 0, 0);
        gridPane3.add(tfFruitName, 1, 0);
        gridPane3.add(lbKeycode  , 0, 1);
        gridPane3.add(tfKeycode  , 1, 1);
        gridPane3.add(lbRegion   , 0, 2);
        gridPane3.add(tfRegion   , 1, 2);
        gridPane3.add(lbPurchased, 0, 3);
        gridPane3.add(tfPurchased, 1, 3);
        gridPane3.add(lbSold,      0, 4);
        gridPane3.add(tfSold,      1, 4);
        gridPane3.add(lbStock    , 0, 5);
        gridPane3.add(tfStock    , 1, 5);
        gridPane3.add(lbRotten   , 0, 6);
        gridPane3.add(tfRotten   , 1, 6);
        gridPane3.add(lbCosts    , 0, 7);
        gridPane3.add(tfCosts    , 1, 7);
        gridPane3.add(lbSupplier , 0, 8);
        gridPane3.add(tfSupplier , 1, 8);
        gridPane3.add(lbArrival  , 0, 9);
        gridPane3.add(tfArrival  , 1, 9);
        
        initialLock();
        
        //Date Convertor
        StringConverter converter = new StringConverter<LocalDate>(){
            
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(datePattern);
            
            @Override
            public String toString(LocalDate date) {
                if (date != null) {
                    return dateFormatter.format(date);
                } else {
                    return "";
                }
            }
            @Override
            public LocalDate fromString(String string) {
                if (string != null && !string.isEmpty()) {
                    return LocalDate.parse(string, dateFormatter);
                } else {
                    return null;
                }
            }
        };             
        tfArrival.setConverter(converter);
        tfArrival.setPromptText(datePattern.toLowerCase());
        
        //Unlock Button
        btnUnlock.setPrefWidth(80);
        hbox2.getChildren().addAll(btnUnlock);
        hbox2.setAlignment(Pos.CENTER_LEFT);
        hbox2.setMargin(btnUnlock,new Insets(10,10,10,95));        

        //Insert, Update, Delete, Show Button
        gridPane4.setAlignment(Pos.CENTER_LEFT);
        gridPane4.setPadding(new Insets(10,10,10,50));
        gridPane4.setHgap(10);
        gridPane4.setVgap(10);
        
        btnInsert.setPrefWidth(80);
        btnUpdate.setPrefWidth(80);
        btnDelete.setPrefWidth(80);
        btnShow  .setPrefWidth(80);
        
        gridPane4.add(btnInsert, 0, 0);
        gridPane4.add(btnUpdate, 1, 0);
        gridPane4.add(btnDelete, 0, 1);
        gridPane4.add(btnShow  , 1, 1);
 
        vbox2.getChildren().addAll(gridPane2, gridPane3, hbox2, gridPane4);

        
        //TableView - Columns   
        column1 .setCellValueFactory(new PropertyValueFactory<>("keycode"));
        column2 .setCellValueFactory(new PropertyValueFactory<>("name"));
        column3 .setCellValueFactory(new PropertyValueFactory<>("region"));
        column4 .setCellValueFactory(new PropertyValueFactory<>("purchased"));
        column5 .setCellValueFactory(new PropertyValueFactory<>("sold"));
        column6 .setCellValueFactory(new PropertyValueFactory<>("stock"));
        column7 .setCellValueFactory(new PropertyValueFactory<>("rotten"));
        column8 .setCellValueFactory(new PropertyValueFactory<>("costs"));
        column9 .setCellValueFactory(new PropertyValueFactory<>("supplier"));
        column10.setCellValueFactory(new PropertyValueFactory<>("arrival_date"));
        
     
        tblView.getColumns().addAll(column1,column2,column3,column4,column5,column6,column7,column8,column9,column10);
        tblView.setPrefSize(1300, 600);
        
        column2 .prefWidthProperty().bind(tblView.widthProperty().multiply(0.2));
        column3 .prefWidthProperty().bind(tblView.widthProperty().multiply(0.1));
        column4 .prefWidthProperty().bind(tblView.widthProperty().multiply(0.1));
        column9 .prefWidthProperty().bind(tblView.widthProperty().multiply(0.2));
        column10.prefWidthProperty().bind(tblView.widthProperty().multiply(0.1));
        
        column1 .setResizable(false); 
        column2 .setResizable(false);
        column3 .setResizable(false);
        column4 .setResizable(false);
        column5 .setResizable(false);
        column6 .setResizable(false);
        column7 .setResizable(false);
        column8 .setResizable(false);
        column9 .setResizable(false);
        column10.setResizable(false);
           
        //Add Table
        hbox3.getChildren().addAll(tblView);
        hbox3.setAlignment(Pos.CENTER_RIGHT);
        
        //MAINVIEW
        hbox4.getChildren().addAll(vbox2,hbox3);
        hbox4.setPadding(new Insets(10,10,10,25));
        borderPane.setAlignment(hLogout, Pos.TOP_RIGHT);
        borderPane.setTop(hLogout);
        borderPane.setAlignment(hbox4, Pos.CENTER);
        borderPane.setCenter(hbox4);
        
        //Actions
        btnInsert.setOnAction(e->add());
        btnUpdate.setOnAction(e->update());
        btnDelete.setOnAction(e->delete());
        btnSearch.setOnAction(e->search());
        btnShow  .setOnAction(e->show());
        btnUnlock.setOnAction(e->unlock());
        btnLock  .setOnAction(e->lock());
        hLogout  .setOnAction(e->logout());
        
        //Numbers only
        tfPurchased.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                tfPurchased.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
        
        tfStock.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                tfStock.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
        
        tfRotten.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                tfRotten.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
        
        tfSold.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                tfSold.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
        
        tfCosts.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d{0,9}([\\.]\\d{0,9})?")) {
                tfCosts.setText(oldValue);
            }
        });
        
        //Scene
        MainPage = new Scene(borderPane, 1700, 800);
        return MainPage;
    }
    
    
    private Scene Register(){
        
        hbox5.getChildren().addAll(lbErrMsg);
        hbox5.setAlignment(Pos.CENTER);
        
        gridPane5.setAlignment(Pos.CENTER);
        gridPane5.setPadding(new Insets(15,15,15,15));
        gridPane5.setHgap(10);
        gridPane5.setVgap(10);
             
        gridPane5.add(lbUser2 , 0, 0);
        gridPane5.add(tfUser2 , 1, 0);
        gridPane5.add(lbPass2 , 0, 1);
        gridPane5.add(tfPass2 , 1, 1);
        gridPane5.add(lbRePass, 0, 2);
        gridPane5.add(tfRePass, 1, 2);
        
        vbox3.getChildren().addAll(hbox5, gridPane5, btnRegister);
        vbox3.setAlignment(Pos.CENTER);
        
        //Action
        btnRegister.setOnAction(e->register());
        
        Register = new Scene(vbox3, 400, 350);
        return Register;
    }
    
    
    //---------------- Switch Scene -------------------//
    
    public void switchScene(Scene scene){
        stage.setScene(scene);
    }
    
    public void setTransition(Scene scene) {
        PauseTransition transition = new PauseTransition(Duration.seconds(1.3)); //Transition duration

        transition.setOnFinished(e -> switchScene(scene)); //change scene
        transition.playFromStart();
    }
    
    public void setTransition2(){
        PauseTransition transition = new PauseTransition(Duration.seconds(1.5)); //Transition duration

        transition.setOnFinished(e -> stage2.close()); //change scene
        transition.playFromStart();
    }
       

    //---------------- Register ----------------------//
    
    public void register(){
        
        String reUsername, rePass, rePass2;
        
        try{
            reUsername = tfUser2.getText().trim();
            rePass     = tfPass2.getText().trim();
            rePass2    = tfRePass.getText().trim();
          
            if (reUsername.length() == 0 && rePass.length() == 0 && rePass2.length() == 0) {
                lbErrMsg.setText("Username and Password are empty!");
            } else if (reUsername.length() == 0|| rePass.length() == 0 || rePass2.length() == 0) {
                lbErrMsg.setText("Username or Password is empty!");
            } else if(reUsername.length() == 0){
                lbErrMsg.setText("Username is empty");
            } else if(rePass.length() == 0){
                lbErrMsg.setText("Password is empty");
            } else { //correct route
                Register r1 = new Register(reUsername, rePass, rePass2);
                
                if (r1.checkUsername() == true) {
                    lbErrMsg.setText("Username has already exists!");
                } else if (r1.checkPassword() == true) {
                    lbErrMsg.setText("Password does not match!");
                }else{
                    r1.createAccount();
                    
                    if(r1.getAuthenticator3() == false){
                        lbErrMsg.setText("Failed to register!");
                        System.out.println("Failed to register!");
                    }else{
                        lbErrMsg.setText("Account Creation Successfully");
                        lbErrMsg.setStyle("-fx-text-fill: green");
                        tfUser2.setDisable(true);
                        tfPass2.setDisable(true);
                        tfRePass.setDisable(true);
                        btnRegister.setVisible(false);
                        setTransition2();
                    }
                }
            }
        } catch (Exception e) {
            if (tfUser2.getText().trim() == null && tfPass2.getText().trim() == null && tfRePass.getText().trim() == null) {
                alertError("Input Error","Please enter an input!");
            } else if (tfUser2.getText().trim() == null || tfPass2.getText().trim() == null || tfRePass.getText().trim() == null) {
                alertError("Input Error","Please fill in the blanks!"); 
            }
            e.printStackTrace();
        }
    }
    
    //---------------- Login --------------------//
    
    public void login(){
        if(tfUser.getText().isEmpty() == true && tfPass.getText().isEmpty() == true){
            System.err.println("Username & Password is empty");
            lbMsg.setText("Username and Password is empty");
        }else if((tfUser.getText().isEmpty() == true || tfPass.getText().isEmpty() == true)){
            System.err.println("Username or Password is empty");
            lbMsg.setText("Username or Password is empty");
        }else{
            Login login = new Login(tfUser.getText(), tfPass.getText());
            login.validateLogin();

            if(login.getAuthenticator() == true){ //login successful          
                lbMsg.setText("Login successful");
                lbMsg.setStyle("-fx-text-fill: green");
                btnLogin.setVisible(false);
                hRegister.setVisible(false);
                tfUser.setDisable(true);
                tfPass.setDisable(true);
                
                setTransition(MainPage);
                
            }else{ //login failed
                System.err.println("Username or Password is incorrect!");
                lbMsg.setText("Username or Password is incorrect!");
            }
        }
    } 
    
    //---------------- Logout ----------------------//
    
    public void logout(){
        //login page
        btnLogin.setVisible(true);
        hRegister.setVisible(true);
        tfUser.setDisable(false);
        tfPass.setDisable(false);
        lbMsg.setText(null);
        lbMsg.setStyle("-fx-text-fill: red");
        
        //register page
        tfUser2.setDisable(false);
        tfPass2.setDisable(false);
        tfRePass.setDisable(false);
        btnRegister.setVisible(true);
        lbErrMsg.setText(null);
        lbErrMsg.setStyle("-fx-text-fill: red");
        
        reset();
        stage.setScene(LoginPage);
    }
    
    //---------------- Button Functions ----------------------//
    
    public void add(){
        try{
            name      = tfFruitName.getText();
            keycode   = tfKeycode.getText();
            region    = tfRegion.getText();
            purchased = Integer.parseInt(tfPurchased.getText());
            stock     = Integer.parseInt(tfStock.getText());
            sold      = Integer.parseInt(tfSold.getText());
            rotten    = Integer.parseInt(tfRotten.getText());
            costs     = Double.parseDouble(tfCosts.getText());
            supplier  = tfSupplier.getText();
            arrival   = tfArrival.getValue();
            
            Fruits f1 = new Fruits(name, keycode, region, purchased, sold, stock, rotten, costs, supplier, arrival);
            f1.InsertFruits();
            
        }catch(NumberFormatException n){
            alertError("NumberFormatException","Please enter a valid input!");
            n.printStackTrace();
        }catch(NullPointerException e){
            alertError("NullPointerException", "There is an error in pathing!");
        }
    }
    
    public void delete(){
        try{
            keycode   = tfSearch.getText();
         
            Fruits f1 = new Fruits(keycode);
            boolean temp = f1.checkKeycode();
            
            if(temp == true){
                f1.DeleteFruits();
            }else{
                alertError("Input Error!", "Keycode not found!");
            }         
        }catch(Exception e){
            if(keycode != null){
               alertError("Input Error!", "Keycode not found!");
            }else{
                alertError("Input Error!", "Please search for keycode first!");
            }
        }
    }
    
    @SuppressWarnings("unchecked")
    public void search(){
        try{
            
            keycode   = tfSearch.getText();
         
            Fruits f1 = new Fruits(keycode);
            boolean temp = f1.checkKeycode();
            
            if(temp == true){
                f1.SearchFruits();
                
                ObservableList<Fruits> content = FXCollections.observableArrayList(f1);
           
                tfFruitName.setText(f1.getName());
                tfKeycode.setText(f1.getKeycode());
                tfRegion.setText(f1.getRegion());
                tfPurchased.setText(String.valueOf(f1.getPurchased()));
                tfSold.setText(String.valueOf(f1.getSold()));
                tfStock.setText(String.valueOf(f1.getStock()));
                tfRotten.setText(String.valueOf(f1.getRotten()));
                tfCosts.setText(String.valueOf(f1.getCosts()));
                tfSupplier.setText(f1.getSupplier());
                tfArrival.setValue(f1.getArrivalDate());
                
                /**
                 * <LocalDate> TableColumn was unable to get LocalDate data, even after data conversion
                 * therefore I declared as <String> at top, and convert LocalDate into String from Fruits.Java into TableView
                 */

                
                // Define callbacks
                column10.setCellValueFactory(new Callback<CellDataFeatures<Fruits, String>, ObservableValue<String>>() {
                    public ObservableValue<String> call(CellDataFeatures<Fruits, String> p) {
                        return new ReadOnlyObjectWrapper(p.getValue().getArrivalDate());
                    }
                });

                //append to table
                tblView.setItems(content);       
                
                //ObservableList<Fruits> data = tblView.getItems();
                //data.add(f1);
     
            }else{
                alertError("Input Error!", "Keycode not found!");
            }          
        }catch(Exception e){
            if(keycode != null){
               alertError("Input Error!", "Please try again!");
            }else{
                alertError("Input Error!", "Please search for keycode first!");
            }
        }
    }
    
    public void update(){
        
         try{
            name      = tfFruitName.getText();
            keycode   = tfKeycode.getText();
            region    = tfRegion.getText();
            purchased = Integer.parseInt(tfPurchased.getText());
            sold      = Integer.parseInt(tfSold.getText());
            stock     = Integer.parseInt(tfStock.getText());
            rotten    = Integer.parseInt(tfRotten.getText());
            costs     = Double.parseDouble(tfCosts.getText());
            supplier  = tfSupplier.getText();
            arrival   = tfArrival.getValue();
            
            String keycode2 = tfSearch.getText().trim();
            
            Fruits f1 = new Fruits(keycode2);           
            f1.checkKeycode();
            String tempKey = f1.getTempkey();
            if(tempKey.length() > 0){
                
                f1 = new Fruits(name, keycode, region, purchased, sold, stock, rotten, costs, supplier, arrival, tempKey);
                f1.UpdateFruits();

            }else{
                alertError("Input Error!", "Keycode not found!");
            }           
        }catch(Exception e){
            alertError("Input Error!", "Please try again!");
        }
    }
    

    @SuppressWarnings("unchecked")
    public void show(){
        Fruits f1 = new Fruits(); 

        f1.ShowFruits();

        ObservableList<String> content = f1.getObservableList();

        column10.setCellValueFactory(new Callback<CellDataFeatures<Fruits, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(CellDataFeatures<Fruits, String> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getArrivalDate());
            }
        });
        tblView.setItems(content);
    }
    
    
    public void unlock(){
        tfFruitName.setEditable(true);
        tfKeycode.setEditable(true);
        tfRegion.setEditable(true);
        tfPurchased.setEditable(true);
        tfStock.setEditable(true);
        tfSold.setEditable(true);
        tfRotten.setEditable(true);
        tfCosts.setEditable(true);
        tfSupplier.setEditable(true);
        tfArrival.setEditable(true);
        
        hbox2.getChildren().removeAll(btnUnlock);
        hbox2.getChildren().addAll(btnLock);
        btnLock.setPrefWidth(80);
        hbox2.setMargin(btnLock,new Insets(10,10,10,95)); 
        
        tfFruitName.setDisable(false);
        tfKeycode.setDisable(false);
        tfRegion.setDisable(false);
        tfPurchased.setDisable(false);
        tfSold.setDisable(false);
        tfStock.setDisable(false);
        tfRotten.setDisable(false);
        tfCosts.setDisable(false);
        tfSupplier.setDisable(false);
        tfArrival.setDisable(false);
    }
    
    public void lock(){
        tfFruitName.setEditable(false);
        tfKeycode.setEditable(false);
        tfRegion.setEditable(false);
        tfPurchased.setEditable(false);
        tfSold.setEditable(false);
        tfStock.setEditable(false);
        tfRotten.setEditable(false);
        tfCosts.setEditable(false);
        tfSupplier.setEditable(false);
        tfArrival.setEditable(false);
        
        hbox2.getChildren().removeAll(btnLock);
        hbox2.getChildren().addAll(btnUnlock);
        
        tfFruitName.setDisable(true);
        tfKeycode.setDisable(true);
        tfRegion.setDisable(true);
        tfPurchased.setDisable(true);
        tfSold.setDisable(true);
        tfStock.setDisable(true);
        tfRotten.setDisable(true);
        tfCosts.setDisable(true);
        tfSupplier.setDisable(true);
        tfArrival.setDisable(true);       
    }
    
    public void initialLock(){
        tfFruitName.setEditable(false);
        tfKeycode.setEditable(false);
        tfRegion.setEditable(false);
        tfPurchased.setEditable(false);
        tfSold.setEditable(false);
        tfStock.setEditable(false);
        tfRotten.setEditable(false);
        tfCosts.setEditable(false);
        tfSupplier.setEditable(false);
        tfArrival.setEditable(false);
        
        tfFruitName.setDisable(true);
        tfKeycode.setDisable(true);
        tfRegion.setDisable(true);
        tfPurchased.setDisable(true);
        tfSold.setDisable(true);
        tfStock.setDisable(true);
        tfRotten.setDisable(true);
        tfCosts.setDisable(true);
        tfSupplier.setDisable(true);
        tfArrival.setDisable(true);
    }
    
    @SuppressWarnings("unchecked")
    public void reset(){
        
        tfFruitName.setText(null);
        tfKeycode.setText(null);
        tfRegion.setText(null);
        tfPurchased.setText(null);
        tfSold.setText(null);
        tfStock.setText(null);
        tfRotten.setText(null);
        tfCosts.setText(null);
        tfSupplier.setText(null);
        tfArrival.setValue(null);
        tblView.setItems(null);
    }
    
    public void alertError(String title, String description) {
        Alert a = new Alert(Alert.AlertType.ERROR);
        a.setTitle(title);
        a.setHeaderText(null);
        a.setContentText(description);
        a.showAndWait();
    }
    
    
    public static void main(String[] args){
        launch(args);
    }
}