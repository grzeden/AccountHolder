/*
 * Author: Dennis Grzegorzewski
 * Date: 04/14/2016
 * FileName: application.models.Symbol.java
 * Class: ITMD 510
 * Assignment: Final Project
 * Archive: grzegorzewskiITMD510FP.zip
 * Due: 04/30/2016 11:59PM
 */
package application.models;
  
/**
 * Creates <code>Symbol</code> objects which hold
 * the OCLC <code>symbol</code> of a lending library,
 * the <code>state</code> abbreviation of a lending library,
 * and the <code>cost</code> type of a lending library.
 * 
 * @author Dennis Grzegorzewski
 * @version 1.0, 04/14/2016
 */
public class Symbol {
  
  /*
   * Declare instance fields.
   */
  private String symbol; // the OCLC symbol of a lending library.
  private String state;  // the state abbreviation of a lending library.
  private String cost;   // the cost type of a lending library.
  
  /**
   * Empty <code>Symbol</code> constructor.
   * 
   * @since 1.0
   */
  public Symbol() {
  } //end constructor Symbol
  
  /**
   * Single parameter <code>Symbol</code> constructor. 
   * description.
   * 
   * @param symbol the OCLC symbol of a lending library.
   * @see #symbol
   * @see #state
   * @see #cost
   * @see #setSymbol(String)
   * @see #getSymbol()
   * @see #setState(String)
   * @see #getState()
   * @see #setCost(String)
   * @see #getCost()
   */
  public Symbol(String symbol) {
    super();
    this.setSymbol(symbol);
    this.setState (null);
    this.setCost (null);
  } //end constructor Symbol
  
  /**
   * Two parameter <code>Symbol</code> constructor.
   * 
   * @param symbol the OCLC symbol of a lending library.
   * @param state the state abbreviation of a lending library.
   * @see #symbol
   * @see #state
   * @see #cost
   * @see #setSymbol(String)
   * @see #getSymbol()
   * @see #setState(String)
   * @see #getState()
   * @see #setCost(String)
   * @see #getCost()
   */
  public Symbol(String symbol,String state) {
    super();
    this.setSymbol(symbol);
    this.setState(state);
    this.setCost(null);
  } //end constructor Symbol
  
  /**
   * Full parameter <code>Symbol</code> constructor.
   * 
   * @param symbol the OCLC symbol of a lending library.
   * @param state the state abbreviation of a lending library.
   * @param cost the cost type of a lending library.
   * @see #symbol
   * @see #state
   * @see #cost
   * @see #setSymbol(String)
   * @see #getSymbol()
   * @see #setState(String)
   * @see #getState()
   * @see #setCost(String)
   * @see #getCost()
   * @since 1.0
   */
  public Symbol(String symbol,String state, String cost) {
    super();
    this.setSymbol(symbol);
    this.setState(state);
    this.setCost(cost);
  } //end constructor Symbol
  
  /**
   * Gets the <code>symbol</code> of a lending library.
   * 
   * @return symbol - the <code>symbol</code> of a lending library.
   * @see #symbol
   * @see #setSymbol(String)
   * @since 1.0
   */
  public String getSymbol() {
    return this.symbol;
  } // end method getSymbol
  
  /**
   * Sets the <code>symbol</code> of a lending library.
   * 
   * @param symbol the <code>symbol</code> of a lending library to set.
   * @see #symbol
   * @see #getSymbol()
   * @since 1.0
   */
  public void setSymbol(String symbol) {
    this.symbol = symbol;
  } // end method setSymbol

  /**
   * Gets the <code>state</code> abbreviation of a lending library.
   * 
   * @return state - the <code>state</code> abbreviation of a lending library.
   * @see #state
   * @see #setState(String)
   * @since 1.0
   */
  public String getState() {
    return state;
  } // end method getState
  
  /**
   * Sets the <code>state</code> abbreviation of a lending library.
   * 
   * @param state  the <code>state</code> abbreviation of a lending library to set.
   * @see #state
   * @see #getState()
   * @since 1.0
   */
  public void setState(String state) {
    this.state = state;
  } // end method setState
  
  /**
   * Gets the <code>cost</code> type of a lending library.
   * 
   * @return cost - the cost type of a lending library.
   * @see #cost
   * @see #setCost(String)
   * @since 1.0
   */
  public String getCost() {
    return cost;
  } // end method getCost
  
  /**
   * Sets the <code>cost</code> type of a lending library.
   * 
   * @param cost  the <code>cost</code> type of a lending library set.
   * @see #cost
   * @see #getCost()
   * @since 1.0
   */
  public void setCost(String cost) {
    this.cost = cost;
  } // end method setCost
  
} // end class Symbol.