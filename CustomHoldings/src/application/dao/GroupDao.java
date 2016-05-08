/*
 * Author: Dennis Grzegorzewski
 * Date: 04/24/2016
 * FileName: application.dao.GroupDao.java
 * Class: ITMD 510
 * Assignment: Final Project
 * Archive: grzegorzewskiITMD510FP.zip
 * Due: 04/30/2016 11:59PM
 */
package application.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import application.models.Group;
import application.models.User;

/**
 * Interacts directly with the database for storage and retrieval of Group objects.
 * 
 * @author Dennis Grzegorzewski
 * @version 1.0, 04/24/2016
 *
 */
public class GroupDao extends DataSource {

  /*
   * Constructors.
   */
  /**
   * No-arg constructor.
   * 
   * @since 1.0
   */
  public GroupDao() {
    super();
  } // end constructor GroupDao.

  /*
   * Methods.
   */
  /**
   * Creates a new group in the database.
   * 
   * @param groupToAdd The group to add to the selected path.
   * @return success - true or false on success.
   * @since 1.0
   */
  public boolean addGroup(Group groupToAdd) {

    String query = "INSERT INTO custom_holdings.groups " 
                   + "(name, description, path_name)"
                   + "VALUES (?, ?, ?);";

    boolean success = true;
    
    try (PreparedStatement statement 
        = databaseConnection.prepareStatement(query)){
      
      statement.setString(1, groupToAdd.getName());
      statement.setString(2, groupToAdd.getDescription());
      statement.setString(3, groupToAdd.getPathName());
      statement.executeUpdate();
      
    } //end try.
    
    catch (SQLException e){
      success = false;
      System.out.println("Error creating group: "
                         + e.getMessage());
    } //end catch.
    
    return success;
    
  } // end method addGroup.

  /**
   * Deletes a group of the current user from the database.
   * 
   * @param groupToDelete the selected group to delete.
   * @since 1.0
   */
  public void deleteGroup(Group groupToDelete) {
    
    String query = "DELETE FROM groups WHERE name = ?;";
    
    try (PreparedStatement statement 
         = databaseConnection.prepareStatement(query)){
      
      statement.setString(1, groupToDelete.getName());
      statement.executeUpdate();
      
    } //end try.
    
    catch (SQLException e){
      System.out.println("Error deleting path: "
                         + e.getMessage());
    } //end catch.
    
  } // end method deleteGroup.
  
  /**
   * Finds and retrieves group of the current user from the database.
   * 
   * @param groupToFind the group of the current user to find.
   * @return resultOfSearch - the result of the search for the current users group.
   * @since 1.0
   */
  public Group findGroup(Group groupToFind) {
    
    Group resultOfSearch = new Group();
    String query = "SELECT * FROM custom_holdings.groups WHERE name = ? AND path_name = ?;";
    
    try (PreparedStatement statement 
        = databaseConnection.prepareStatement(query)){
      
      statement.setString(1, groupToFind.getName());
      statement.setString(2, groupToFind.getPathName());
      ResultSet resultSet = statement.executeQuery();

      if(resultSet.next()) {
        resultOfSearch.setName(resultSet.getString(1));
        resultOfSearch.setDescription(resultSet.getString(2));
        resultOfSearch.setPathName(resultSet.getString(3));
        
      } // end if.
      
    } //end try.
    
    catch (SQLException e){
      System.out.println("Error finding path: "
                         + e.getMessage());
    } //end catch.
    
    return resultOfSearch;
    
  } // end method findGroup.

  /**
   * Retrieves vector of groups of the current user from the database.
   * 
   * @param currentUser the current user of the groups returned.
   * @return userGroups - the vector of groups of the current user.
   * @since 1.0
   */
  public Vector<Group> getUserGroups(User currentUser) {
    
    // create vector to return user table data.
    Vector<Group> userGroups = new Vector<>();
    // create a query string to retrieve group table data.
    String query = "SELECT * "
                 + "FROM custom_holdings.groups A "
                 + "LEFT JOIN custom_holdings.paths B ON A.path_name = B.name "
                 + "LEFT JOIN custom_holdings.users C ON B.user_username = ?;";
    
    try (PreparedStatement statement 
         = databaseConnection.prepareStatement(query)){
      
      statement.setString(1, currentUser.getUsername ());
      
      ResultSet resultSet = statement.executeQuery();
      
      //get group data from table, add to data vector.
      while (resultSet.next()) {
        Group record = new Group();
        record.setName(resultSet.getString(1));
        record.setDescription(resultSet.getString(2));
        record.setPathName(resultSet.getString(3));
        userGroups.add(record);
      } // end while.
      
    } //end try.
    
    catch (SQLException e){
      System.out.println("Error finding group: "
                         + e.getMessage());
    } //end catch.
    
    return userGroups;
    
  } // end method getUserGroups.

  /**
   * Saves vector of edited groups of the current user to the database.
   * 
   * @param editedGroups the edited groups of the current user.
   * @return success - boolean flag of save success.
   * @since 1.0
   */
  public Boolean saveEditedGroups(Vector<Group> editedGroups) {
    
    String query = "UPDATE custom_holdings.groups " 
                   + "SET description = ?, path_name = ? "
                   + "WHERE name = ?;";
    
    Boolean success = true;
    
    try (PreparedStatement statement 
        = databaseConnection.prepareStatement(query)){
      
      //
      int index = 0;
      while (index < editedGroups.size ()) {
        
        statement.setString(1, editedGroups.get(index).getDescription());
        statement.setString(2, editedGroups.get(index).getPathName());
        statement.setString(3, editedGroups.get(index).getName());
        
        statement.executeUpdate();
      
        index++;
      } //end while.
      
    } //end try.
    
    catch (SQLException e){
      success = false;
      System.out.println("Error updating groups: "
                         + e.getMessage());
    } //end catch.
    
    return success;
    
  } // end method saveEditedGroups.
  
} // end class GroupDao.