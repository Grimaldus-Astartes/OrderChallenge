package org.jdbc.order;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

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
      OrderDAO orderDAO = new OrderDAO(connection);
      Order order = orderDAO.findById(1000);
      System.out.println(order);
    } catch (SQLException e){
      System.err.println(e.getErrorCode());
      e.printStackTrace();
    }
  }
}
