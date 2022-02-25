package service;

import model.Customer;
import model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerImpl implements CustomerService {

    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection
                    ("jdbc:mysql://localhost:3306/demo2001?useSSL=false", "root", "123456");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

    @Override
    public List<Customer> findAll() {
        List<Customer> list = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement
                     ("select  * from customer")) {
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                int productId = rs.getInt("productId");
                list.add(new Customer(id, name, age, productId));
            }
        } catch (SQLException e) {
            System.out.println("");
        }
        return list;
    }

    @Override
    public void add(Customer customer) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement
                     ("insert into customer(id,name, age, productId)  values (?,?,?,?) ");) {
            preparedStatement.setInt(1, customer.getId());
            preparedStatement.setString(2, customer.getName());
            preparedStatement.setDouble(3, customer.getAge());
            preparedStatement.setInt(4, customer.getProductId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("error");
        }
    }

    @Override
    public Customer findById(int id) {
        Customer customer = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement
                     ("select * from customer where id =?")) {
            System.out.println(preparedStatement);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                int age = Integer.parseInt(resultSet.getString("age"));
                int productId = Integer.parseInt(resultSet.getString("productId"));
                customer = new Customer(id, name, age, productId);
            }
        } catch (SQLException ignored) {
        }
        return customer;
    }

    @Override
    public boolean delete(int id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement
                     ("delete from customer where id = ?")) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    @Override
    public boolean update(Customer customer) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement
                     ("update customer set name = ?,age =?, productId=? where id = ?;")) {
            preparedStatement.setString(1, customer.getName());
            preparedStatement.setDouble(2, customer.getAge());
            preparedStatement.setInt(3, customer.getProductId());
            preparedStatement.setInt(4, customer.getId());
            rowUpdated = preparedStatement.executeUpdate() > 0;
        }
        return rowUpdated;
    }
}
