/*
 * Author: Dennis Grzegorzewski
 * Date: 04/24/2016
 * FileName: application.controllers.CurrentPathsController.java
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
import application.dao.PathDao;
import application.models.Path;
import application.tableModels.PathTableModel;
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
 * Manages data and methods for the CurrentPathsView.
 * 
 * @author Dennis Grzegorzewski
 * @version 1.0, May 4, 2016
 *
 */
public class CurrentPathsController extends Controller implements Initializable {
  
  /*
   * Define class fields
   */
  @FXML private Label titleLabel;
  @FXML private TableView<PathTableModel> currentUserPathsTable;
  @FXML private TableColumn<PathTableModel, String> nameCol;
  @FXML private TableColumn<PathTableModel, String> descriptionCol;
  @FXML private Button deleteButton;
  @FXML private Button editButton;
  
  private ObservableList<PathTableModel> tableData;
  private Vector<PathTableModel> currentUserPaths = new Vector<>();
  
  /*
   * Constructors.
   */
  /**
   * No-arg constructor.
   * 
   * @since 1.0
   */
  public CurrentPathsController() {
    super();
  } //end constructor CurrentPathsController.
  
  /*
   * Methods.
   */
  /**
   * Saves edited paths to the database.
   * 
   * @since 1.0
   */
  @FXML
  public void saveEditedPaths() {
    
    // create a vector of Path objects from vector of UserTableModel objects.
    int index = 0;
    Vector<Path> editedPaths = new Vector<>();
    while (index < currentUserPaths.size()) {
      
      Path editedPath = new Path();
      
      editedPath.setName(currentUserPaths.get(index).getTableName());
      editedPath.setDescription(currentUserPaths.get(index).getTableDescription());
      editedPath.setUsername(getCurrentUser().getUsername ());
      
      editedPaths.addElement(editedPath);
      
      index++;
      
    } // end while
    
    PathDao data = new PathDao();
    data.saveEditedPaths(editedPaths);
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
    
  } //end method saveEditedPaths.
  
  /**
   * Edits selected path in the viewtable.
   * 
   * @since 1.0
   */
  @FXML
  public void editPath() {
    
  } //end method editPath.
  
  /**
   * Deletes selected path from the database.
   * 
   * @since 1.0
   */
  @FXML
  public void deleteSelectedPath() {
    
    // confirmation dialog
    Alert cancelDeletePath = new Alert(AlertType.CONFIRMATION);
    cancelDeletePath.setTitle("Delete Path");
    cancelDeletePath.setHeaderText("Warning! you are about to delete a path.");
    cancelDeletePath.setContentText("Are you sure you want to continue?");
    Optional<ButtonType> userAction = cancelDeletePath.showAndWait();
    
    // user clicks OK button.
    if (userAction.isPresent() && userAction.get() == ButtonType.OK){
      
      Path selectedPath = new Path();
      selectedPath.setName(currentUserPaths
                               .get(currentUserPathsTable
                                    .getSelectionModel()
                                    .getSelectedIndex())
                               .getTableName());
      PathDao data = new PathDao();
      data.deletePath(selectedPath);
      data.close();
      
      // reload tableview to refresh data
      try {
        
        // load CurrentPathsView.fxml.
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
      
    } //end if.
    
    // user clicks CANCEL button.
    else {
      
      // close dialog and return.
      return;
    } // end else.
    
  } //end method deleteSelectedPath.

  /**
   * This method is called by the FXMLLoader when initialization is complete.
   * 
   * @see javafx.fxml.Initializable#initialize(java.net.URL, java.util.ResourceBundle)
   * @since 1.0
   */
  @Override
  public void initialize(URL arg0, ResourceBundle arg1) {
    
    // get a vector of Path objects of the current user from the database.
    PathDao data = new PathDao();
    Vector<Path> pathData = data.getUserPaths(getCurrentUser());
    data.close();
    
    // create a vector of UserTableModel objects from vector of the current users Path objects.
    int index = 0;
    while (index < pathData.size()) {
      
      PathTableModel currentUserPath = new PathTableModel(null, null);
      
      currentUserPath.setTableName(pathData.get(index).getName());
      currentUserPath.setTableDescription(pathData.get(index).getDescription());
      
      currentUserPaths.addElement(currentUserPath);
      
      index++;
      
    } // end while
    
    // create an ObservableList of the vector of UserTableModel objects.
    tableData = FXCollections.observableArrayList(currentUserPaths);
    
    // link the ObservableList to the Tableview.
    currentUserPathsTable.getItems().setAll(this.tableData);
    
    // link the table cells to the UserTableModel objects fields.
    nameCol.setCellValueFactory(new PropertyValueFactory<PathTableModel, String>("tableName"));
    descriptionCol.setCellValueFactory(new PropertyValueFactory<PathTableModel, String>("tableDescription"));
    
    // select first item by default.
    currentUserPathsTable.getSelectionModel().selectFirst();
    
    // disable clear and admin buttons when list is empty.
    if (currentUserPaths.isEmpty()) {
      editButton.setDisable (true);
      deleteButton.setDisable (true);
    }
    
    // set the title displayed at the top of the application window.
    Main.stage.setTitle("Custom Holdings - Edit User Paths - " + getCurrentUser().getUsername() + "Logged In");
    // set the message displayed in the application window.
    titleLabel.setText("Edit and Delete User Paths");
    
  } //end method initialize.
  
} // end class CurrentPathsController.