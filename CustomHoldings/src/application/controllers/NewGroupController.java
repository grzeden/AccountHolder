/*
 * Author: Dennis Grzegorzewski
 * Date: Apr 30, 2016
 * FileName: pplication.controllers.NewGroupController.java
 * Class: ITMD 510
 * Assignment: 
 * Archive: grzegorzewskiITMD510????.zip
 * Due: Apr 30, 2016
 */
package application.controllers;

import java.util.Optional;
import java.util.Vector;
import java.util.regex.Pattern;

import application.Main;
import application.dao.GroupDao;
import application.dao.PathDao;
import application.models.Group;
import application.models.Path;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextArea;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.scene.control.ComboBox;

/**
 * Manages data and methods for the NewGroupView.
 * 
 * @author Dennis Grzegorzewski
 * @version 1.0, Apr 30, 2016
 */
public class NewGroupController extends Controller {
  
  /*
   * Define class fields
   */
  @FXML private Label titleLabel;
  @FXML private TextField currentUserTextField;
  @FXML private TextField nameTextField;
  @FXML private TextArea descriptionTextArea;
  @FXML private Button saveButton;
  @FXML private Button cancelButton;
  @FXML private ComboBox<String> pathComboBox = new ComboBox<>();

  
  /*
   * Constructors.
   */
  /**
   * No-arg constructor.
   * 
   * @since 1.0
   */
  public NewGroupController() {
    super();
  } // end constructor NewGroupController.
  
  /*
   * Methods.
   */
  /**
   * Saves created group to the database.
   * 
   * @since 1.0
   */
  @FXML
  public void saveGroup() {
    
    // validate input
    
    // is the path name blank or empty.
    if (nameTextField.getText().equals("")) {
      titleLabel.setText("Group Name Is Blank. Try Again.");
      return;
    } // end if.
    
    // are there disallowed characters in the path name.
    if (!Pattern.compile("[a-zA-Z0-9]*").matcher(nameTextField.getText()).matches()) {
      titleLabel.setText("Group Name Must Be Alphanumeric.");
      return;
    } // end if.
    
    // search database for username input.
    Group groupToCreate = new Group();
    groupToCreate.setName(nameTextField.getText());
    groupToCreate.setDescription(descriptionTextArea.getText());
    groupToCreate.setPathName(pathComboBox.getValue ());
    GroupDao data = new GroupDao();
    Group resultOfSearch = data.findGroup(groupToCreate);
    
    // if path already exists.
    if (resultOfSearch.getPathName().equals(groupToCreate.getPathName())) {
      titleLabel.setText("Group Already In Use. Try again.");
      data.close();
      return;
    } //end if.
    
    // path is unique, insert into the database, cleanup.
    data.addGroup(groupToCreate);
    data.close();
    titleLabel.setText("New Group Added.");
    
    // clear the text fields
    nameTextField.clear();
    descriptionTextArea.clear();
    
  } //end method saveGroup.

  /**
   * Creates a confirmation dialog, then cancels the creation of a new group or continues.
   * 
   * @since 1.0
   */
  @FXML
  public void cancelGroup() {
    
    // confirmation dialog
    Alert cancelNewGroup = new Alert(AlertType.CONFIRMATION);
    cancelNewGroup.setTitle("New Group Cancellation");
    cancelNewGroup.setHeaderText("Cancelation Confirmation");
    cancelNewGroup.setContentText("Are you sure you want to cancel creating a new group?");
    Optional<ButtonType> userAction = cancelNewGroup.showAndWait();
    
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
    
  } //end method cancelGroup.
  
  /**
   * Select a path.
   * 
   * @since 1.0
   */
  @FXML
  public void pickPath() {
    
  } //end method pickPath.
  
  /**
   * This method is called by the FXMLLoader when initialization is complete.
   * 
   * @since 1.0
   */
  @FXML
  void initialize() {
  
    // get a vector of Path objects of the current users from the database.
    PathDao data = new PathDao();
    Vector<Path> userPathData = data.getUserPaths(getCurrentUser());
    data.close();
    
    // populate pathComboBox with path names of the current user.
    int index = 0;
    while (index < userPathData.size()) {
      pathComboBox.getItems().add(userPathData.get(index).getName());
      index++;
    } // end while
    
    // set default item in pathComboBox
    pathComboBox.getSelectionModel().selectFirst();
    
    // set the title displayed at the top of the application window.
    Main.stage.setTitle("Custom Holdings - Create New Group " + getCurrentUser().getUsername());
    
    // set the initial title text.
    titleLabel.setText("Add A New Group");
    
    // set the currentUserTextField text.
    currentUserTextField.setText(getCurrentUser().getUsername());
    
  } // end method initialize.
  
} // end class NewGroupController.