package com.example.demo.task2;


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

    public List<Pizza> getAllPizzas() {

        try (Statement statement = this.connection.createStatement()) {

            List<Pizza> pizzas = new ArrayList<Pizza>();

            ResultSet resultSet = statement.executeQuery("SELECT id, name, mass, price FROM pizza");

            while (resultSet.next()) {
                pizzas.add(new Pizza(resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("mass"),
                        resultSet.getDouble("price")));
            }
            return pizzas;

        } catch (SQLException e) {
            e.printStackTrace();
            // Если произошла ошибка - возвращаем пустую коллекцию
            return Collections.emptyList();
        }
    }

    public List<Pizza> getExpensivePizzas(int barrierPrice) {

        try (Statement statement = this.connection.createStatement()) {

            List<Pizza> pizzas = new ArrayList<Pizza>();

            ResultSet resultSet = statement
                    .executeQuery("SELECT id, name, mass, price FROM pizza WHERE price >" + barrierPrice);

            while (resultSet.next()) {
                pizzas.add(new Pizza(resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("mass"),
                        resultSet.getDouble("price")));
            }
            return pizzas;

        } catch (SQLException e) {
            e.printStackTrace();
            // Если произошла ошибка - возвращаем пустую коллекцию
            return Collections.emptyList();
        }

    }

    public List<Pizza> getBeetweenPizzas(int minPrice, int maxPrice) {

        try (Statement statement = this.connection.createStatement()) {

            List<Pizza> pizzas = new ArrayList<Pizza>();

            ResultSet resultSet = statement
                    .executeQuery("SELECT id, name, mass, price FROM pizza WHERE price >" + minPrice + "AND price <" + maxPrice);

            while (resultSet.next()) {
                pizzas.add(new Pizza(resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("mass"),
                        resultSet.getDouble("price")));
            }
            return pizzas;

        } catch (SQLException e) {
            e.printStackTrace();
            // Если произошла ошибка - возвращаем пустую коллекцию
            return Collections.emptyList();
        }

    }


    public void addPizza(Pizza pizza) {
        try(PreparedStatement statement = this.connection.prepareStatement(
                "INSERT INTO Pizza (mass, price, name) Values (?, ?, ?)")) {
            statement.setObject(3, pizza.name);
            statement.setObject(1, pizza.mass);
            statement.setObject(2, pizza.price);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletePizza(int id) {
        try (PreparedStatement statement = this.connection.prepareStatement(
                "DELETE FROM Pizza WHERE id = ?")) {
            statement.setObject(1, id);
            // Выполняем запрос
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updatePricePizza(int id) {
        try (PreparedStatement statement = this.connection.prepareStatement(
                "UPDATE Pizza SET price = price*1.05 WHERE id = ?")) {
            statement.setObject(1, id);
            // Выполняем запрос
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}