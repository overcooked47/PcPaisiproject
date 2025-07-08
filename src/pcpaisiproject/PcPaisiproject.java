/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML.java to edit this template
 */
package pcpaisiproject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Shayan
 */
public class PcPaisiproject extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
         System.out.println("Trying different paths:");
    
    System.out.println("Path 1: " + getClass().getResource("FXMLDocument.fxml"));
    System.out.println("Path 2: " + getClass().getResource("/FXMLDocument.fxml"));
    System.out.println("Path 3: " + getClass().getResource("/pcpaisiproject/FXMLDocument.fxml"));
    System.out.println("Path 4: " + getClass().getResource("pcpaisiproject/FXMLDocument.fxml"));
         FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("/pcpaisiproject/FXMLDocument.fxml"));
     if (loader.getLocation() == null) {
        System.out.println("Could not find FXML file!");
        return;
    }
        
        Parent root = FXMLLoader.load(getClass().getResource("/pcpaisiproject/FXMLDocument.fxml"));
        
        Scene scene = new Scene(root);
        stage.setTitle("PC Paisi");
        stage.setMinHeight(450);
        stage.setMinWidth(620);
        
        
        
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
