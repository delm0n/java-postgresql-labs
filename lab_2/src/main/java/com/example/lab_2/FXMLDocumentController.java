package com.example.lab_2;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;


import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

//import org.sqlite.JDBC;
import java.sql.*;

import javafx.scene.control.TableColumn;


public class FXMLDocumentController implements Initializable{

    @FXML
    private Label label;
    private ObservableList<Pizza> userData = FXCollections.observableArrayList();

    @FXML
    private TableView<Pizza> tableUsers;

    @FXML
    private TableColumn<Pizza, Integer> idCol;

    @FXML
    private TableColumn<Pizza, String> nameCol;

    @FXML
    private TableColumn<Pizza, Double> priceCol;

    @FXML
    private TableColumn<Pizza, Integer> massCol;

    @FXML
    private void handlButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello!");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //initData();

        idCol.setCellValueFactory(new PropertyValueFactory<Pizza, Integer>("id"));
        nameCol.setCellValueFactory(new PropertyValueFactory<Pizza, String>("name"));
        priceCol.setCellValueFactory(new PropertyValueFactory<Pizza, Double>("price"));
        massCol.setCellValueFactory(new PropertyValueFactory<Pizza, Integer>("mass"));

        tableUsers.setItems(userData);

    }

    @FXML
    public void handleButtonAction(ActionEvent event) {
        initData();
    }

    private void initData() {
        try {

             final String DB_URL = "jdbc:postgresql://localhost:5432/java_demo?currentSchema=public";
             //final String DB_URL = "jdbc:postgresql://localhost:5432/java_lab2?currentSchema=public";
             final String USER = "postgres";
             final String PASS = "123";
             Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement statement = connection.createStatement();

             ResultSet resultSet = statement.executeQuery("SELECT * FROM pizza");
             //ResultSet resultSet = statement.executeQuery("SELECT * FROM products");
             while (resultSet.next()) {
                 Pizza pizza = new Pizza();

                 pizza.setId(resultSet.getInt("id"));
                 pizza.setName(resultSet.getString("name"));
                 pizza.setPrice(resultSet.getDouble("price"));
                 pizza.setMass(resultSet.getInt("mass")); //???\

                 userData.add(pizza);
             }


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
