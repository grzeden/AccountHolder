/*
 * Author: Dennis Grzegorzewski
 * Date: 04/29/2016
 * FileName: application.controllers.NewPathController.java
 * Class: ITMD 510
 * Assignment: Final Project
 * Archive: grzegorzewskiITMD510FP.zip
 * Due: 04/30/2016 11:59PM
 */
package application.controllers;

import java.util.Optional;
import java.util.regex.Pattern;

import application.Main;
import application.dao.PathDao;
import application.models.Path;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.VBox;

/**
 * Manages data and methods for the NewPathView.
 * 
 * @author Dennis Grzegorzewski
 * @version 1.0, 04/29/2016
 *
 */
public class NewPathController extends Controller {
  
  /*
   * Define class fields
   */
  @FXML private Label titleLabel;
  @FXML private TextField currentUserTextField;
  @FXML private TextField nameTextField;
  @FXML private TextArea descriptionTextArea;
  @FXML private Button saveButton;
  @FXML private Button cancelButton;

  /*
   * Constructors.
   */
  /**
   * No-arg constructor.
   * 
   * @since 1.0
   */
  public NewPathController() {
    super();
  } //end constructor NewPathController.
  
  /*
   * Methods.
   */
  /**
   * Saves created path to the database.
   * 
   * @since 1.0
   */
  @FXML
  public void savePath() {
    
    // validate input
    
    // is the path name blank or empty.
    if (nameTextField.getText().equals("")) {
      titleLabel.setText("Path Name Is Blank. Try Again.");
      return;
    } // end if.
    
    // are there disallowed characters in the path name.
    if (!Pattern.compile("[a-zA-Z0-9]*").matcher(nameTextField.getText()).matches()) {
      titleLabel.setText("Path Name Must Be Alphanumeric.");
      return;
    } // end if.
    
    // search database for username input.
    Path pathToCreate = new Path();
    pathToCreate.setName(nameTextField.getText());
    pathToCreate.setDescription(descriptionTextArea.getText());
    pathToCreate.setUsername(getCurrentUser().getUsername());
    PathDao data = new PathDao();
    Path resultOfSearch = data.findPath(pathToCreate);
    
    // if path already exists.
    if (resultOfSearch.getUsername().equals(pathToCreate.getUsername())) {
      titleLabel.setText("Path Already In Use. Try again.");
      data.close();
      return;
    } //end if.
    
    // path is unique, insert into the database, cleanup.
    data.addPath(pathToCreate);
    data.close();
    titleLabel.setText("New Path Added.");
    
    // clear the text fields
    nameTextField.clear();
    descriptionTextArea.clear();
    
  } //end method savePath.

  /**
   * Creates a confirmation dialog, then cancels the creation of a new path or continues.
   * 
   * @since 1.0
   */
  @FXML
  public void cancelPath() {
    
    // confirmation dialog
    Alert cancelNewPath = new Alert(AlertType.CONFIRMATION);
    cancelNewPath.setTitle("New Path Cancellation");
    cancelNewPath.setHeaderText("Cancelation Confirmation");
    cancelNewPath.setContentText("Are you sure you want to cancel creating a new path?");
    Optional<ButtonType> userAction = cancelNewPath.showAndWait();
    
    // user clicks OK button.
    if (userAction.isPresent() && userAction.get() == ButtonType.OK){
      
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
      
      return;
      
    } // end if.
    
    // user clicks CANCEL button.
    else {
      // close dialog and return.
      return;
    } // end else.
    
  } //end method cancelPath.
  
  /**
   * This method is called by the FXMLLoader when initialization is complete.
   * 
   * @since 1.0
   */
  @FXML
  void initialize() {

    // set the title displayed at the top of the application window.
    Main.stage.setTitle("Custom Holdings - Create New Path " + getCurrentUser().getUsername());
    
    // set the initial title text.
    titleLabel.setText("Add A New Path");
    
    // set the currentUserTextField text.
    currentUserTextField.setText(getCurrentUser().getUsername());
    
  } // end method initialize.
  
} //end class NewPathController.