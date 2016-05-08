/*
 * Author: Dennis Grzegorzewski
 * Date: 05/08/2016
 * FileName: application.dao.DataSource.java
 * Class: ITMD 510
 * Assignment: Final Project
 * Archive: grzegorzewskiITMD510FP.zip
 * Due: 04/30/2016 11:59PM
 */
package application.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Creates a connection to a database.
 * 
 * @author Dennis Grzegorzewski
 * @version 1.01, 05/08/2016
 *
 */
public class DataSource implements AutoCloseable {
  
  /*
   * Declare and initialize class fields.
   */
  private final String URL = "jdbc:mysql://localhost"; // database protocal.
  private final String DB = "custom_holdings";         // database schema.
  private final String USERNAME = "Dennis";            // database username.
  private final String PASSWORD = "Dr5tgbhu8ik,";      // database password.
  
  /*
   * Declare instance fields.
   */
  // accessible to package and subclasses
  protected Connection databaseConnection;             // connection object.
  
  /**
   * Creates a connection to a database server and the custom_holdings database schema. 
   * 
   * Loads MySQL driver,
   * create the database schema if it does not exist,
   * create a connect to the database server.
   * 
   * @see #URL        database protocal
   * @see #DB         database schema
   * @see #USERNAME   database username
   * @see #PASSWORD   database password
   * @see #createDB() creates the database schema if it does not exist
   * @see #close()    closes the existing connection to a database.
   * @since 1.01
   */
  public DataSource() {
    
    try {
      // load mysql driver.
      Class.forName("com.mysql.jdbc.Driver");
      System.out.println("Load mysql driver.");
    } // end try.
    
    catch (ClassNotFoundException e) {
      System.out.println("Error loading database driver:" 
                         + e.getMessage());
    } // end catch.
    
    // create the database schema if it does not exist.
    createDB();
    
    try {
      // create a connect to the database server and the custom_holdings database schema.
      databaseConnection = DriverManager.getConnection(URL + "/" + DB, USERNAME, PASSWORD);
    } //end try.
    
    catch (SQLException e) {
      System.out.println("Error creating database server connection: " 
                         + e.getMessage());
      // terminate application.
      System.exit(-1);
    } //end catch.
    
  } //end constructor DataSource.
  
  /**
   * Creates the custom_holdings database schema and tables if they do not exist.
   * 
   * Creates a connect to the database server,
   * create the database schema if it does not exist,
   * create the users table if it does not exist,
   * create a default admin user if it does not exist,
   * create the paths table if it does not exist,
   * create the groups table if it does not exist,
   * create the symbols2groups table if it does not exist,
   * create the symbols table if it does not exist,
   * create the states table if it does not exist,
   * create the costs table if it does not exist.
   * 
   * @see #databaseConnection connection object.
   * @see #URL                database protocal
   * @see #USERNAME           database username
   * @see #PASSWORD           database password
   * @since 1.01
   */
  public void createDB() {
    
    try {
      // create a connect to the database server.
      databaseConnection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
      System.out.println("Connect to the database server.");
    } //end try
    
    catch (SQLException e) {
      System.out.println("Error creating database server connection: " 
                         + e.getMessage());
      // terminate application.
      System.exit(-1);
    } //end catch.

    // create the database schema.
    String query = "CREATE SCHEMA IF NOT EXISTS `custom_holdings`";
    try (PreparedStatement statement 
        = databaseConnection.prepareStatement(query)){
      statement.executeUpdate();
      System.out.println("Create the database.");
    } //end try.
    
    catch (SQLException e) {
      System.out.println("Error creating custom_holdings database: " 
                         + e.getMessage());
      // terminate application.
      System.exit(-1);
    } //end catch.
    
    // create the users table.
    query = "CREATE TABLE IF NOT EXISTS `custom_holdings`.`users` ( " 
          + "`username` VARCHAR(45) NOT NULL, " 
          + "`password` VARCHAR(45) NOT NULL, " 
          + "`cleared` TINYINT(1) NOT NULL, " 
          + "`admin` TINYINT(1) NOT NULL, " 
          + "PRIMARY KEY (`username`), " 
          + "UNIQUE INDEX `username_UNIQUE` (`username` ASC))";
    try (PreparedStatement statement 
        = databaseConnection.prepareStatement(query)){
      statement.executeUpdate();
      System.out.println("Create the users table.");
    } //end try.
    
    catch (SQLException e) {
      System.out.println("Error creating users table: " 
                         + e.getMessage());
      // terminate application.
      System.exit(-1);
    } //end catch.
    
    // create a default admin user.
    query = "INSERT INTO `custom_holdings`.`users` " 
          + "(`username`, `password`, `cleared`, `admin`) " 
          + "VALUES ('admin', 'admin', true, true) "
          + "ON DUPLICATE KEY UPDATE "
          + "`username` = 'admin', "
          + "`password` = 'admin', "
          + "`cleared` = true, "
          + "`admin` = true;";
    try (PreparedStatement statement 
        = databaseConnection.prepareStatement(query)){
      statement.executeUpdate();
      System.out.println("Create a default admin user.");
    } //end try.
    
    catch (SQLException e) {
      System.out.println("Error creating default admin user: " 
                         + e.getMessage());
      // terminate application.
      System.exit(-1);
    } //end catch.
    
    // create the paths table.
    query = "CREATE TABLE IF NOT EXISTS `custom_holdings`.`paths` ( " 
          + "`name` VARCHAR(45) NOT NULL, " 
          + "`description` VARCHAR(255) NOT NULL, " 
          + "`user_username` VARCHAR(45) NOT NULL, " 
          + "PRIMARY KEY (`name`), " 
          + "UNIQUE INDEX `name_UNIQUE` (`name` ASC), " 
          + "INDEX `user_username_idx` (`user_username` ASC))";
    try (PreparedStatement statement 
        = databaseConnection.prepareStatement(query)){
      statement.executeUpdate();
      System.out.println("Create the paths table.");
    } //end try.
    
    catch (SQLException e) {
      System.out.println("Error creating paths table: " 
                         + e.getMessage());
      // terminate application.
      System.exit(-1);
    } //end catch.
    
    // create the groups table.
    query = "CREATE TABLE IF NOT EXISTS `custom_holdings`.`groups` ( " 
          + "`name` VARCHAR(45) NOT NULL, " 
          + "`description` VARCHAR(255) NOT NULL, " 
          + "`path_name` VARCHAR(45) NOT NULL, " 
          + "PRIMARY KEY (`name`), " 
          + "UNIQUE INDEX `name_UNIQUE` (`name` ASC), " 
          + "INDEX `path_name_idx` (`path_name` ASC))";
    try (PreparedStatement statement 
        = databaseConnection.prepareStatement(query)){
      statement.executeUpdate();
      System.out.println("Create the groups table.");
    } //end try.
    
    catch (SQLException e) {
      System.out.println("Error creating groups table: " 
                         + e.getMessage());
      // terminate application.
      System.exit(-1);
    } //end catch.
    
    // create the symbols2groups table.
    query = "CREATE TABLE IF NOT EXISTS `custom_holdings`.`symbols2groups` ( " 
          + "`symbol_code` VARCHAR(45) NOT NULL, " 
          + "`group_name` VARCHAR(45) NOT NULL, " 
          + "INDEX `symbol_code_idx` (`symbol_code` ASC), " 
          + "INDEX `group_name_idx` (`group_name` ASC))";
    try (PreparedStatement statement 
        = databaseConnection.prepareStatement(query)){
      statement.executeUpdate();
      System.out.println("Create the symbols2groups table.");
    } //end try.
    
    catch (SQLException e) {
      System.out.println("Error creating symbols2groups table: " 
                         + e.getMessage());
      // terminate application.
      System.exit(-1);
    } //end catch.
    
    // create the symbols table.
    query = "CREATE TABLE IF NOT EXISTS `custom_holdings`.`symbols` ( " 
          + "`symbol_code` VARCHAR(45) NOT NULL, " 
          + "`state_abbreviation` VARCHAR(45) NOT NULL, " 
          + "`cost_type` VARCHAR(45) NOT NULL, " 
          + "PRIMARY KEY (`symbol_code`), " 
          + "UNIQUE INDEX `symbol_code_UNIQUE` (`symbol_code` ASC), " 
          + "INDEX `cost_type_idx` (`cost_type` ASC), " 
          + "INDEX `state_abbreviation_idx` (`state_abbreviation` ASC))";
    try (PreparedStatement statement 
        = databaseConnection.prepareStatement(query)){
      statement.executeUpdate();
      System.out.println("Create the symbols table.");
    } //end try.
    
    catch (SQLException e) {
      System.out.println("Error creating symbols table: " 
                         + e.getMessage());
      // terminate application.
      System.exit(-1);
    } //end catch.
    
    // create the states table.
    query = "CREATE TABLE IF NOT EXISTS `custom_holdings`.`states` ( " 
          + "`abbreviation` VARCHAR(45) NOT NULL, " 
          + "`zone` INT(11) NOT NULL, " 
          + "PRIMARY KEY (`abbreviation`), " 
          + "UNIQUE INDEX `abbreviation_UNIQUE` (`abbreviation` ASC), " 
          + "INDEX `zone_idx` (`zone` ASC))";
    try (PreparedStatement statement 
        = databaseConnection.prepareStatement(query)){
      statement.executeUpdate();
      System.out.println("Create the states table.");
    } //end try.
    
    catch (SQLException e) {
      System.out.println("Error creating states table: " 
                         + e.getMessage());
      // terminate application.
      System.exit(-1);
    } //end catch.
    
    // create the costs table.
    query = "CREATE TABLE IF NOT EXISTS `custom_holdings`.`costs` ( " 
          + "`type` VARCHAR(45) NOT NULL, " 
          + "PRIMARY KEY (`type`), " 
          + "UNIQUE INDEX `type_UNIQUE` (`type` ASC))";
    try (PreparedStatement statement 
        = databaseConnection.prepareStatement(query)){
      statement.executeUpdate();
      System.out.println("Create the costs table.");
    } //end try.
    
    catch (SQLException e) {
      System.out.println("Error creating costs table: " 
                         + e.getMessage());
      // terminate application.
      System.exit(-1);
    } //end catch.
    
    // close the existing connection to the database.
    close();
  }
  
  /**
   * Closes the existing connection to a database.
   * 
   * @see #databaseConnection
   * @see java.lang.AutoCloseable#close()
   * @since 1.0
   */
  @Override
  public void close() {
    
    try {
      databaseConnection.close();
      databaseConnection = null;
    } //end try.
    
    catch(SQLException e) {
      System.out.println("Error closing database connection: " 
                         + e.getMessage());
    } //end catch.
    
  } //end method close.
  
} // end class DataSource.