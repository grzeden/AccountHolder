/*
 * Author: Dennis Grzegorzewski
 * Date: 04/24/2016
 * FileName: application.dao.PathDao.java
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

import application.models.Path;
import application.models.User;

/**
 * Interacts directly with the database for storage and retrieval of Path objects..
 * 
 * @author Dennis Grzegorzewski
 * @version 1.0, 04/24/2016
 *
 */
public class PathDao extends DataSource {
  
  /*
   * Constructors.
   */
  /**
   * No-arg constructor.
   * 
   * @since 1.0
   */
  public PathDao() {
    super();
  } // end constructor PathDao.
  
  /*
   * Methods.
   */
  /**
   * Creates a new path for the current user in the database.
   * 
   * @param pathToAdd new path for the current user
   * @return success - true or false on success.
   * @since 1.0
   */
  public boolean addPath(Path pathToAdd) {

    String query = "INSERT INTO custom_holdings.paths " 
                   + "(name, description, user_username)"
                   + "VALUES (?, ?, ?);";

    boolean success = true;
    
    try (PreparedStatement statement 
        = databaseConnection.prepareStatement(query)){
      
      statement.setString(1, pathToAdd.getName());
      statement.setString(2, pathToAdd.getDescription());
      statement.setString(3, pathToAdd.getUsername());
      statement.executeUpdate();
      
    } //end try.
    
    catch (SQLException e){
      success = false;
      System.out.println("Error creating path: "
                         + e.getMessage());
    } //end catch.
    
    return success;
    
  } // end method addPath.

  /**
   * Deletes path record from the database.
   * 
   * @param pathToDelete path to delete.
   * @since 1.0
   */
  public void deletePath(Path pathToDelete) {
    
    String query = "DELETE FROM paths WHERE name = ?;";
    
    try (PreparedStatement statement 
         = databaseConnection.prepareStatement(query)){
      
      statement.setString(1, pathToDelete.getName());
      statement.executeUpdate();
      
    } //end try.
    
    catch (SQLException e){
      System.out.println("Error deleting path: "
                         + e.getMessage());
    } //end catch.
    
  } // end method deletePath.
  
  /**
   * Finds and retrieves path of the current user from the database.
   * 
   * @param pathToFind the path of the current user to find.
   * @return resultOfSearch - the result of the search for the current users path.
   * @since 1.0
   */
  public Path findPath(Path pathToFind) {
    
    Path resultOfSearch = new Path();
    String query = "SELECT * FROM custom_holdings.paths WHERE name = ? AND user_username = ?;";
    
    try (PreparedStatement statement 
        = databaseConnection.prepareStatement(query)){
      
      statement.setString(1, pathToFind.getName());
      statement.setString(2, pathToFind.getUsername());
      ResultSet resultSet = statement.executeQuery();

      if(resultSet.next()) {
        resultOfSearch.setName(resultSet.getString(1));
        resultOfSearch.setDescription(resultSet.getString(2));
        resultOfSearch.setUsername(resultSet.getString(3));
        
      } // end if.
      
    } //end try.
    
    catch (SQLException e){
      System.out.println("Error finding path: "
                         + e.getMessage());
    } //end catch.
    
    return resultOfSearch;
    
  } // end method findPath.
  
  /**
   * Retrieves vector of paths of the current user from the database.
   * 
   * @param currentUser the current user of the paths returned.
   * @return userPaths - the vector of paths of the current user.
   * @since 1.0
   */
  public Vector<Path> getUserPaths(User currentUser) {
    
    // create vector to return user table data.
    Vector<Path> userPaths = new Vector<>();
    // create a query string to retrieve path table data.
    String query = "SELECT * FROM paths WHERE user_username = ?";
    
    try (PreparedStatement statement 
         = databaseConnection.prepareStatement(query)){
      
      statement.setString(1, currentUser.getUsername ());
      
      ResultSet resultSet = statement.executeQuery();
      
      //get path data from table, add to data vector.
      while (resultSet.next()) {
        Path record = new Path();
        record.setName(resultSet.getString(1));
        record.setDescription(resultSet.getString(2));
        record.setUsername(resultSet.getString(3));
        userPaths.add(record);
      } // end while.
      
    } //end try.
    
    catch (SQLException e){
      System.out.println("Error finding pathr: "
                         + e.getMessage());
    } //end catch.
    
    return userPaths;
    
  } // end method getUserPaths.

  /**
   * Saves vector of edited paths of the current user to the database.
   * 
   * @param editedPaths the edited paths of the current user.
   * @return success - boolean flag of save success.
   * @since 1.0
   */
  public Boolean saveEditedPaths(Vector<Path> editedPaths) {
    
    String query = "UPDATE custom_holdings.paths " 
                   + "SET user_username = ?, description = ? "
                   + "WHERE name = ?;";
    
    Boolean success = true;
    
    try (PreparedStatement statement 
        = databaseConnection.prepareStatement(query)){
      
      //
      int index = 0;
      while (index < editedPaths.size ()) {
        
        statement.setString(1, editedPaths.get(index).getUsername());
        statement.setString(2, editedPaths.get(index).getDescription());
        statement.setString(3, editedPaths.get(index).getName());
        
        statement.executeUpdate();
      
        index++;
      } //end while.
      
    } //end try.
    
    catch (SQLException e){
      success = false;
      System.out.println("Error updating paths: "
                         + e.getMessage());
    } //end catch.
    
    return success;
    
  } // end method saveEditedPaths.
  
} // end class PathDao.