/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package pcpaisiproject;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;

/**
 *
 * @author Shayan
 */
public class FXMLDocumentController implements Initializable {

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

    private String[] questionList = {"What is your favourite color?", "What is your favourite food?", "What is your birth date?"};

    public void regLquestionlist() {
        List<String> listQ = new ArrayList<>();
        for (String data : questionList) {
            listQ.add(data);
        }
        ObservableList listData = FXCollections.observableArrayList(listQ);
        su_question.setItems(listData);

    }

    @FXML
    public void switchForm(ActionEvent event) {
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
