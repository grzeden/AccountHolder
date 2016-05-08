/* 
 * Author: Dennis Grzegorzewski
 * Date: 04/14/2016
 * FileName: application.controllers.Controller.java
 * Class: ITMD 510
 * Assignment: Final Project
 * Archive: grzegorzewskiITMD510FP.zip
 * Due: 05/05/2016 11:59PM
 */
package application.controllers;

import application.models.User;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * Parent class for all controllers. 
 * Holds <code>currentUser</code> field inherited by all controllers.
 * 
 * @author Dennis Grzegorzewski
 * @version 1.0, 04/14/2016
 */
public class Controller {
  
  /*
   * Define and initialize class fields.
   */
  private static User currentUser = new User();
  
  /**
   * No-arg constructor.
   * 
   * @since 1.0
   */
  public Controller() {
  } //end constructor Controller.
  
  /**
   * Gets the current user.
   * 
   * @return currentUser - the current user.
   * @see #currentUser
   * @see #setCurrentUser(User)
   * @since 1.0
   */
  public User getCurrentUser() {
    return currentUser;
  } // end method getCurrentUser.
  
  /**
   * Sets the current user.
   * 
   * @param newCurrentUser  the new current user to set.
   * @see #currentUser
   * @see #getCurrentUser()
   * @since 1.0
   */
  public void setCurrentUser(User newCurrentUser) {
    Controller.currentUser = newCurrentUser;
  } // end method setCurrentUser.
  
  /**
  *  Creates dialog triggered by about menu.
   * 
   * @since 1.0
   */
  @FXML
  public void about() {
    // information dialog
    Alert aboutApp = new Alert(AlertType.INFORMATION);
    aboutApp.setTitle("About Custom Holdings");
    aboutApp.setHeaderText("Custom Holdings Maker");
    aboutApp.setContentText("This application helps you gather and process the data to create your own set of " 
                            + "custom holdings paths for your library. Using custom holdings paths will help " 
                            + "you to keep your costs down and increase the speed of your interlibrary loan service.");
    aboutApp.show();
    
  } // end method about.
  
} // end class CustomHoldingsController.