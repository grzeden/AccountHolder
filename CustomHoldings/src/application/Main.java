/*
 * Author: Dennis Grzegorzewski
 * Date: 04/14/2016
 * FileName: application.Main.java
 * Class: ITMD 510
 * Assignment: Final Project
 * Archive: grzegorzewskiITMD510FP.zip
 * Due: 05/05/2016 11:59PM
 */
package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

/**
 * Launches Custom Holdings application Login controller.
 * 
 * @author Dennis Grzegorzewski
 * @version 1.0, 04/14/2016
 *
 */
public class Main extends Application {
  
  /*
   * Declare class fields.
   */
  public static Stage stage;
  
  /**
   * No-arg constructor.
   * 
   * @since 1.0
   */
  public Main() {
    super();
  } // end constructor Main.
  
  /**
   * Starts application.
   * 
   * @see javafx.application.Application#start(javafx.stage.Stage)
   * @since 1.0
   */
  @Override
  public void start(Stage primaryStage) {
    
    try {
      
      stage = primaryStage;
      BorderPane loginRoot = new BorderPane();
      Scene scene = new Scene(loginRoot);
      loginRoot.setCenter(FXMLLoader.load(getClass().getResource("views/LoginView.fxml")));
      scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
      stage.setScene(scene);
      // make window visible.
      stage.show();
      
    } //end try.
    
    catch (Exception e) {
      System.out.println("Error occured while inflating LoginView: "
                         + e.getMessage());
    } //end catch.
    
  } //end start method.
  
  /**
   * Launch JavaFX application.
   * 
   * @param args[] command-line arguments (not used)
   * @since 1.0
   */
  public static void main(String[] args) {
    launch(args);
  } //end method main.
  
} //end class Main.