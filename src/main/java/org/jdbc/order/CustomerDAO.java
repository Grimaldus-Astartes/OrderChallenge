package org.jdbc.order;

import org.jdbc.order.util.DataAccessObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO extends DataAccessObject<Customer> {
    private static final String GET_ALL_LMT = "SELECT customer_id, first_name, last_name, email, phone," +
            " address, city, state, zipcode FROM customer ORDER BY last_name, first_name LIMIT ?";

    public CustomerDAO(Connection connection) {
        super(connection);
    }

    @Override
    public Customer findById(long id) {
        return null;
    }

    @Override
    public List<Customer> findAll() {
        return null;
    }

    public List<Customer> findallSorted(int limit){
        List<Customer> customers = new ArrayList<>();
        try(PreparedStatement statement = this.connection.prepareStatement(GET_ALL_LMT)){
            statement.setInt(1, limit);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                Customer customer = new Customer();
                customers.add(customer);
                customer.setId(resultSet.getLong(1));
                customer.setFirstName(resultSet.getString(2));
                customer.setLastName(resultSet.getString(3));
                customer.setEmail(resultSet.getString(4));
                customer.setPhone(resultSet.getString(5));
                customer.setAddress(resultSet.getString(6));
                customer.setCity(resultSet.getString(7));
                customer.setState(resultSet.getString(8));
                customer.setZipcode(resultSet.getString(9));
            }
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
        return customers;
    }

}
