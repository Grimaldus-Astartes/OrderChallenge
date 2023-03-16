package org.jdbc.order;

import org.jdbc.order.util.DataAccessObject;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class OrderDAO extends DataAccessObject<Order> {
  private static final String GET_ONE = "SELECT * FROM orders ORDER BY order_id DESC limit 1";

  public OrderDAO(Connection connection) {
    super(connection);
  }

  @Override
  public Order findById(long id) {
    Order order = new Order();
    try (Statement statement = this.connection.createStatement()) {
      ResultSet resultSet = statement.executeQuery(GET_ONE);
      while (resultSet.next()) {
        order.setId(resultSet.getLong("order_id"));
        order.setCreationDate(resultSet.getDate("creation_date"));
        order.setTotalDue(resultSet.getInt("total_due"));
        order.setStatus(resultSet.getString("status"));
        order.setCustomerId(resultSet.getLong("customer_id"));
        order.setSalesPersonId(resultSet.getLong("salesperson_id"));
      }
    } catch (SQLException e) {
      System.err.println(e.getErrorCode());
      e.printStackTrace();
    }
    return order;
  }
}
