/*
 * Author: Dennis Grzegorzewski
 * Date: 04/14/2016
 * FileName: application.models.User.java
 * Class: ITMD 510
 * Assignment: Final Project
 * Archive: grzegorzewskiITMD510FP.zip
 * Due: 04/30/2016 11:59PM
 */
package application.models;

import java.util.Vector;

/**
 * Creates <code>User</code> objects which hold
 * the user login <code>username</code>,
 * the user login <code>password</code>,
 * the user <code>cleared</code> flag,
 * the user <code>administrator</code> flag,
 * and an array of <code>Path</code> objects.
 * 
 * @author Dennis Grzegorzewski
 * @version 1.0, 04/14/2016
 */
public class User {
  
  /*
   * Declare instance fields.
   */
  private String username;       // the user login username.
  private String password;       // the user login password.
  private boolean cleared;       // the user cleared flag.
  private boolean admin;         // the user admin flag.
  
  private Vector<Path> paths = new Vector<>(); //  the user created custom paths.
  
  /**
   * No-arg constructor.
   * 
   * @since 1.0
   */
  public User() {
    super();
    this.setUsername("");
    this.setPassword("");
    this.setCleared(false);
    this.setAdmin(false);
    this.setPaths(null);
  } //end constructor User.
  
  /**
   * Full parameter <code>User</code> constructor.
   * 
   * @param username  the user login <code>username</code>.
   * @param password  the user login <code>password</code>.
   * @param cleared   the user <code>cleared</code> flag.
   * @param admin     the user <code>admin</code> flag.
   * @param paths     the user created custom <code>Vector&lt;Path&gt; paths</code>.
   * @since 1.0
   */
  public User(String username, String password, boolean cleared, boolean admin, Vector<Path> paths) {
    super();
    this.setUsername(username);
    this.setPassword(password);
    this.setCleared(cleared);
    this.setAdmin(admin);
    this.setPaths(paths);
  } //end constructor User.
  
  /**
   * Gets the user <code>username</code>.
   * 
   * @return username - the user login <code>username</code>.
   * @see #username
   * @see #setUsername(String)
   * @since 1.0
   */
  public String getUsername() {
    return this.username;
  } //end method getUsername.
  
  /**
   * Sets the user <code>username</code>.
   * 
   * @param username the user login <code>username</code> to set.
   * @see #username
   * @see #getUsername()
   * @since 1.0
   */
  public void setUsername(String username) {
    this.username = username;
  } //end method setUsername.
  
  /**
   * Gets the user <code>password</code>.
   * 
   * @return password - the user login <code>password</code>.
   * @see #password
   * @see #setPassword(String)
   * @since 1.0
   */
  public String getPassword() {
    return this.password;
  } //end method getPassword.
  
  /**
   * Sets the user <code>password</code>.
   * 
   * @param password the user login <code>password</code> to set.
   * @see #password
   * @see #getPassword()
   * @since 1.0
   */
  public void setPassword(String password) {
    this.password = password;
  } //end method setPassword.
  
  /**
   * Gets the user <code>cleared</code> flag.
   * 
   * @return cleared - the user <code>cleared</code> flag.
   * @see #cleared
   * @see #setCleared(boolean)
   * @since 1.0
   */
  public boolean isCleared() {
    return this.cleared;
  } //end method isCleared.
  
  /**
   * Sets the user <code>cleared</code> flag.
   * 
   * @param cleared the user <code>cleared</code> flag status to set
   * @see #cleared
   * @see #isCleared()
   * @since 1.0
   */
  public void setCleared(boolean cleared) {
    this.cleared = cleared;
  } //end method setCleared.
  
  /**
   * Gets the user <code>admin</code> flag.
   * 
   * @return admin - the user <code>admin</code> flag.
   * @see #admin
   * @see #setAdmin(boolean)
   * @since 1.0
   */
  public boolean isAdmin() {
    return this.admin;
  } //end method isAdmin.

  /**
   * Sets the user <code>admin</code> flag.
   * 
   * @param admin the user <code>admin</code> flag status to set.
   * @see #admin
   * @see #isAdmin()
   * @since 1.0
   */
  public void setAdmin(boolean admin) {
    this.admin = admin;
  } //end method setAdmin.
  
  /**
   * Gets the user created custom <code>Vector&lt;Path&gt; paths</code>.
   * 
   * @return paths - the user created custom <code>Vector&lt;Path&gt; paths</code>.
   * @see #paths
   * @see #setPaths
   * @since 1.0
   */
  public Vector<Path> getPaths() {
    return this.paths;
  } //end method getPaths.
  
  /**
   * Sets the user created custom <code>Vector&lt;Path&gt; paths</code>.
   * 
   * @param paths the user created custom <code>Vector&lt;Path&gt; paths</code> to set.
   * @see #paths
   * @see #getPaths()
   * @since 1.0
   */
  public void setPaths(Vector<Path> paths) {
    this.paths = paths;
  } //end method setPaths.
  
  /**
   * Adds <code>Path</code> to the user created custom <code>Vector&lt;Path&gt; paths</code>.
   * 
   * @param path the <code>path</code> to add to the <code>Vector&lt;Path&gt; paths</code>.
   * @return pathAdded - boolean flag of <code>path</code> addition.
   * @see #paths
   * @see #removePath(Path)
   * @see Path
   * @see Path#getName()
   * @since 1.0
   */
  public boolean addPath(Path path) {
    
    /*
     * local fields.
     */
    boolean pathAdded = true;

    // iterate over the user paths.
    for (Path currentPath : paths) {
      
      // if the path passed in the parameter already exists.
      if (currentPath.getName().equals(path.getName())) {
        
        // then set the return flag to false.
        pathAdded = false;
        
      } //end if.
      
    } // end for.
    
    // if the path passed in the parameter does not yet exist.
    if (pathAdded) {
      
      // add the path to the user vector of paths.
      this.paths.addElement(path);
      
    } //end if.
    
    return pathAdded;
    
    } //end method addPath.
  
  /**
   * Removes <code>Path</code> from the user created custom <code>Vector&lt;Path&gt; paths</code>.
   * 
   * @param path the <code>path</code> to remove from the <code>Vector&lt;Path&gt; paths</code>.
   * @return pathRemoved - boolean flag of <code>path</code> removal.
   * @see #paths
   * @see #addPath(Path)
   * @see Path
   * @see Path#getName()
   * @since 1.0
   */
  public boolean removePath(Path path) {
    
    /*
     * local fields.
     */
    boolean pathRemoved = false;
    
    // iterate over the user paths.
    for (Path currentPath : paths) {
      
      // if the path passed in the parameter exists.
      if (currentPath.getName().equals(path.getName())) {
        
        try {
          // remove the path from the user vector of paths.
          paths.removeElementAt(paths.indexOf(currentPath));
        } // end try.
        
        catch (ArrayIndexOutOfBoundsException e) {
          System.out.println("Array Index Error!" 
                             + e.getMessage());
        } //end catch.
        
        // and set the return flag to true.
        pathRemoved = true;
        
      } //end if.
      
    } // end for.
    
    return pathRemoved;
  } //end method removePath.
  
} //end class User.