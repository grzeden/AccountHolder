/*
 * Author: Dennis Grzegorzewski
 * Date: 04/14/2016
 * FileName: application.models.Group.java
 * Class: ITMD 510
 * Assignment: Final Project
 * Archive: grzegorzewskiITMD510FP.zip
 * Due: 04/30/2016 11:59PM
 */
package application.models;
 
import java.util.Vector;

/**
 * Creates <code>Group</code> objects which hold
 * the group's <code>name</code>,
 * the group's <code>description</code>,
 * and an array of <code>Symbol</code> objects.
 * 
 * @author Dennis Grzegorzewski
 * @version 1.0, 04/14/2016
 */
public class Group {
  
  /*
   * Declare instance fields.
   */
  private String name;        // the group name.
  private String description; // the group description.
  private String pathName;    // the group path.
  
  private Vector<Symbol> symbols = new Vector<>(); // the group custom symbols.
  
  /**
   * No-arg constructor.
   * 
   * @since 1.0
   */
  public Group() {
    super();
    this.setName("");
    this.setDescription("");
    this.setPathName("");
    this.setSymbols(null);
  } //end constructor Group.
  
  /**
   * Full parameter <code>Group</code> constructor.
   * 
   * @param name the group <code>name</code>.
   * @param description the group <code>description</code>.
   * @param symbols the group custom <code>Vector&lt;Symbol&gt; symbols</code>.
   * @since 1.0
   */
  public Group (String name, String description, Vector<Symbol> symbols) {
    super();
    this.setName(name);
    this.setDescription(description);
    this.setSymbols(symbols);
  } //end constructor Group.
  
  /**
   * Gets the group <code>name</code>.
   * 
   * @return name - the group <code>name</code>.
   * @see #name
   * @see #setName(String)
   * @since 1.0
   */
  public String getName() {
    return this.name;
  } //end method getName.
  
  /**
   * Sets the group <code>name</code>.
   * 
   * @param name the group <code>name</code> to set.
   * @see #name
   * @see #getName()
   * @since 1.0
   */
  public void setName(String name) {
    this.name = name;
  } //end method setName.
  
  /**
   * Gets the group <code>description</code>.
   * 
   * @return description - the group <code>description</code>.
   * @see #description
   * @see #setDescription(String)
   * @since 1.0
   */
  public String getDescription() {
    
    return this.description;
    
  } //end method getDescription.
  
  /**
   * Sets the group <code>description</code>.
   * 
   * @param description the group <code>description</code> to set.
   * @see #description
   * @see #getDescription()
   * @since 1.0
   */
  public void setDescription(String description) {
    this.description = description;
  } //end method setDescription.

  /**
   * Gets the path name of the path of this group.
   * 
   * @return pathName - the pathName of the path of this group.
   * @see #pathName
   * @since 1.0
   */
  public String getPathName() {
    return pathName;
  } // end method getPathName.
  
  /**
   * Sets the path name of the path of this group.
   * 
   * @param pathName the path name of the group to set.
   * @see #pathName
   * @since 1.0
   */
  public void setPathName(String pathName) {
    this.pathName = pathName;
  } // end method setPathName.
  
  /**
   * Gets the group custom <code>Vector&lt;Symbol&gt; symbols</code>.
   * 
   * @return symbols - the group custom <code>Vector&lt;Symbol&gt; symbols</code>.
   * @see #symbols
   * @see #setSymbols
   * @since 1.0
   */
  public Vector<Symbol> getSymbols() {
  return this.symbols;
  } //end method getSymbols.
  
  /**
   * Sets the group custom <code>Vector&lt;Symbol&gt; symbols</code>.
   * 
   * @param symbols the group custom <code>Vector&lt;Symbol&gt; symbols</code> to set.
   * @see #symbols
   * @see #getSymbols()
   * @since 1.0
   */
  public void setSymbols(Vector<Symbol> symbols) {
    this.symbols = symbols;
  } //end method setSymbols.
  
  /** 
   * Adds <code>Symbol</code> to the group custom <code>Vector&lt;Symbol&gt; symbols</code>.
   * 
   * @param symbol the <code>symbol</code> to add to the <code>Vector&lt;Symbol&gt; symbols</code>.
   * @return symbolAdded - boolean flag of <code>Symbol</code> addition.
   * @see #symbols
   * @see #removeSymbol(Symbol)
   * @since 1.0
   */
  public boolean addSymbol(Symbol symbol) {
    
    /*
     * local fields.
     */
    boolean symbolAdded = true;
    
    // iterate over the group symbols.
    for (Symbol currentSymbol : symbols) {
      
      // if the symbol passed in the parameter already exists.
      if (currentSymbol.getSymbol().equals(symbol.getSymbol())) {
        // then set the return flag to false.
        symbolAdded = false;
      } //end if.
      
    } // end for.
    
    // if the symbol passed in the parameter does not yet exist.
    if (symbolAdded) {
      // add the symbol to the group vector of symbols.
      this.symbols.addElement(symbol);
    } // end if.
    
    return symbolAdded;
    } //end method addSymbol.
  
  /**
   * Removes <code>Symbol</code> from the group custom <code>Vector&lt;Symbol&gt; symbols</code>.
   * 
   * @param symbol the <code>symbol</code> to remove from the <code>Vector&lt;Symbol&gt; symbols</code>.
   * @return symbolRemoved - boolean flag of <code>Symbol</code> removal.
   * @see #symbols
   * @see #addSymbol(Symbol)
   * @since 1.0
   */
  public boolean removeSymbol(Symbol symbol) {
    
    /*
     * local fields.
     */
    boolean symbolRemoved = false;
    
    // iterate over the group symbols.
    for (Symbol currentSymbol : symbols) {
      
      // if the symbol passed in the parameter already exists in the group.
      if (currentSymbol.getSymbol().equals(symbol.getSymbol())) {
        
        try {
          // remove the group from the path vector of groups.
          symbols.removeElementAt(symbols.indexOf(currentSymbol));
        } // end try.
        
        catch (ArrayIndexOutOfBoundsException e) {
          System.out.println("Array Index Error!" 
                             + e.getMessage());
        } //end catch
        
        // and set the return flag to true.
        symbolRemoved = true;
        
      } //end if.
      
    } // end for.
    
    return symbolRemoved;
  } //end method removeSymbol.
  
} //end class Group.