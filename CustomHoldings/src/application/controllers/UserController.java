/*
 * Author: Dennis Grzegorzewski
 * Date: 04/27/2016
 * FileName: application.controllers.UserController.java
 * Class: ITMD 510
 * Assignment: Final Project
 * Archive: grzegorzewskiITMD510FP.zip
 * Due: 04/30/2016 11:59PM
 */
package application.controllers;

import java.util.Optional;
import application.Main;
import application.models.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

/**
 * Manages data and methods for the UserView.
 * 
 * @author Dennis Grzegorzewski
 * @version 1.0, Apr 27, 2016
 *
 */
public class UserController extends Controller {
  
  /*
   * Declare instance fields.
   */
  @FXML Label titleLabel;               // to dynamically change the displayed title text.
  @FXML Menu usersMenu;                 // to dynamically disable users menu for non admin users.
  @FXML MenuItem saveSessionMenuItem;   // to dynamically disable save menu option when no session has been started.
  @FXML MenuItem resumeSessionMenuItem; // to dynamically disable resume menu option when no session has been saved.
  
  /*
   * Constructors.
   */
  /**
   * No-arg constructor.
   * 
   * @since 1.0
   */
  public UserController() {
    super();
  } // end controller UserController.
  
  /*
   * Methods.
   */
  /**
   * Loads the StartSessionView.
   * Starts a session to create set of user paths, groups and symbols.
   * 
   * @since 1.0
   */
  @FXML
  public void startUserSession() {
    
  } // end method startUserSession.
  
  /**
   * Loads the SaveSessionView.
   * 
   * @since 1.0
   */
  @FXML
  public void saveUserSession() {
    
  } // end method saveUserSession.
  
  /**
   * Loads the ResumeSessionView.
   * 
   * @since 1.0
   */
  @FXML
  public void resumeUserSession() {
    
  } // end method resumeUserSession.
  
  /**
   * Closes the application.
   * 
   * @since 1.0
   */
  @FXML
  public void closeUserSession() {
    // confirmation dialog
    Alert quitApp = new Alert(AlertType.CONFIRMATION);
    quitApp.setTitle("Close Custom Holdings");
    quitApp.setHeaderText("Close Application Confirmation");
    quitApp.setContentText("Are you sure you want to Quit?");
    Optional<ButtonType> userAction = quitApp.showAndWait();
    
    // user clicks OK button.
    if (userAction.isPresent() && userAction.get() == ButtonType.OK){
      // close application.
      System.exit(0);
    } //end if.
    
    // user clicks CANCEL button.
    else {
      // close dialog and return.
      return;
    } // end else.
    
  } // end method closeUserSession.
  
  /**
   * Loads the ClearUsersView.
   * 
   * @since 1.0
   */
  @FXML
  public void clearUsers() {
    
    try {
      
      VBox root;
      root = FXMLLoader.load(getClass().getResource("/application/views/ClearUsersView.fxml"));
      Scene scene = new Scene(root);
      scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
      Main.stage.setScene(scene);
      
    } // end try.
    
    catch(Exception e) {
      System.out.println("Error occured while inflating ClearUsersView: " 
                         + e.getMessage());
      e.printStackTrace();
    } // end catch.
    
  } // end method clearUsers.
  
  /**
   * Loads the CurrentUsersView.
   * 
   * @since 1.0
   */
  @FXML
  public void currentUsers() {
    
    try {
      
      VBox root;
      root = FXMLLoader.load(getClass().getResource("/application/views/CurrentUsersView.fxml"));
      Scene scene = new Scene(root);
      scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
      Main.stage.setScene(scene);
      
    } // end try.
    
    catch(Exception e) {
      System.out.println("Error occured while inflating CurrentUsersView: " 
                         + e.getMessage());
      e.printStackTrace();
    } // end catch.
    
  } // end method currentUsers.
  
  /**
   * Loads the LoginView.
   * 
   * @since 1.0
   */
  @FXML
  public void logoutUser() {
    
    // confirmation dialog
    Alert logoutUser = new Alert(AlertType.CONFIRMATION);
    logoutUser.setTitle("Logout User");
    logoutUser.setHeaderText("Logout Confirmation");
    logoutUser.setContentText("Are you sure you want to logout?");
    Optional<ButtonType> userAction = logoutUser.showAndWait();
    
    // user clicks OK button.
    if (userAction.isPresent() && userAction.get() == ButtonType.OK){
      
      // clears current user
      setCurrentUser(new User());
      
      try {
        
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
    
  } // end method logoutUser.
  
  /**
   * Loads the NewPathView.
   * Create a new path for the current user account.
   * 
   * @since 1.0
   */
  @FXML
  public void newPath() {
    
    try {
      
      VBox root;
      root = FXMLLoader.load(getClass().getResource("/application/views/NewPathView.fxml"));
      Scene scene = new Scene(root);
      scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
      Main.stage.setScene(scene);
      
    } // end try.
    
    catch(Exception e) {
      System.out.println("Error occured while inflating NewPathView: " 
                         + e.getMessage());
    } // end catch.
    
  } // end method newPath.
  
  /**
   * Loads the CurrentPathsView.
   * 
   * @since 1.0
   */
  @FXML
  public void currentPaths() {
    
    try {
      
      VBox root;
      root = FXMLLoader.load(getClass().getResource("/application/views/CurrentPathsView.fxml"));
      Scene scene = new Scene(root);
      scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
      Main.stage.setScene(scene);
      
    } // end try.
    
    catch(Exception e) {
      System.out.println("Error occured while inflating CurrentPathsView: " 
                         + e.getMessage());
    } // end catch.
    
  } // end method currentPaths.
  
  /**
   * Loads the NewGroupView.
   * 
   * @since 1.0
   */
  @FXML
  public void newGroup() {
    
    try {
      
      VBox root;
      root = FXMLLoader.load(getClass().getResource("/application/views/NewGroupView.fxml"));
      Scene scene = new Scene(root);
      scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
      Main.stage.setScene(scene);
      
    } // end try.
    
    catch(Exception e) {
      System.out.println("Error occured while inflating NewGroupView: " 
                         + e.getMessage());
    } // end catch.
    
  } // end method newGroup.
  
  /**
   * Loads the CurrentGroupsView.
   * 
   * @since 1.0
   */
  @FXML
  public void currentGroups() {
    
    try {
      
      VBox root;
      root = FXMLLoader.load(getClass().getResource("/application/views/CurrentGroupsView.fxml"));
      Scene scene = new Scene(root);
      scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
      Main.stage.setScene(scene);
      
    } // end try.
    
    catch(Exception e) {
      System.out.println("Error occured while inflating CurrentGroupsView: " 
                         + e.getMessage());
    } // end catch.
    
  } // end method currentGroups.
  
  /**
   * Loads the AddSymboldView.
   * Adds symbols.
   * 
   * @since 1.0
   */
  @FXML
  public void addSymbols() {
    
  } // end method addSymbols.

  /**
   * Loads the EditSymbolsdView.
   * Edits Symbols.
   * 
   * @since 1.0
   */
  @FXML
  public void editSymbols() {
    
  } // end method editSymbols.
  
  /**
   * This method is called by the FXMLLoader when initialization is complete.
   * 
   * @since 1.0
   */
  @FXML
  private void initialize() {
    
    // set the title displayed at the top of the application window.
    Main.stage.setTitle("Custom Holdings - " + getCurrentUser().getUsername() + " Logged In");
    
    // set the initial title text.
    titleLabel.setText("Welcome " + getCurrentUser().getUsername() + ".");

    // disables user menu if user admin flag is false.
    usersMenu.setDisable(!getCurrentUser().isAdmin());
    
  } //end method initialize.
  
} // end class UserController.