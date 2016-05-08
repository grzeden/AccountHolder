/* 
 * Author: Dennis Grzegorzewski
 * Date: 04/14/2016
 * FileName: application.controllers.CreateNewUserController.java
 * Class: ITMD 510
 * Assignment: Final Project
 * Archive: grzegorzewskiITMD510FP.zip
 * Due: 04/30/2016 11:59PM
 */
package application.controllers;

import java.util.Optional;
import java.util.regex.Pattern;

import application.Main;
import application.dao.UserDao;
import application.models.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.VBox;
import javafx.scene.control.PasswordField;

/**
 * Manages data and methods for the CreateNewUserView.
 * 
 * @author Dennis Grzegorzewski
 * @version 1.0, 04/14/2016
 */
public class CreateNewUserController extends Controller {
  
  /*
   * Declare instance fields.
   */
  @FXML Label titleLabel;
  @FXML TextField usernameTextField;
  @FXML PasswordField passwordITextField;
  @FXML PasswordField confirmITextField;
  
  /**
   * No-arg constructor.
   * 
   * @since 1.0
   */
  public CreateNewUserController() {
    super();
  } // end constructor CreateNewUserController.
  
  public void validateLogin() {
    
    // validate input
    
    // are there a spaces in the username.
    if (Pattern.compile(".*\\s.*").matcher(usernameTextField.getText()).find()) {
      titleLabel.setText("Username Must Contain No Spaces.");
      return;
    } // end if.
    
    // are there disallowed characters in the username.
    if (!Pattern.compile("[a-zA-Z0-9]*").matcher(usernameTextField.getText()).find()) {
      titleLabel.setText("Username Must Be Alphanumeric.");
      return;
    } // end if.
    
    // is there a lower-case letter in the password.
    if (!Pattern.compile("[a-z]").matcher(passwordITextField.getText()).find()) {
      titleLabel.setText("Password Must Have A Lower-Case Letter.");
      return;
    } // end if.
    
    // is there a capital letter in the password.
    if (!Pattern.compile("[A-Z]").matcher(passwordITextField.getText()).find()) {
      titleLabel.setText("Password Must Have A Capital Letter.");
      return;
    } // end if.
    
    // is there a number in the password.
    if (!Pattern.compile("[0-9]").matcher(passwordITextField.getText()).find()) {
      titleLabel.setText("Password Must Have A Number.");
      return;
    } // end if.
    
    // is the length of the password less than 12 characters.
    if (passwordITextField.getText().length() < 12) {
      titleLabel.setText("Password Must Be 12 Characters Long.");
      return;
    } // end if.
    
    // are there disallowed characters in the password.
    if (!Pattern.compile("[a-zA-Z0-9]*").matcher(passwordITextField.getText()).find()) {
      titleLabel.setText("Password Must Be Alphanumeric.");
      return;
    } // end if.
    
    // does the confirm password match the password.
    if (!confirmITextField.getText().equals(passwordITextField.getText())) {
      titleLabel.setText("Password Not Confirmed. Try again.");
      return;
    } // end if.
    
    // search database for username input.
    User userToCreate = new User();
    userToCreate.setUsername(usernameTextField.getText());
    userToCreate.setPassword(passwordITextField.getText());
    UserDao data = new UserDao();
    User resultOfSearch = data.findUser(userToCreate);
    
    // if username already exists.
    if (!resultOfSearch.getUsername().equals("")) {
      titleLabel.setText("Username already in use. Try again.");
      data.close();
      return;
    } //end if.
    
    // username is unique, insert new user in the database, cleanup.
    data.createUser(userToCreate);
    titleLabel.setText("New User Submitted For Approval.");
    data.close();
    
    try {
      
      // load LoginView.fxml.
      VBox root;
      root = FXMLLoader.load(getClass().getResource("/application/views/LoginView.fxml"));
      Scene scene = new Scene(root);
      scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
      Main.stage.setScene(scene);
      
    } // end try.
    
    catch(Exception e) {
      System.out.println("Error occured while inflating LoginView: " 
                         + e.getMessage());
    } // end catch.
    
  } //end method validateLogin.
  
  @FXML
  public void cancelNewLogin() {
    
    // confirmation dialog
    Alert cancelNewUser = new Alert(AlertType.CONFIRMATION);
    cancelNewUser.setTitle("New Account Cancellation");
    cancelNewUser.setHeaderText("Cancelation Confirmation");
    cancelNewUser.setContentText("Are you sure you want to cancel creating a new account?");
    Optional<ButtonType> userAction = cancelNewUser.showAndWait();
    
    // user clicks OK button.
    if (userAction.isPresent() && userAction.get() == ButtonType.OK){
      
      try {
        
        // load LoginView.fxml.
        VBox root;
        root = FXMLLoader.load(getClass().getResource("/application/views/LoginView.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
        Main.stage.setScene(scene);
        
      } // end try.
      
      catch(Exception e) {
        System.out.println("Error occured while inflating LoginView: " 
                           + e.getMessage());
      } // end catch.
      
      return;
      
    } //end if.
    
    // user clicks CANCEL button.
    else {
      // close dialog and return.
      return;
    } // end else.
  }
  
  /**
   * This method is called by the FXMLLoader when initialization is complete.
   * 
   * @since 1.0
   */
  @FXML
  void initialize() {
    
    // set the title displayed at the top of the application window.
    Main.stage.setTitle("Custom Holdings - Create A New Login");
    
    // set the initial title text.
    titleLabel.setText("Please Enter a Username and Password");
    
  } // end method initialize.
  
} // end class CreateNewUserController.