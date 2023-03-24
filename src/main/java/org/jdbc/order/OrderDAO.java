package org.jdbc.order;

import org.jdbc.order.util.DataAccessObject;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO extends DataAccessObject<Order> {
  private static final String GET_ONE =
      "SELECT "
          + "c.first_name, c.last_name, c.email, o.order_id, "
          + "o.creation_date, o.total_due, o.status, "
          + "s.first_name, s.last_name, s.email,"
          + "ol.quantity, p.code, p.name, p.size, p.variety, p.price "
          + "FROM orders o "
          + "join customer c on o.customer_id=c.customer_id "
          + "join salesperson s on "
          + "o.salesperson_id=s.salesperson_id "
          + "join order_item ol on ol.order_id=o.order_id "
          + "join product p on ol.product_id=p.product_id "
          + "WHERE o.order_id=?";
  private static final String GET_FOR_CUST = "SELECT * FROM get_orders_by_customer(?)";

  public OrderDAO(Connection connection) {
    super(connection);
  }

  @Override
  public Order findById(long id) {
    Order order = new Order();
    try (PreparedStatement statement = this.connection.prepareStatement(GET_ONE)) {
      statement.setLong(1, id);
      ResultSet resultSet = statement.executeQuery();
      long orderId = 0;
      List<OrderLine> orderLines = new ArrayList<>();
      while (resultSet.next()) {
        //This condition will protect us to update this information multiple times
        if (orderId == 0) {
          order.setCustomerFirstName(resultSet.getString(1));
          order.setCustomerLastName(resultSet.getString(2));
          order.setCustomerEmail(resultSet.getString(3));
          order.setId(resultSet.getLong(4));
          orderId = order.getId();//active our safeguard condition
          order.setCreationDate(new Date(resultSet.getDate(5).getTime()));
          order.setTotalDue(resultSet.getBigDecimal(6));
          order.setStatus(resultSet.getString(7));
          order.setSalespersonFirstName(resultSet.getString(8));
          order.setSalespersonLastName(resultSet.getString(9));
          order.setSalespersonEmail(resultSet.getString(10));
        }
        OrderLine orderLine = new OrderLine();
        orderLine.setQuantity(resultSet.getInt(11));
        orderLine.setProductCode(resultSet.getString(12));
        orderLine.setProductName(resultSet.getString(13));
        orderLine.setProductSize(resultSet.getInt(14));
        orderLine.setProductVariety(resultSet.getString(15));
        orderLine.setProductPrice(resultSet.getBigDecimal(16));
        orderLines.add(orderLine);
      }
      order.setOrderLines(orderLines);
    } catch (SQLException e) {
      System.err.println(e.getErrorCode());
      e.printStackTrace();
    }
    return order;
  }

  public List<Order> findAll(){
    return null;
  }


  public List<Order> getOrdersForCustomer(long customerId){
    List<Order> orders = new ArrayList<>();
    try(PreparedStatement statement = this.connection.prepareStatement(GET_FOR_CUST);){
      statement.setLong(1, customerId);
      ResultSet resultSet = statement.executeQuery();
      long orderId = 0;
      Order order = new Order();
      while(resultSet.next()){
        long localOrderId = resultSet.getLong(4);
        if(orderId!=localOrderId){
          order = new Order();
          orders.add(order);
          order.setId(localOrderId);
          orderId = localOrderId;
          order.setCustomerFirstName(resultSet.getString(1));
          order.setCustomerLastName(resultSet.getString(2));
          order.setCustomerEmail(resultSet.getString(3));
          order.setCreationDate(new Date(resultSet.getDate(5).getTime()));
          order.setTotalDue(resultSet.getBigDecimal(6));
          order.setStatus(resultSet.getString(7));
          order.setSalespersonFirstName(resultSet.getString(8));
          order.setSalespersonLastName(resultSet.getString(9));
          order.setSalespersonEmail(resultSet.getString(10));
          List<OrderLine> orderLines = new ArrayList<>();
          order.setOrderLines(orderLines);
        }
        OrderLine orderLine = new OrderLine();
        orderLine.setQuantity(resultSet.getInt(11));
        orderLine.setProductCode(resultSet.getString(12));
        orderLine.setProductName(resultSet.getString(13));
        orderLine.setProductSize(resultSet.getInt(14));
        orderLine.setProductVariety(resultSet.getString(15));
        orderLine.setProductPrice(resultSet.getBigDecimal(16));
        order.getOrderLines().add(orderLine);
      }
    }catch(SQLException e){
      e.printStackTrace();
      throw new RuntimeException(e);
    }
    return orders;
  }

}
