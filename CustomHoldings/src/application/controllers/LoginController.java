/* 
 * Author: Dennis Grzegorzewski
 * Date: 04/14/2016
 * FileName: application.controllers.LoginController.java
 * Class: ITMD 510
 * Assignment: Final Project
 * Archive: grzegorzewskiITMD510FP.zip
 * Due: 04/30/2016 11:59PM
 */
package application.controllers;

import application.Main;
import application.dao.UserDao;
import application.models.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.control.PasswordField;

/**
 * Manages data and methods for the LoginView.
 * 
 * @author Dennis Grzegorzewski
 * @version 1.0, 04/14/2016
 */
public class LoginController extends Controller {
  
  /*
   * Declare instance fields.
   */
  @FXML private Label titleLabel;
  @FXML private TextField usernameTextField;
  @FXML private PasswordField passwordTextField;
  
  /**
   * No-arg constructor.
   * 
   * @since 1.0
   */
  public LoginController() {
    super();
  } //end constructor LoginController.
  
  /**
   * Validates and submits user login.
   * 
   * @since 1.0
   */
  @FXML
  public void submitLogin() {
    
    User userToFind = new User();
    userToFind.setUsername(this.usernameTextField.getText());
    UserDao data = new UserDao();
    User resultOfSearch = data.findUser(userToFind);
    data.close();

    //Validate input
    
    // if username does not exist
    if (resultOfSearch.getUsername().equals("")) {
      titleLabel.setText("Username not found. Try again.");
      return;
    } //end if.
    
    // if username does not match password
    if (!resultOfSearch.getPassword().equals(this.passwordTextField.getText())) {
      titleLabel.setText("Incorrect Password. Try again.");
      return;
    } //end else if.
    
    // if username is not cleared
    if (!resultOfSearch.isCleared()) {
      titleLabel.setText("Username not cleared. Try Later.");
      return;
    } //end else if.
    
    else {
      titleLabel.setText("Login Accepted.");
      // saves current user in super controller class for all controllers
      setCurrentUser(resultOfSearch);
      
    } // end else.
    
    try {
      
      // load UserView.fxml.
      VBox root;
      root = FXMLLoader.load(getClass().getResource("/application/views/UserView.fxml"));
      Scene scene = new Scene(root);
      scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
      Main.stage.setScene(scene);
      
    } // end try.
    
    catch(Exception e) {
      System.out.println("Error occured while inflating UserView: " 
                         + e.getMessage());
    } // end catch.
    
  } // end method submitLogin.
  
  /**
   * Loads the CreateNewUserView.
   * 
   * @since 1.0
   */
  @FXML
  public void createNewUser() {
    
    try {
      
      // load CreateNewUserView.fxml.
      VBox root;
      root = FXMLLoader.load(getClass().getResource("/application/views/CreateNewUserView.fxml"));
      Scene scene = new Scene(root);
      scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
      Main.stage.setScene(scene);
      
    } // end try.
    
    catch(Exception e) {
      System.out.println("Error occured while inflating CreateNewUserView: " 
                         + e.getMessage());
    } // end catch.
    
  } // end method createNewUser.
  
  /**
   * This method is called by the FXMLLoader when initialization is complete.
   * 
   * @since 1.0
   */
  @FXML
  private void initialize() {
    
    // set the title displayed at the top of the application window.
    Main.stage.setTitle("Custom Holdings - Please Login");
    
    // set the initial title text.
    titleLabel.setText("Please Enter a Username and Password");
    
  } // end method initialize.
  
} // end class LoginController.