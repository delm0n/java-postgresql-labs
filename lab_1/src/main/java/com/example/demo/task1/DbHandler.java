package com.example.demo.task1;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class DbHandler {

    static final String DB_URL = "jdbc:postgresql://localhost:5432/java_demo?currentSchema=public";
    static final String USER = "postgres";
    static final String PASS = "123";
    private Connection connection;

    DbHandler() throws SQLException {

        this.connection = DriverManager.getConnection(DB_URL, USER, PASS);
    }

    public List<Product> getAllProducts() {
        // Statement используется для того, чтобы выполнить sql-запрос
        try (Statement statement = this.connection.createStatement()) {
            // В данный список будем загружать данные, полученные из БД
            List<Product> products = new ArrayList<Product>();
            // В resultSet помещаем результат запроса,
            // который выполняется командой statement.executeQuery()
            ResultSet resultSet = statement.executeQuery("SELECT id, good, price, category_name FROM products");

            while (resultSet.next()) {
                products.add(new Product(resultSet.getInt("id"),
                        resultSet.getString("good"),
                        resultSet.getDouble("price"),
                        resultSet.getString("category_name")));
            }

            return products;

        } catch (SQLException e) {
            e.printStackTrace();
            // Если произошла ошибка - возвращаем пустую коллекцию
            return Collections.emptyList();
        }
    }

    public void addProduct(Product product) {
        try(PreparedStatement statement = this.connection.prepareStatement(
                "INSERT INTO Products (good, price, category_name) Values (?, ?, ?)")) {
            statement.setObject(1, product.good);
            statement.setObject(2, product.price);
            statement.setObject(3, product.category_name);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteProduct(int id) {
        try (PreparedStatement statement = this.connection.prepareStatement(
                "DELETE FROM Products WHERE id = ?")) {
            statement.setObject(1, id);
            // Выполняем запрос
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
