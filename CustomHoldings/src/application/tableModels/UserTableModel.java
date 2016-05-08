/*
 * Author: Dennis Grzegorzewski
 * Date: 04/14/2016
 * FileName: application.tableModels.UserTableModel.java
 * Class: ITMD 510
 * Assignment: Final Project
 * Archive: grzegorzewskiITMD510FP.zip
 * Due: 04/30/2016 11:59PM
 */
package application.tableModels;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Creates an <code>UserTableModel</code> object to hold User data contained in Property objects for the <code>TableView</code> FX control.
 * 
 * @author Dennis Grzegorzewski
 * @version 1.0, May 4, 2016
 *
 */
public class UserTableModel {
  
  private final SimpleStringProperty tableUsername;
  private final SimpleStringProperty tablePassword;
  private final SimpleBooleanProperty tableCleared;
  private final SimpleBooleanProperty tableAdmin;
  
  /*
   * Class constructors.
   */
  /**
   * Full parameter Constructor.
   * 
   * Parameters instantiate private object fields for instantiated class objects
   * 
   * @param username   the uncleared user <code>username</code>.
   * @param password   the uncleared user <code>password</code>.
   * @param cleared    the uncleared user <code>cleared</code> flag.
   * @param admin      the uncleared user <code>admin</code> flag.
   * @since 1.0
   */
  public UserTableModel(String username, String password, Boolean cleared, Boolean admin) {
    super();
    this.tableUsername = new SimpleStringProperty(username);
    this.tablePassword = new SimpleStringProperty(password);
    this.tableCleared = new SimpleBooleanProperty(cleared);
    this.tableAdmin = new SimpleBooleanProperty(admin);
  } // end constructor unclearedUser.
  
  /*
   * Instance methods.
   * Getters return calls to methods of class field objects.
   * Setters call methods of class field objects set them.
   */
  /**
   * Gets the user <code>username</code>.
   * 
   * @return getTableUsername() - the user <code>username</code>.
   * @see #tableUsername
   * @see #setTableUsername(String)
   * @since 1.0
   */
  public String getTableUsername() {
    return tableUsername.get();
  } // end method getTableUsername.
  
  /**
   * Sets the user <code>username</code> flag.
   * 
   * @param username the user <code>username</code> to set.
   * @see #tableUsername
   * @see #getTableUsername()
   * @since 1.0
   */
  public void setTableUsername(String username) {
    tableUsername.set(username);
  } // end method setTableUsername.
  
  /**
   * Gets the user <code>password</code>.
   * 
   * @return getTablePassword() - the user <code>password</code>.
   * @see #tablePassword
   * @see #setTablePassword(String)
   * @since 1.0
   */
  public String getTablePassword() {
    return tablePassword.get();
  } // end method getTablePassword.
  
  /**
   * Sets the user <code>password</code> flag.
   * 
   * @param password the user <code>password</code> to set.
   * @see #tablePassword
   * @see #getTablePassword()
   * @since 1.0
   */
  public void setTablePassword(String password) {
    tablePassword.set(password);
  } // end method setTablePassword.
  
  /**
   * Gets the user <code>cleared</code> flag.
   * 
   * @return tableCleared.get() - the user <code>cleared</code> flag.
   * @see #tableCleared
   * @see #setTableCleared(Boolean)
   * @since 1.0
   */
  public Boolean getTableCleared() {
    return tableCleared.get();
  } // end method getTableCleared.
  
  /**
   * Sets the user <code>cleared</code> flag.
   * 
   * @param cleared the user <code>cleared</code> flag status to set.
   * @see #tableCleared
   * @see #getTableCleared()
   * @since 1.0
   */
  public void setTableCleared(Boolean cleared) {
    tableCleared.set(cleared);
  } // end method setTableCleared.
  
  /**
   * Gets the user <code>admin</code> flag.
   * 
   * @return tableAdmin.get() - the user <code>admin</code> flag.
   * @see #tableAdmin
   * @see #setTableCleared(Boolean)
   * @since 1.0
   */
  public Boolean getTableAdmin() {
    return tableAdmin.get();
  } // end method getTableAdmin.
  
  /**
   * Sets the user <code>admin</code> flag.
   * 
   * @param admin the user <code>admin</code> flag status to set.
   * @see #tableAdmin
   * @see #getTableAdmin()
   * @since 1.0
   */
  public void setTableAdmin(Boolean admin) {
    tableAdmin.set(admin);
  } // end method setTableAdmin.
  
} // end class UserTableModel.