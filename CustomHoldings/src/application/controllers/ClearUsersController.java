/*
 * Author: Dennis Grzegorzewski
 * Date: 04/27/2016
 * FileName: application.controllers.ClearUsersController.java
 * Class: ITMD 510
 * Assignment: Final Project
 * Archive: grzegorzewskiITMD510FP.zip
 * Due: 04/30/2016 11:59PM
 */
package application.controllers;

import java.net.URL;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * Manages data and methods for the ClearUserView.
 * 
 * @author Dennis Grzegorzewski
 * @version 1.0, 04/27/2016
 */
public class ClearUsersController extends Controller implements Initializable {
  
  /*
   * Declare and initialize class fields.
   */
  @FXML Label titleLabel;
  @FXML private TableView<UserTableModel> unclearedUsersTable;  
  @FXML private TableColumn<UserTableModel, String> usernameCol; 
  @FXML private TableColumn<UserTableModel, String> passwordCol;
  @FXML private TableColumn<UserTableModel, Boolean> clearedCol;
  @FXML private TableColumn<UserTableModel, Boolean> adminCol;
  @FXML Button saveCloseButton;
  @FXML Button clearButton;
  @FXML Button adminButton;
  
  private ObservableList<UserTableModel> tableData;
  private Vector<UserTableModel> unclearedUsers = new Vector<>();
  
  /*
   * Class constructors.
   */
  /**
   * No-arg constructor.
   * 
   * @since 1.0
   */
  public ClearUsersController(){
    super();
  } // end constructor ClearUsersController.
  
  /**
   * Toggles user clearance when <code>Clear</code> button is clicked.
   * 
   * @since 1.0
   */
  @FXML public void clearUser() {
    
    // toggles user clearance in UserTableModel vector
    unclearedUsers.get(unclearedUsersTable
                       .getSelectionModel()
                       .getSelectedIndex())
                  .setTableCleared(!unclearedUsers
                                   .get(unclearedUsersTable
                                        .getSelectionModel()
                                        .getSelectedIndex())
                                   .getTableCleared());

    // refresh tableview
    unclearedUsersTable.getColumns().get(0).setVisible(false);
    unclearedUsersTable.getColumns().get(0).setVisible(true);
    
  } // end method clearUser.
  
  /**
   * Toggles user admin status when <code>Admin</code> button is clicked.
   * 
   * @since 1.0
   */
  @FXML
  public void grantAdminUser() {
    
    // toggles user clearance in UserTableModel vector
    unclearedUsers.get(unclearedUsersTable
                       .getSelectionModel()
                       .getSelectedIndex())
                  .setTableAdmin(!unclearedUsers
                                 .get(unclearedUsersTable
                                      .getSelectionModel()
                                      .getSelectedIndex())
                                 .getTableAdmin());

    // refresh tableview
    unclearedUsersTable.getColumns().get(0).setVisible(false);
    unclearedUsersTable.getColumns().get(0).setVisible(true);
    
  } // end method grantAdminUser.
  
  /**
   * Save changes in user clearance to the database when <code>Save/Close</code> button is clicked.
   * 
   * @since 1.0
   */
  @FXML
  public void saveClearedUsers() {
    
    // create a vector of User objects from vector of UserTableModel objects.
    int index = 0;
    Vector<User> clearedUsers = new Vector<>();
    while (index < unclearedUsers.size()) {
      
      User clearedUser = new User();
      
      clearedUser.setUsername(unclearedUsers.get(index).getTableUsername());
      clearedUser.setPassword(unclearedUsers.get(index).getTablePassword());
      clearedUser.setCleared(unclearedUsers.get(index).getTableCleared());
      clearedUser.setAdmin(unclearedUsers.get(index).getTableAdmin());
      
      clearedUsers.addElement(clearedUser);
      
      index++;
      
    } // end while
    
    UserDao data = new UserDao();
    data.saveEditedUsers(clearedUsers);
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
    
    
  } // end method saveClearedUsers.
  
  /**
   * Initializes the TableView.
   * 
   * @see javafx.fxml.Initializable#initialize(java.net.URL, java.util.ResourceBundle)
   * @since 1.0
   */
  @Override
  public void initialize(URL arg0, ResourceBundle arg1) {
    
    // get a vector of User objects of uncleared users from the database.
    UserDao data = new UserDao();
    Vector<User> userData = data.getUnclearedUsers();
    data.close();
    
    // create a vector of UserTableModel objects from vector of User objects.
    int index = 0;
    while (index < userData.size()) {
      
      UserTableModel unclearedUser = new UserTableModel(null, null, false, false);
      
      unclearedUser.setTableUsername(userData.get(index).getUsername());
      unclearedUser.setTablePassword(userData.get(index).getPassword());
      unclearedUser.setTableCleared(userData.get(index).isCleared());
      unclearedUser.setTableAdmin(userData.get(index).isAdmin());
      
      unclearedUsers.addElement(unclearedUser);
      
      index++;
      
    } // end while
    
    // create an ObservableList of the vector of UserTableModel objects.
    tableData = FXCollections.observableArrayList(unclearedUsers);
    
    // link the ObservableList to the Tableview.
    unclearedUsersTable.getItems().setAll(this.tableData);
    
    // link the table cells to the UserTableModel objects fields.
    usernameCol.setCellValueFactory(new PropertyValueFactory<UserTableModel, String>("tableUsername"));
    passwordCol.setCellValueFactory(new PropertyValueFactory<UserTableModel, String>("tablePassword"));
    clearedCol.setCellValueFactory(new PropertyValueFactory<UserTableModel, Boolean>("tableCleared"));
    adminCol.setCellValueFactory(new PropertyValueFactory<UserTableModel, Boolean>("tableAdmin"));
    
    // select first item by default.
    unclearedUsersTable.getSelectionModel().selectFirst();
    
    // disable clear and admin buttons when list is empty.
    if (unclearedUsers.isEmpty()) {
      clearButton.setDisable (true);
      adminButton.setDisable (true);
    }
    
    // set the title displayed at the top of the application window.
    Main.stage.setTitle("Custom Holdings - Clear Users - " + getCurrentUser().getUsername() + "Logged In");
    // set the message displayed in the application window.
    titleLabel.setText("Clear Users and Grant Admin Privileges");
    
  } // end method initialize.
  
} // end class ClearUsersController.