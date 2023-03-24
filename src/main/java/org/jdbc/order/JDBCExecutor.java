package org.jdbc.order;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JDBCExecutor {
  static String host;
  static String dabaseName;
  static String userName;
  static String password;

  static {
    host = "localhost";
    dabaseName = "hplussport";
    userName = "postgres";
    password = "password";
  }

  public static void main(String[] args) {
    //
    DatabaseConnectionManager dbm = new DatabaseConnectionManager(host, dabaseName,
            userName, password);
    try{
      Connection connection = dbm.getConnection();
      CustomerDAO customerDAO = new CustomerDAO(connection);
      customerDAO.findallSorted(20).forEach(System.out::println);

    } catch (SQLException e){
      System.err.println(e.getErrorCode() + " ffdfdfd");
      e.printStackTrace();
    }
  }
}
