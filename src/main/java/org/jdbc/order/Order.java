package org.jdbc.order;

import org.jdbc.order.util.DataTransferObject;

import java.util.Date;
import java.util.List;

public class Order implements DataTransferObject {
  /*
  * Some members don't belong to Order table, but due to the relation the table has with the
  * others it can handle them
  * */
  private long id;
  private String customerFirstName;
  private String customerLastName;
  private String customerEmail;
  private Date creationDate;
  private int totalDue;
  private String status;
  private String salesPersonFirstName;
  private String salesPersonLastName;
  private String salesPersonEmail;
  private List<OrderLine> orderLines;
  /*
  * Due to Order and OrderLine tables has a relation <Order has one or more Order lines>
  * we helps us with composition pattern, delegate handle those data to a sub-object "orderLines"
  * */
  @Override
  public long getId() {
    return this.id;
  }

  public void setId(long id) {
    this.id = id;
  }

  @Override
  public String toString() {
    return "Order{" +
            "id=" + id +
            ", customerFirstName='" + customerFirstName + '\'' +
            ", customerLastName='" + customerLastName + '\'' +
            ", customerEmail='" + customerEmail + '\'' +
            ", creationDate=" + creationDate +
            ", totalDue=" + totalDue +
            ", status='" + status + '\'' +
            ", salesPersonFirstName='" + salesPersonFirstName + '\'' +
            ", salesPersonLastName='" + salesPersonLastName + '\'' +
            ", salesPersonEmail='" + salesPersonEmail + '\'' +
            ", orderLines=" + orderLines +
            '}';
  }

  public String getCustomerFirstName() {
    return customerFirstName;
  }

  public void setCustomerFirstName(String customerFirstName) {
    this.customerFirstName = customerFirstName;
  }

  public String getCustomerLastName() {
    return customerLastName;
  }

  public void setCustomerLastName(String customerLastName) {
    this.customerLastName = customerLastName;
  }

  public String getCustomerEmail() {
    return customerEmail;
  }

  public void setCustomerEmail(String customerEmail) {
    this.customerEmail = customerEmail;
  }

  public Date getCreationDate() {
    return creationDate;
  }

  public void setCreationDate(Date creationDate) {
    this.creationDate = creationDate;
  }

  public int getTotalDue() {
    return totalDue;
  }

  public void setTotalDue(int totalDue) {
    this.totalDue = totalDue;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getSalesPersonFirstName() {
    return salesPersonFirstName;
  }

  public void setSalesPersonFirstName(String salesPersonFirstName) {
    this.salesPersonFirstName = salesPersonFirstName;
  }

  public String getSalesPersonLastName() {
    return salesPersonLastName;
  }

  public void setSalesPersonLastName(String salesPersonLastName) {
    this.salesPersonLastName = salesPersonLastName;
  }

  public String getSalesPersonEmail() {
    return salesPersonEmail;
  }

  public void setSalesPersonEmail(String salesPersonEmail) {
    this.salesPersonEmail = salesPersonEmail;
  }

  public List<OrderLine> getOrderLines() {
    return orderLines;
  }

  public void setOrderLines(List<OrderLine> orderLines) {
    this.orderLines = orderLines;
  }
}
