package pcpaisiproject;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javafx.util.Duration;
import java.util.ResourceBundle;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;


public class FXMLDocumentController implements Initializable {
    
    private Label label;
    @FXML
    private AnchorPane si_loginForm;
    @FXML
    private TextField si_username;
    @FXML
    private PasswordField si_password;
    @FXML
    private Button si_loginBtn;
    @FXML
    private Hyperlink si_forgotpass;
    @FXML
    private AnchorPane su_signupForm;
    @FXML
    private TextField su_username;
    @FXML
    private PasswordField su_password;
    @FXML
    private TextField su_answer;
    @FXML
    private Button su_signupBtn;
    @FXML
    private ComboBox<?> su_question;
    @FXML
    private AnchorPane side_Form;
    @FXML
    private Button side_CreateBtn;
    @FXML
    private Button side_alreadyhave;
    
    private Connection Connect;
    private PreparedStatement prepare;
    private ResultSet result;
    private Alert alert;
    
    public void regBtn(){
    if(su_username.getText().isEmpty() || su_password.getText().isEmpty() || su_question.getSelectionModel().getSelectedItem() == null || su_answer.getText().isEmpty()){
     
        alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error Message");
        alert.setHeaderText(null);
        alert.setContentText("Please fill all blank fields");
        alert.showAndWait();
    }else{
    String regData = "INSERT INTO employee (username, password, question, answer)" + "VALUE(?,?,?,?)";
    
    Connect = database.ConnectDB();
    try{
        prepare = Connect.prepareStatement(regData);
        prepare.setString(1, su_username.getText());
        prepare.setString(2, su_password.getText());
        prepare.setString(3, (String)su_question.getSelectionModel().getSelectedItem());
        prepare.setString(4, su_answer.getText());
        prepare.executeUpdate();
        
        
        alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Information Message");
        alert.setHeaderText(null);
        alert.setContentText("Successfully registered Account!");
        alert.showAndWait();
        
        
        su_username.setText("");
        su_password.setText("");
        su_question.getSelectionModel().clearSelection();
        su_answer.setText("");
        
        TranslateTransition slider = new TranslateTransition();
        
         slider.setNode(side_Form);
         slider.setToX(0);
         slider.setDuration(Duration.seconds(.5));
                    
         slider.setOnFinished((ActionEvent e) -> {
             side_alreadyhave.setVisible(false);
             side_CreateBtn.setVisible(true);
                    });
                    slider.play();

    
    }catch(Exception e){
      e.printStackTrace();
        
    }
    
    }
    
    }
   
   private String[] questionList = {"What is your favourite color?", "What is your favourite food?", "What is your birth date?"};

    public void regLquestionlist() {
        List<String> listQ = new ArrayList<>();
        for (String data : questionList) {
            listQ.add(data);
        }
        ObservableList listData = FXCollections.observableArrayList(listQ);
        su_question.setItems(listData);

    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }    

    @FXML
    private void switchForm(ActionEvent event) {
        
        TranslateTransition slider = new TranslateTransition();
        if (event.getSource() == side_CreateBtn) {
            slider.setNode(side_Form);
            slider.setToX(300);
            slider.setDuration(Duration.seconds(.5));

            slider.setOnFinished((ActionEvent e) -> {
                side_alreadyhave.setVisible(true);
                side_CreateBtn.setVisible(false);
                regLquestionlist();

            });
            slider.play();
        } else if (event.getSource() == side_alreadyhave) {
            slider.setNode(side_Form);
            slider.setToX(0);
            slider.setDuration(Duration.seconds(.5));

            slider.setOnFinished((ActionEvent e) -> {
                side_alreadyhave.setVisible(false);
                side_CreateBtn.setVisible(true);

            });

            slider.play();
        }
    }
    
}
