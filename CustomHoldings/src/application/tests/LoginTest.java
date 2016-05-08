/*
 * Author: Dennis Grzegorzewski
 * Date: 04/24/2016
 * FileName: application.tests.LoginTest.java
 * Class: ITMD 510
 * Assignment: Final Project
 * Archive: grzegorzewskiITMD510FP.zip
 * Due: 04/30/2016 11:59PM
 */
package application.tests;

import application.models.User;
import application.dao.UserDao;

/**
 * Tests Login.
 * 
 * @author Dennis Grzegorzewski
 * @version 1.0, 04/24/2016
 *
 */
public class LoginTest {
  
  /**
   * Empty constructor.
   * 
   * @since 1.0
   */
  public LoginTest() {
  } //end constructor test.
  
  /**
   * Tests Login.
   * 
   * @param args[] command-line arguments (not used)
   * @since 1.0
   */
  public static void main(String[] args) {
    
    User userToFind = new User();
    userToFind.setUsername ("admin");
    userToFind.setPassword("admin");
    UserDao test = new UserDao();
    User resultOfSearch = test.findUser(userToFind);
    test.close();
    System.out.println("Username:   " + resultOfSearch.getUsername() + " " + userToFind.getUsername());
    System.out.println("Password:   " + resultOfSearch.getPassword() + " " + userToFind.getPassword());
    System.out.println("Cleared:    " + resultOfSearch.isCleared());
    System.out.println("Admin: " + resultOfSearch.isAdmin());
    
    //Validate input
    // if username does not exist
    if (resultOfSearch.getUsername() == null) {
      System.out.println("Username not found. Try again.");
    } //end if.
    
    // if username does not match password
    else if (!resultOfSearch.getPassword().equals(userToFind.getPassword())) {
      System.out.println("Incorrect Password. Try again.");
    } //end else if.
    
    // if username is not cleared
    else if (!resultOfSearch.isCleared()) {
      System.out.println("Username not cleared. Try Later.");
    } //end else if.
    
    else {
      System.out.println("Login Accepted.");
    } // end else.
    
  } // end method main.
  
} // end class LoginTest.