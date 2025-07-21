package pcpaisiproject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.TableView;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class mainFormController implements Initializable {

    @FXML
    private Button customers_Btn;

    @FXML
    private Button dashboard_Btn;

    @FXML
    private Button inventory_Btn;

    @FXML
    private AnchorPane inventory_Form;

    @FXML
    private Button inventory_addBtn;

    @FXML
    private Button inventory_clearBtn;

    @FXML
    private TableColumn<?, ?> inventory_col_date;

    @FXML
    private TableColumn<?, ?> inventory_col_price;

    @FXML
    private TableColumn<?, ?> inventory_col_productID;

    @FXML
    private TableColumn<?, ?> inventory_col_productName;

    @FXML
    private TableColumn<?, ?> inventory_col_productType;

    @FXML
    private TableColumn<?, ?> inventory_col_status;

    @FXML
    private TableColumn<?, ?> inventory_col_stock;

    @FXML
    private Button inventory_deleteBtn;

    @FXML
    private ImageView inventory_imageView;

    @FXML
    private Button inventory_importBtn;

    @FXML
    private TableView<?> inventory_tableView;

    @FXML
    private Button inventory_updateBtn;

    @FXML
    private Button logout_Btn;

    @FXML
    private AnchorPane main_form;

    @FXML
    private Button product_Btn;

    @FXML
    private Label username;
    
     @FXML
    private TextField inventory_price;

    @FXML
    private TextField inventory_productID;
    
    @FXML
    private TextField inventory_productname;

    @FXML
    private ComboBox<?> inventory_status;

    @FXML
    private TextField inventory_stock;
    
    @FXML
    private ComboBox<?> inventory_type;

    
    private Alert alert;
    private Connection Connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;
    
    
    private String[] typeList = {"Processors", "Motherboards"};
    
     
    public void inventoryTypeList() {
        
        List<String> typeL = new ArrayList<>();
        
        for (String data : typeList) {
            typeL.add(data);
        }
        
        ObservableList listData = FXCollections.observableArrayList(typeL);
        inventory_type.setItems(listData);
       
    }
    
    private String[] statusList = {"Available", "Unavailable"};
    
    public void inventoryStatusList() {
        
        List<String> statusL = new ArrayList<>();
        
        for (String data : statusList) {
            statusL.add(data);
        }
        
        ObservableList listData = FXCollections.observableArrayList(statusL);
        inventory_status.setItems(listData);
        
    }

     public void logout() {
        
        try {
            
            alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to logout?");
            Optional<ButtonType> option = alert.showAndWait();
            
            if (option.get().equals(ButtonType.OK)) {
                logout_Btn.getScene().getWindow().hide();

                Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
                
                Stage stage = new Stage();
                Scene scene = new Scene(root);
                
                stage.setTitle("PC Paisi");
                
                stage.setScene(scene);
                stage.show();
                
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
    public void displayUsername() {
        
        String user = data.username;
        user = user.substring(0, 1).toUpperCase() + user.substring(1);
        username.setText(user);
        
    }
 
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
  
        displayUsername();
        inventoryTypeList();
        inventoryStatusList();
        
    }
    
}
