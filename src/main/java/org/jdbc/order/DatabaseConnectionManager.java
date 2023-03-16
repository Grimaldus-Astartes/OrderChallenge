package org.jdbc.order;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnectionManager {
  private final String url;
  private final Properties properties;

  public DatabaseConnectionManager(
      String host, String dabaseName, String userName, String password) {
    this.url = "jdbc:postgresql://" + host + "/" + dabaseName;
    this.properties = new Properties();
    this.properties.setProperty("user", userName);
    this.properties.setProperty("password", password);
  }

  public Connection getConnection() throws SQLException {
    try {
      return DriverManager.getConnection(this.url, this.properties);
    } catch (SQLException e) {
      throw new SQLException(e + "\n " + url + "\n " + properties);
    }
  }
}
