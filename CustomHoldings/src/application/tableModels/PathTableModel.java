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

import javafx.beans.property.SimpleStringProperty;

/**
 * Creates an <code>UserTableModel</code> object to hold User data contained in Property objects for the <code>TableView</code> FX control.
 * 
 * @author Dennis Grzegorzewski
 * @version 1.0, May 4, 2016
 *
 */
public class PathTableModel {
  
  private final SimpleStringProperty tableName;
  private final SimpleStringProperty tableDescription;
  
  /*
   * Class constructors.
   */
  /**
   * Full parameter Constructor.
   * 
   * Parameters instantiate private object fields for instantiated class objects
   * 
   * @param name   the path <code>name</code>.
   * @param description   the path <code>description</code>.
   * @since 1.0
   */
  public PathTableModel(String name, String description) {
    super();
    this.tableName = new SimpleStringProperty(name);
    this.tableDescription = new SimpleStringProperty(description);
  } // end constructor PathTableModel.
  
  /*
   * Instance methods.
   * Getters return calls to methods of class field objects.
   * Setters call methods of class field objects set them.
   */
  /**
   * Gets the path <code>name</code>.
   * 
   * @return getTableName() - the path <code>name</code>.
   * @see #tableName
   * @see #setTableName(String)
   * @since 1.0
   */
  public String getTableName() {
    return tableName.get();
  } // end method getTableName.
  
  /**
   * Sets the path <code>name</code> flag.
   * 
   * @param name the path <code>name</code> to set.
   * @see #tableName
   * @see #getTableName()
   * @since 1.0
   */
  public void setTableName(String name) {
    tableName.set(name);
  } // end method setTableName.
  
  /**
   * Gets the path <code>description</code>.
   * 
   * @return getTableDescription() - the path <code>description</code>.
   * @see #tableDescription
   * @see #setTableDescription(String)
   * @since 1.0
   */
  public String getTableDescription() {
    return tableDescription.get();
  } // end method getTableDescription.
  
  /**
   * Sets the path <code>description</code> flag.
   * 
   * @param description the path <code>description</code> to set.
   * @see #tableDescription
   * @see #getTableDescription()
   * @since 1.0
   */
  public void setTableDescription(String description) {
    tableDescription.set(description);
  } // end method setTableDescription.
  
} // end class PathTableModel.