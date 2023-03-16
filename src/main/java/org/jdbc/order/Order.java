package org.jdbc.order;

import org.jdbc.order.util.DataTransferObject;

import java.util.Date;

public class Order implements DataTransferObject {
  private long id;
  private Date creationDate;
  private int totalDue;
  private String status;
  private long customerId;
  private long salesPersonId;

  @Override
  public long getId() {
    return 0;
  }

  @Override
  public String toString() {
    return "Order{" +
            "id=" + id +
            ", creationDate=" + creationDate +
            ", totalDue=" + totalDue +
            ", status='" + status + '\'' +
            ", customerId=" + customerId +
            ", salesPersonId=" + salesPersonId +
            '}';
  }

  public void setId(long id) {
    this.id = id;
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

  public long getCustomerId() {
    return customerId;
  }

  public void setCustomerId(long customerId) {
    this.customerId = customerId;
  }

  public long getSalesPersonId() {
    return salesPersonId;
  }

  public void setSalesPersonId(long salesPersonId) {
    this.salesPersonId = salesPersonId;
  }
}
