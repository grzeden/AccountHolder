/*
 * Author: Dennis Grzegorzewski
 * Date: 04/24/2016
 * FileName: application.controllers.CurrentGroupsController.java
 * Class: ITMD 510
 * Assignment: Final Project
 * Archive: grzegorzewskiITMD510FP.zip
 * Due: 04/30/2016 11:59PM
 */
package application.controllers;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Vector;

import application.Main;
import application.dao.GroupDao;
import application.models.Group;
import application.tableModels.GroupTableModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;


/**
 * Manages data and methods for the CurrentGroupsView.
 * 
 * @author Dennis Grzegorzewski
 * @version 1.0, May 4, 2016
 *
 */
public class CurrentGroupsController extends Controller implements Initializable {
  
  /*
   * Define class fields
   */
  @FXML private Label titleLabel;
  @FXML private TableView<GroupTableModel> currentUserGroupsTable;
  @FXML private TableColumn<GroupTableModel, String> nameCol;
  @FXML private TableColumn<GroupTableModel, String> descriptionCol;
  @FXML private TableColumn<GroupTableModel, String> pathNameCol;
  @FXML private Button deleteButton;
  @FXML private Button editButton;
  
  private ObservableList<GroupTableModel> tableData;
  private Vector<GroupTableModel> currentUserGroups = new Vector<>();
  
  /*
   * Constructors.
   */
  /**
   * No-arg constructor.
   * 
   * @since 1.0
   */
  public CurrentGroupsController() {
    super();
  } //end constructor CurrentGroupsController.
  
  /*
   * Methods.
   */
  /**
   * Saves edited groups to the database.
   * 
   * @since 1.0
   */
  @FXML
  public void saveEditedGroups() {
    
    // create a vector of Group objects from vector of UserTableModel objects.
    int index = 0;
    Vector<Group> editedGroups = new Vector<>();
    while (index < currentUserGroups.size()) {
      
      Group editedGroup = new Group();
      
      editedGroup.setName(currentUserGroups.get(index).getTableName());
      editedGroup.setDescription(currentUserGroups.get(index).getTableDescription());
      editedGroup.setPathName(currentUserGroups.get(index).getTablePathName());
      
      editedGroups.addElement(editedGroup);
      
      index++;
      
    } // end while
    
    GroupDao data = new GroupDao();
    data.saveEditedGroups(editedGroups);
    titleLabel.setText("Group Changes Saved.");
    data.close();
    
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
    
  } //end method saveEditedGroups.
  
  /**
   * Edits selected group in the viewtable.
   * 
   * @since 1.0
   */
  @FXML
  public void editGroup() {
    
  }  //end method editGroup.
  
  /**
   * Deletes selected group from the database.
   * 
   * @since 1.0
   */
  @FXML
  public void deleteSelectedGroup() {
    
    // confirmation dialog
    Alert cancelDeleteGroup = new Alert(AlertType.CONFIRMATION);
    cancelDeleteGroup.setTitle("Delete Group");
    cancelDeleteGroup.setHeaderText("Warning! you are about to delete a group.");
    cancelDeleteGroup.setContentText("Are you sure you want to continue?");
    Optional<ButtonType> userAction = cancelDeleteGroup.showAndWait();
    
    // user clicks OK button.
    if (userAction.isPresent() && userAction.get() == ButtonType.OK){
      
      Group selectedGroup = new Group();
      selectedGroup.setName(currentUserGroups
                               .get(currentUserGroupsTable
                                    .getSelectionModel()
                                    .getSelectedIndex())
                               .getTableName());
      GroupDao data = new GroupDao();
      data.deleteGroup(selectedGroup);
      data.close();
      
      // reload tableview to refresh data
      try {
        
        // load CurrentGroupsView.fxml.
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
      
    } //end if.
    
    // user clicks CANCEL button.
    else {
      
      // close dialog and return.
      return;
    } // end else.
    
  } //end method deleteSelectedGroup.

  /**
   * This method is called by the FXMLLoader when initialization is complete.
   * 
   * @see javafx.fxml.Initializable#initialize(java.net.URL, java.util.ResourceBundle)
   * @since 1.0
   */
  @Override
  public void initialize(URL arg0, ResourceBundle arg1) {
    
    // get a vector of Group objects of the current user from the database.
    GroupDao data = new GroupDao();
    Vector<Group> groupData = data.getUserGroups(getCurrentUser());
    data.close();
    
    // create a vector of UserTableModel objects from vector of the current users Path objects.
    int index = 0;
    while (index < groupData.size()) {
      
      GroupTableModel currentUserGroup = new GroupTableModel(null, null, null);
      
      currentUserGroup.setTableName(groupData.get(index).getName());
      currentUserGroup.setTableDescription(groupData.get(index).getDescription());
      currentUserGroup.setTablePathName(groupData.get(index).getPathName());
      
      currentUserGroups.addElement(currentUserGroup);
      
      index++;
      
    } // end while
    
    // create an ObservableList of the vector of UserTableModel objects.
    tableData = FXCollections.observableArrayList(currentUserGroups);
    
    // link the ObservableList to the Tableview.
    currentUserGroupsTable.getItems().setAll(this.tableData);
    
    // link the table cells to the UserTableModel objects fields.
    nameCol.setCellValueFactory(new PropertyValueFactory<GroupTableModel, String>("tableName"));
    descriptionCol.setCellValueFactory(new PropertyValueFactory<GroupTableModel, String>("tableDescription"));
    
    // select first item by default.
    currentUserGroupsTable.getSelectionModel().selectFirst();
    
    // disable clear and admin buttons when list is empty.
    if (currentUserGroups.isEmpty()) {
      editButton.setDisable (true);
      deleteButton.setDisable (true);
    }
    
    // set the title displayed at the top of the application window.
    Main.stage.setTitle("Custom Holdings - Edit User Groups - " + getCurrentUser().getUsername() + "Logged In");
    // set the message displayed in the application window.
    titleLabel.setText("Edit and Delete User Groups");
    
  } //end method initialize.
  
} //end class CurrentGroupsController.
