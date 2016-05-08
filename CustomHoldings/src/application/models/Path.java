/*
 * Author: Dennis Grzegorzewski
 * Date: 04/14/2016
 * FileName: application.models.Path.java
 * Class: ITMD 510
 * Assignment: Final Project
 * Archive: grzegorzewskiITMD510FP.zip
 * Due: 04/30/2016 11:59PM
 */
package application.models;

import java.util.Vector;

/**
 * Creates <code>Path</code> objects which hold
 * the path's <code>name</code>,
 * the path's <code>description</code>,
 * and an array of <code>Group</code> objects.
 * 
 * @author Dennis Grzegorzewski
 * @version 1.0, 04/14/2016
 */
public class Path {
  
  /*
   * Declare instance fields.
   */
  private String name;        // the path name.
  private String description; // the path description.
  private String username;    // the path user.
  
  private Vector<Group> groups = new Vector<>(); // the path custom groups.
  
  /**
   * No-arg constructor.
   * 
   * @since 1.0
   */
  public Path() {
    super();
    this.setName("");
    this.setDescription("");
    this.setUsername("");
    this.setGroups(null);
  } //end constructor Path.
  
  /**
   * Full parameter <code>Path</code> constructor.
   * 
   * @param name the path <code>name</code>.
   * @param description the path <code>description</code>.
   * @param username the name of the user of this path.
   * @param groups the path custom <code>Vector&lt;Group&gt; groups</code>.
   * @since 1.0
   */
  public Path(String name, String description, String username, Vector<Group> groups) {
    super();
    this.setName(name);
    this.setDescription(description);
    this.setUsername (username);
    this.setGroups(groups);
  } //end constructor Path.
  
  /**
   * Gets the path <code>name</code>.
   * 
   * @return name - the path <code>name</code>.
   * @see #name
   * @see #setName(String)
   * @since 1.0
   */
  public String getName() {
    return this.name;
  } //end method getName.
  
  /**
   * Sets the path <code>name</code>.
   * 
   * @param name the path <code>name</code> to set.
   * @see #name
   * @see #getName()
   * @since 1.0
   */
  public void setName(String name) {
    this.name = name;
  } //end method setName.
  
  /**
   * Gets the path <code>description</code>.
   * 
   * @return description - the path <code>description</code>.
   * @see #description
   * @see #setDescription(String)
   * @since 1.0
   */
  public String getDescription() {
    return this.description;
  } //end method getDescription.
  
  /**
   * Sets the path <code>description</code>.
   * 
   * @param description the path <code>description</code> to set.
   * @see #description
   * @see #getDescription()
   * @since 1.0
   */
  public void setDescription(String description) {
  this.description = description;
  } //end method setDescription.
  
  /**
   * Gets the username of the user of this path.
   * 
   * @return username - the username of the user of this path.
   * @see #username
   * @since 1.0
   */
  public String getUsername() {
    return username;
  } // end method getUsername.
  
  /**
   * Sets the username of the user of this path.
   * 
   * @param username the username of the User to set.
   * @see #username
   * @since 1.0
   */
  public void setUsername(String username) {
    this.username = username;
  } // end method setUsername.
  
  /**
   * Gets the path custom <code>Vector&lt;Group&gt; groups</code>.
   * 
   * @return groups - the path custom <code>Vector&lt;Group&gt; groups</code>.
   * @see #groups
   * @see #setGroups
   * @since 1.0
   */
  public Vector<Group> getGroups() {
    return this.groups;
  } //end method getGroups.
  
  /**
   * Sets the path custom <code>Vector&lt;Group&gt; groups</code>.
   * 
   * @param groups the path custom <code>Vector&lt;Group&gt; groups</code> to set.
   * @see #groups
   * @see #getGroups()
   * @since 1.0
   */
  public void setGroups(Vector<Group> groups) {
    this.groups = groups;
  } //end method setGroups.
  
  /**
   * Adds <code>Group</code> to the path custom <code>Vector&lt;Group&gt; groups</code>.
   * 
   * @param group the <code>group</code> to add to the <code>Vector&lt;Group&gt; groups</code>.
   * @return groupAdded - boolean flag of <code>group</code> addition.
   * @see #groups
   * @see #removeGroup(Group)
   * @see Group
   * @see Group#getName()
   * @since 1.0
   */
  public boolean addGroup(Group group) {
    
    /*
     * local fields.
     */
    boolean groupAdded = true;

    // iterate over the path groups.
    for (Group currentGroup : groups) {
      
      // if the group passed in the parameter already exists.
      if (currentGroup.getName().equals(group.getName())) {
        // then set the return flag to false.
        groupAdded = false;
      } //end if.
      
    } // end for.
    
    // if the group passed in the parameter does not yet exist.
    if (groupAdded) {
      // add the group to the path vector of groups.
      this.groups.addElement(group);
    } //end if.
    
    return groupAdded;
    } //end method addGroup.
  
  /**
   * Removes <code>Group</code> from the path custom <code>Vector&lt;Group&gt; groups</code>.
   * 
   * @param group the <code>group</code> to remove from the <code>Vector&lt;Group&gt; groups</code>.
   * @return groupRemoved - boolean flag of <code>group</code> removal.
   * @see #groups
   * @see #addGroup(Group)
   * @see Group
   * @see Group#getName()
   * @since 1.0
   */
  public boolean removeGroup(Group group) {
    
    /*
     * local fields.
     */
    boolean groupRemoved = false;
    
    // iterate over the path groups.
    for (Group currentGroup : groups) {
      
      // if the group passed in the parameter exists.
      if (currentGroup.getName().equals(group.getName())) {
        
        try {
          // remove the group from the path vector of groups.
          groups.removeElementAt(groups.indexOf(currentGroup));
        } // end try.
        
        catch (ArrayIndexOutOfBoundsException e) {
          System.out.println("Array Index Error!" 
                             + e.getMessage());
        } //end catch
        
        // and set the return flag to true.
        groupRemoved = true;
        
      } //end if.
      
    } // end for.
    
    return groupRemoved;
  } //end method groupRemoved.
  
} // end class Path.