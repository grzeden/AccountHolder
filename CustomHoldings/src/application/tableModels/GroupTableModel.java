/*
 * Author: Dennis Grzegorzewski
 * Date: 04/14/2016
 * FileName: application.tableModels.GroupTableModel.java
 * Class: ITMD 510
 * Assignment: Final Project
 * Archive: grzegorzewskiITMD510FP.zip
 * Due: 04/30/2016 11:59PM
 */
package application.tableModels;

import javafx.beans.property.SimpleStringProperty;


/**
 * Creates an <code>GroupTableModel</code> object to hold User data contained in Property objects for the <code>TableView</code> FX control.
 * 
 * @author Dennis Grzegorzewski
 * @version 1.0, May 5, 2016
 *
 */
public class GroupTableModel {
  
  private final SimpleStringProperty tableName;
  private final SimpleStringProperty tableDescription;
  private final SimpleStringProperty tablePathName;

  /*
   * Class constructors.
   */
  /**
   * Full parameter Constructor.
   * 
   * Parameters instantiate private object fields for instantiated class objects
   * 
   * @param name        the group <code>name</code>.
   * @param description the group <code>description</code>.
   * @param pathName    the name of the path the group belongs to.
   * @since 1.0
   */
  public GroupTableModel(String name, String description, String pathName) {
    super();
    this.tableName = new SimpleStringProperty(name);
    this.tableDescription = new SimpleStringProperty(description);
    this.tablePathName = new SimpleStringProperty(pathName);
  } // end constructor GroupTableModel.

  /*
   * Instance methods.
   * Getters return calls to methods of class field objects.
   * Setters call methods of class field objects set them.
   */
  /**
   * Gets the group <code>name</code>.
   * 
   * @return getTableName() - the group <code>name</code>.
   * @see #tableName
   * @see #setTableName(String)
   * @since 1.0
   */
  public String getTableName() {
    return tableName.get();
  } // end method getTableName.
  
  /**
   * Sets the group <code>name</code> flag.
   * 
   * @param name the group <code>name</code> to set.
   * @see #tableName
   * @see #getTableName()
   * @since 1.0
   */
  public void setTableName(String name) {
    tableName.set(name);
  } // end method setTableName.
  
  /**
   * Gets the group <code>description</code>.
   * 
   * @return getTableDescription() - the group <code>description</code>.
   * @see #tableDescription
   * @see #setTableDescription(String)
   * @since 1.0
   */
  public String getTableDescription() {
    return tableDescription.get();
  } // end method getTableDescription.
  
  /**
   * Sets the group <code>description</code> flag.
   * 
   * @param description the group <code>description</code> to set.
   * @see #tableDescription
   * @see #getTableDescription()
   * @since 1.0
   */
  public void setTableDescription(String description) {
    tableDescription.set(description);
  } // end method setTableDescription.

  /**
   * Gets the group <code>pathName</code>.
   * 
   * @return getTablePathName() - the group <code>pathName</code>.
   * @see #tablePathName
   * @see #setTablePathName(String)
   * @since 1.0
   */
  public String getTablePathName() {
    return tablePathName.get();
  } // end method getTablePathName.
  
  /**
   * Sets the group <code>pathName</code> flag.
   * 
   * @param pathName the group <code>pathName</code> to set.
   * @see #tablePathName
   * @see #getTablePathName()
   * @since 1.0
   */
  public void setTablePathName(String pathName) {
    tablePathName.set(pathName);
  } // end method setTablePathName.
  
} // end class GroupTableModel.