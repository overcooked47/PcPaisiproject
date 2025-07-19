package pcpaisiproject;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.application.Application;

public class FXMLDocumentController implements Initializable {
    
    
    @FXML
    private TextField fp_answer;

    @FXML
    private Button fp_back;

    @FXML
    private Button fp_proceedBtn;

    @FXML
    private ComboBox<?> fp_question;

    @FXML
    private AnchorPane fp_questionForm;

    @FXML
    private Button np_back;

    @FXML
    private Button np_changePassBtn;

    @FXML
    private PasswordField np_confrmPassword;

    @FXML
    private PasswordField np_newPassword;

    @FXML
    private AnchorPane np_newPasswordForm;

    @FXML
    private Hyperlink si_forgotpass;

    @FXML
    private Button si_loginBtn;

    @FXML
    private AnchorPane si_loginForm;

    @FXML
    private PasswordField si_password;

    @FXML
    private TextField si_username;

    @FXML
    private Button side_CreateBtn;

    @FXML
    private AnchorPane side_Form;

    @FXML
    private Button side_alreadyhave;

    @FXML
    private TextField su_answer;

    @FXML
    private PasswordField su_password;

    @FXML
    private ComboBox<?> su_question;

    @FXML
    private Button su_signupBtn;

    @FXML
    private AnchorPane su_signupForm;

    @FXML
    private TextField su_username;
    
    @FXML
    private TextField fp_username;

    
    private Connection Connect;
    private PreparedStatement prepare;
    private ResultSet result;
    private Alert alert;
    
    
     public void loginBtn() {
        
        if (si_username.getText().isEmpty() || si_password.getText().isEmpty()) {
            alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Incorrect Username/Password");
            alert.showAndWait();
        } else {
            String selctData = "SELECT username, password FROM employee WHERE username = ? and password = ?";
            Connect = database.ConnectDB();
            
            try {
                prepare = Connect.prepareStatement(selctData);
                prepare.setString(1, si_username.getText());
                prepare.setString(2, si_password.getText());
                
                result = prepare.executeQuery();
                if (result.next()) {
                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Login!");
                    alert.showAndWait();   
                } else {
                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Incorrect Username/Password");
                    alert.showAndWait();
                } 
                
            } catch (Exception e) {
                e.printStackTrace();
            }
            
        }
        
    }
    
    
    public void regBtn(){
    if(su_username.getText().isEmpty() || su_password.getText().isEmpty() || su_question.getSelectionModel().getSelectedItem() == null || su_answer.getText().isEmpty()){
     
        alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error Message");
        alert.setHeaderText(null);
        alert.setContentText("Please fill all blank fields");
        alert.showAndWait();
    }else{
    String regData = "INSERT INTO employee (username, password, question, answer, date)" + "VALUE(?,?,?,?,?)";
    Connect = database.ConnectDB();
    try{
        
       String checkUsername = "SELECT username FROM employee WHERE username = '" + su_username.getText() + "'";
       prepare = Connect.prepareStatement(checkUsername);
       result = prepare.executeQuery();
       
       if(result.next()){
        alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error Message");
        alert.setHeaderText(null);
        alert.setContentText(su_username.getText() + " is already taken");
        alert.showAndWait();
       }else if(su_password.getText().length() < 8){
        alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error Message");
        alert.setHeaderText(null);
        alert.setContentText("Invalid password, at least 8 characters are required.");
        alert.showAndWait();
       }else{
        prepare = Connect.prepareStatement(regData);
        prepare.setString(1, su_username.getText());
        prepare.setString(2, su_password.getText());
        prepare.setString(3, (String)su_question.getSelectionModel().getSelectedItem());
        prepare.setString(4, su_answer.getText());
        
        Date date = new Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        prepare.setString(5, String.valueOf(sqlDate));
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
       }
                           
    }catch(Exception e){
      e.printStackTrace();
        
    }
    
    }
    
    }
   
   private String[] questionList = {"What is your favourite color?", "What is your birth date?"};

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
    
    public void switchForgotPass(){
        fp_questionForm.setVisible(true);
        si_loginForm.setVisible(false);
        
        forgotPassQuestionList();
}
    
     public void proceedBtn() {
        
        if (fp_username.getText().isEmpty() || fp_question.getSelectionModel().getSelectedItem() == null
                || fp_answer.getText().isEmpty()) {
            alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all blank fields");
            alert.showAndWait();
        } else {
            
            String selectData = "SELECT username, question, answer FROM employee WHERE username = ? AND question = ? AND answer = ?";
            Connect = database.ConnectDB();
            
            try {
              prepare = Connect.prepareStatement(selectData);
              prepare.setString(1, fp_username.getText());
              prepare.setString(2, (String) fp_question.getSelectionModel().getSelectedItem());
              prepare.setString(3, fp_answer.getText());
              result = prepare.executeQuery();
                
                if (result.next()) {
                    np_newPasswordForm.setVisible(true);
                    fp_questionForm.setVisible(false);
                } else {
                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Incorrect Information");
                    alert.showAndWait();
                }
                
            } catch (Exception e) {
                e.printStackTrace();
            }
            
        }
        
    }
     
     public void changePassBtn() {
        
        if (np_newPassword.getText().isEmpty() || np_confrmPassword.getText().isEmpty()) {
            alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all blank fields");
            alert.showAndWait();
        } else {
            
            if (np_newPassword.getText().equals(np_confrmPassword.getText())) {
                String getDate = "SELECT date FROM employee WHERE username = '"
                        + fp_username.getText() + "'";
                
                Connect = database.ConnectDB();
                
                try {
                    
                    prepare = Connect.prepareStatement(getDate);
                    result = prepare.executeQuery();
                    
                    String date = "";
                    if (result.next()) {
                        date = result.getString("date");
                    }
                    
                    String updatePass = "UPDATE employee SET password = '" + np_newPassword.getText() + "', question = '" + fp_question.getSelectionModel().getSelectedItem() + "', answer = '" + fp_answer.getText() + "', date = '" + date + "' WHERE username = '" + fp_username.getText() + "'";
                    prepare = Connect.prepareStatement(updatePass);
                    prepare.executeUpdate();
                    
                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully changed Password!");
                    alert.showAndWait();
                    
                    si_loginForm.setVisible(true);
                    np_newPasswordForm.setVisible(false);
                    
                    np_confrmPassword.setText("");
                    np_newPassword.setText("");
                    fp_question.getSelectionModel().clearSelection();
                    fp_answer.setText("");
                    fp_username.setText("");
                    
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Not match");
                alert.showAndWait();
            }
        }
    }
     public void forgotPassQuestionList(){
     List<String> listQ = new ArrayList<>();
        for(String data : questionList) {
            listQ.add(data);
        }
        ObservableList listData = FXCollections.observableArrayList(listQ);
        fp_question.setItems(listData);
        
    }
     
     public void backToLoginForm(){
        si_loginForm.setVisible(true);
        fp_questionForm.setVisible(false);
    }
    
    public void backToQuestionForm(){
        fp_questionForm.setVisible(true);
        np_newPasswordForm.setVisible(false);
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
                
                fp_questionForm.setVisible(false);
                si_loginForm.setVisible(true);
                np_newPasswordForm.setVisible(false);
                
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
                
                fp_questionForm.setVisible(false);
                si_loginForm.setVisible(true);
                np_newPasswordForm.setVisible(false);

            });

            slider.play();
        }
    }
    
}
