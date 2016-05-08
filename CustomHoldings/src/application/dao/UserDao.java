/*
 * Author: Dennis Grzegorzewski
 * Date: 04/24/2016
 * FileName: application.dao.UserDao.java
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

import application.models.User;

/**
 * Interacts directly with the database for storage and retrieval of User objects.
 * 
 * @author Dennis Grzegorzewski
 * @version 1.0, 04/24/2016
 */
public class UserDao extends DataSource {
  
  /**
   * Empty UserDao constructor.
   * 
   * @since 1.0
   */
  public UserDao() {
  } // end empty constructor UserDao.

  /**
   * Creates a new user in the database.
   * 
   * @param newUser new user object to create in the database.
   * @return success - true or false on success.
   * @since 1.0
   */
  public boolean createUser(User newUser) {
    
    String query = "INSERT INTO custom_holdings.users " 
                   + "(username, password, cleared, admin)"
                   + "VALUES (?, ?, ?, ?);";
    
    boolean success = true;
    try (PreparedStatement statement 
        = databaseConnection.prepareStatement(query)){
      
      statement.setString(1, newUser.getUsername());
      statement.setString(2, newUser.getPassword());
      statement.setBoolean(3, newUser.isCleared());
      statement.setBoolean(4, newUser.isAdmin());
      statement.executeUpdate();
      
    } //end try.
    
    catch (SQLException e){
      success = false;
      System.out.println("Error creating user: "
                         + e.getMessage());
    } //end catch.
    
    return success;
    
  } // end method createUser.
  
  /**
   * Reads user record from the database.
   * 
   * @param userToRead user to retrieve from the database.
   * @return resultOfSearch - the results of the search for the user.
   * @since 1.0
   */
  public User readUser(User userToRead) {
    
    User resultOfSearch = new User();
    String query = "SELECT * FROM users WHERE username = ?;";
    try (PreparedStatement statement 
         = databaseConnection.prepareStatement(query)){
      
      statement.setString(1, userToRead.getUsername());
      ResultSet resultSet = statement.executeQuery();
      
      if(resultSet.next()) {
        resultOfSearch.setUsername(resultSet.getString(1));
        resultOfSearch.setPassword(resultSet.getString(2));
        resultOfSearch.setCleared(resultSet.getBoolean(3));
        resultOfSearch.setAdmin(resultSet.getBoolean(4));
      } // end if.
      
    } //end try.
    
    catch (SQLException e){
      System.out.println("Error reading user: "
                         + e.getMessage());
    } //end catch.
    
    return resultOfSearch;
    
  } // end method readUser.
  
  /**
   * Updates user record in database.
   * 
   * @param selectedUser user to update in the database.
   * @since 1.0
   */
  public void updateUser(User selectedUser) {
    
  } // end method updateUser.
  
  /**
   * Deletes user record from the database.
   * 
   * @param userToDelete user to delete from the database.
   * @since 1.0
   */
  public void deleteUser(User userToDelete) {
    
    String query = "DELETE FROM users WHERE username = ?;";
    
    try (PreparedStatement statement 
         = databaseConnection.prepareStatement(query)){
      
      statement.setString(1, userToDelete.getUsername());
      statement.executeUpdate();
      
    } //end try.
    
    catch (SQLException e){
      System.out.println("Error finding user: "
                         + e.getMessage());
    } //end catch.
    
  } // end method deleteUser.
  
  /**
   * Retrieves user record matching <code>username</code> from the database.
   * 
   * @param userToFind user to find in the database.
   * @return resultOfSearch - <code>User</code> matching <code>username</code>.
   * @since 1.0
   */
  public User findUser(User userToFind) {
    
    User resultOfSearch = new User();
    String query = "SELECT * FROM users WHERE username = ?;";
    
    try (PreparedStatement statement 
         = databaseConnection.prepareStatement(query)){
      
      statement.setString(1, userToFind.getUsername());
      ResultSet resultSet = statement.executeQuery();
      
      if(resultSet.next()) {
        resultOfSearch.setUsername(resultSet.getString(1));
        resultOfSearch.setPassword(resultSet.getString(2));
        resultOfSearch.setCleared(resultSet.getBoolean(3));
        resultOfSearch.setAdmin(resultSet.getBoolean(4));
      } // end if.
      
    } //end try.
    
    catch (SQLException e){
      System.out.println("Error finding user: "
                         + e.getMessage());
    } //end catch.
    
    return resultOfSearch;
    
  } // end method findUser.
  
  /**
   * Retrieves uncleared user records from the database.
   * 
   * @return unclearedUsers - Vector&lt;User&gt; of uncleared users.
   * @since 1.0
   */
  public Vector<User> getUnclearedUsers() {
    
    // create vector to return user table data.
    Vector<User> unclearedUsers = new Vector<>();
    // create a query string to retrieve user table data.
    String query = "SELECT * FROM users WHERE cleared = false;";
    
    try (PreparedStatement statement 
         = databaseConnection.prepareStatement(query)){
      
      ResultSet resultSet = statement.executeQuery();
      
      //get user data from table, add to data vector.
      while (resultSet.next()) {
        User record = new User();
        record.setUsername(resultSet.getString(1));
        record.setPassword(resultSet.getString(2));
        record.setCleared(resultSet.getBoolean(3));
        record.setAdmin(resultSet.getBoolean(4));
        unclearedUsers.add(record);
      } // end while.
      
    } //end try.
    
    catch (SQLException e){
      System.out.println("Error finding user: "
                         + e.getMessage());
    } //end catch.
    
    return unclearedUsers;
    
  } // end method getUnclearedUsers.
  
  /**
   * Retrieves all current user records from the database.
   * 
   * @return currentUsers - Vector&lt;User&gt; of current users.
   * @since 1.0
   */
  public Vector<User> getCurrentUsers() {
    
    // create vector to return user table data.
    Vector<User> currentUsers = new Vector<>();
    // create a query string to retrieve user table data.
    String query = "SELECT * FROM users;";
    
    try (PreparedStatement statement 
         = databaseConnection.prepareStatement(query)){
      
      ResultSet resultSet = statement.executeQuery();
      
      //get user data from table, add to data vector.
      while (resultSet.next()) {
        User record = new User();
        record.setUsername(resultSet.getString(1));
        record.setPassword(resultSet.getString(2));
        record.setCleared(resultSet.getBoolean(3));
        record.setAdmin(resultSet.getBoolean(4));
        currentUsers.add(record);
      } // end while.
      
    } //end try.
    
    catch (SQLException e){
      System.out.println("Error finding user: "
                         + e.getMessage());
    } //end catch.
    
    return currentUsers;
    
  } // end method getCurrentUsers.
  
  public Boolean saveEditedUsers(Vector<User> clearedUsers) {
    
    String query = "UPDATE custom_holdings.users " 
        + "SET cleared = ?, admin = ? "
        + "WHERE username = ?;";
    
    Boolean success = true;
    
    try (PreparedStatement statement 
        = databaseConnection.prepareStatement(query)){
      
      //
      int index = 0;
      while (index < clearedUsers.size ()) {
        
      statement.setBoolean(1, clearedUsers.get(index).isCleared());
      statement.setBoolean(2, clearedUsers.get(index).isAdmin());
      statement.setString(3, clearedUsers.get(index).getUsername());
      
      statement.executeUpdate();
      
      index++;
      } //end while.
      
    } //end try.
    
    catch (SQLException e){
      success = false;
      System.out.println("Error updating users: "
                         + e.getMessage());
    } //end catch.
    
    return success;
    
  } // end method saveEditedUsers.
  
} // end class UserDao.