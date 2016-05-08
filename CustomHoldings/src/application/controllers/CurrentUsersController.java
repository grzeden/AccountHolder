/*
 * Author: Dennis Grzegorzewski
 * Date: 04/14/2016
 * FileName: application.controllers.CurrentUsersController.java
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
import application.dao.UserDao;
import application.models.User;
import application.tableModels.UserTableModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.control.TableColumn;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;

/**
 * Manages data and methods for the CurrentUsersView.
 * 
 * @author Dennis Grzegorzewski
 * @version 1.0, May 4, 2016
 *
 */
public class CurrentUsersController extends Controller implements Initializable {
  
  @FXML Label titleLabel;
  @FXML TableView<UserTableModel> currentUsersTable;
  @FXML TableColumn<UserTableModel, String> usernameCol;
  @FXML TableColumn<UserTableModel, String> passwordCol;
  @FXML TableColumn<UserTableModel, Boolean> clearedCol;
  @FXML TableColumn<UserTableModel, Boolean> adminCol;
  @FXML Button clearButton;
  @FXML Button saveCloseButton;
  @FXML Button adminButton;
  @FXML Button deleteButton;
  
  private ObservableList<UserTableModel> tableData;
  private Vector<UserTableModel> currentUsers = new Vector<>();

  /*
   * Class constructors.
   */
  /**
   * No-arg constructor.
   * 
   * @since 1.0
   */
  public CurrentUsersController() {
    super();
  } // end constructor CurrentUsersController.

  /**
   * Toggles user clearance when <code>Clear</code> button is clicked.
   * 
   * @since 1.0
   */
  @FXML
  public void clearUser() {
    
    // toggles user clearance in currentUsers vector
    currentUsers.get(currentUsersTable
                     .getSelectionModel()
                     .getSelectedIndex())
                     .setTableCleared(!currentUsers
                                      .get(currentUsersTable
                                           .getSelectionModel()
                                           .getSelectedIndex())
                                      .getTableCleared());

    // refresh tableview
    currentUsersTable.getColumns().get(0).setVisible(false);
    currentUsersTable.getColumns().get(0).setVisible(true);
    
  }

  /**
   * Toggles user admin status when <code>Admin</code> button is clicked.
   * 
   * @since 1.0
   */
  @FXML
  public void grantAdminUser() {
    
    // toggles user admin status in currentUsers vector
    currentUsers.get(currentUsersTable
                     .getSelectionModel()
                     .getSelectedIndex())
                     .setTableAdmin(!currentUsers
                                    .get(currentUsersTable
                                         .getSelectionModel()
                                         .getSelectedIndex())
                                     .getTableAdmin());

    // refresh tableview
    currentUsersTable.getColumns().get(0).setVisible(false);
    currentUsersTable.getColumns().get(0).setVisible(true);
    
  }

  @FXML
  public void deleteSelectedUser() {
    
    // confirmation dialog
    Alert cancelNewUser = new Alert(AlertType.CONFIRMATION);
    cancelNewUser.setTitle("Delete Account");
    cancelNewUser.setHeaderText("Warning! you are about to delete a users account.");
    cancelNewUser.setContentText("Are you sure you want to continue?");
    Optional<ButtonType> userAction = cancelNewUser.showAndWait();
    
    // user clicks OK button.
    if (userAction.isPresent() && userAction.get() == ButtonType.OK){
      
      User selectedUser = new User();
      selectedUser.setUsername(currentUsers
                               .get(currentUsersTable
                                    .getSelectionModel()
                                    .getSelectedIndex())
                               .getTableUsername());
      UserDao data = new UserDao();
      data.deleteUser(selectedUser);
      data.close();
      
      // reload tableview to refresh data
      try {
        
        // load CurrentUsersView.fxml.
        VBox root;
        root = FXMLLoader.load(getClass().getResource("/application/views/CurrentUsersView.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
        Main.stage.setScene(scene);
        
      } // end try.
      
      catch(Exception e) {
        System.out.println("Error occured while inflating UserView: " 
                           + e.getMessage());
      } // end catch.
      
    } //end if.
    
    // user clicks CANCEL button.
    else {
      
      // close dialog and return.
      return;
    } // end else.
    
  } // end method deleteSelectedUser.

  /**
   * Save changes in user clearance to the database when <code>Save/Close</code> button is clicked.
   * 
   * @since 1.0
   */
  @FXML
  public void saveEditedUsers() {
    
    // create a vector of User objects from vector of UserTableModel objects.
    int index = 0;
    Vector<User> editedUsers = new Vector<>();
    while (index < currentUsers.size()) {
      
      User editedUser = new User();
      
      editedUser.setUsername(currentUsers.get(index).getTableUsername());
      editedUser.setPassword(currentUsers.get(index).getTablePassword());
      editedUser.setCleared(currentUsers.get(index).getTableCleared());
      editedUser.setAdmin(currentUsers.get(index).getTableAdmin());
      
      editedUsers.addElement(editedUser);
      
      index++;
      
    } // end while
    
    UserDao data = new UserDao();
    data.saveEditedUsers(editedUsers);
    titleLabel.setText("User Changes Saved.");
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
    
  } //end method saveEditedUsers.

  /**
   * Initializes the TableView.
   * 
   * @see javafx.fxml.Initializable#initialize(java.net.URL, java.util.ResourceBundle)
   * @since 1.0
   */
  @Override
  public void initialize(URL arg0, ResourceBundle arg1) {
    
    // get a vector of User objects of all users from the database.
    UserDao data = new UserDao();
    Vector<User> userData = data.getCurrentUsers();
    data.close();
    
    // create a vector of UserTableModel objects from vector of User objects.
    int index = 0;
    while (index < userData.size()) {
      
      UserTableModel currentUser = new UserTableModel(null, null, false, false);
      
      currentUser.setTableUsername(userData.get(index).getUsername());
      currentUser.setTablePassword(userData.get(index).getPassword());
      currentUser.setTableCleared(userData.get(index).isCleared());
      currentUser.setTableAdmin(userData.get(index).isAdmin());
      
      currentUsers.addElement(currentUser);
      
      index++;
      
    } // end while
    
    // create an ObservableList of the vector of UserTableModel objects.
    tableData = FXCollections.observableArrayList(currentUsers);
    
    // link the ObservableList to the Tableview.
    currentUsersTable.getItems().setAll(this.tableData);
    
    // link the table cells to the UserTableModel objects fields.
    usernameCol.setCellValueFactory(new PropertyValueFactory<UserTableModel, String>("tableUsername"));
    passwordCol.setCellValueFactory(new PropertyValueFactory<UserTableModel, String>("tablePassword"));
    clearedCol.setCellValueFactory(new PropertyValueFactory<UserTableModel, Boolean>("tableCleared"));
    adminCol.setCellValueFactory(new PropertyValueFactory<UserTableModel, Boolean>("tableAdmin"));
    
    // select first item by default.
    currentUsersTable.getSelectionModel().selectFirst();
    
    // disable clear and admin buttons when list is empty.
    if (currentUsers.isEmpty()) {
      clearButton.setDisable (true);
      adminButton.setDisable (true);
    }
    
    // set the title displayed at the top of the application window.
    Main.stage.setTitle("Custom Holdings - Edit Current Users - " + getCurrentUser().getUsername() + "Logged In");
    // set the message displayed in the application window.
    titleLabel.setText("Clear, Grant Admin Privileges and Delete Users");
    
  } // end method initialize.
  
} // end class CurrentUsersController.