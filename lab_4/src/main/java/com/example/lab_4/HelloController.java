package com.example.lab_4;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    static final String DB_URL = "jdbc:postgresql://localhost:5432/lab_4?currentSchema=public";
    static final String USER = "postgres";
    static final String PASS = "123";
    private Connection connection;

    private ObservableList<TableClass> pizzas = FXCollections.observableArrayList();

    @FXML //  fx:id="combo"
    private ComboBox<String> combo;

    @FXML //  fx:id="combo_2"
    private ComboBox<String> combo_2;

    @FXML
    private TableView<TableClass> tablePizzas;

    @FXML
    private TableColumn<TableClass, Integer> numberCol;

    @FXML
    private TableColumn<TableClass, String> cityCol;

    @FXML
    private TableColumn<TableClass, String> nameCol;

    @FXML
    private TableColumn<TableClass, String> sizeCol;

    @FXML
    private TableColumn<TableClass, Double> priceCol;

    @FXML
    private TableColumn<TableClass, Integer> massCol;

    private int numRe;

    @FXML
    private TextField cityColRe;



    public void changeCombo() throws SQLException {
        this.connection = DriverManager.getConnection(DB_URL, USER, PASS);
        Statement statement = connection.createStatement();
        try {

            List<String> city = new ArrayList<String>();
            ResultSet resultSet = statement
                    .executeQuery("SELECT city FROM pizzeria");

            while (resultSet.next()) {
                city.add(resultSet.getString("city"));
            }

            combo.getItems().addAll(city);



        } catch (SQLException e) {
            e.printStackTrace();
            // Если произошла ошибка - возвращаем пустую коллекцию

        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {


        //combo.getItems().addAll("Киров", "Пермь", "Москва");
        //changeCombo();
        combo_2.getItems().addAll("Охотника", "Гавайская", "Дьябло", "Бьянка", "Сырная");

        numberCol.setCellValueFactory(new PropertyValueFactory<TableClass, Integer>("num"));
        cityCol.setCellValueFactory(new PropertyValueFactory<TableClass, String >("city"));
        nameCol.setCellValueFactory(new PropertyValueFactory<TableClass, String>("name_pizza"));
        sizeCol.setCellValueFactory(new PropertyValueFactory<TableClass, String>("name_size"));
        priceCol.setCellValueFactory(new PropertyValueFactory<TableClass, Double>("price"));
        massCol.setCellValueFactory(new PropertyValueFactory<TableClass, Integer>("mass"));
        tablePizzas.setItems(pizzas);

        tablePizzas.setRowFactory( tv -> {
            TableRow<TableClass> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    TableClass rowData = row.getItem();

                    numRe = rowData.getNum();
                    cityColRe.setText(rowData.getCity());

                }
            });
            return row ;
        });

        try {
            changeCombo();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void UpdatePizza() {
        try (PreparedStatement statement = this.connection.prepareStatement(
                "UPDATE pizzeria SET city = ? WHERE num = ?")) {
            statement.setObject(1, cityColRe.getText());
            statement.setObject(2, numRe);
            // Выполняем запрос
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        getAll();
        try {
            combo.getItems().clear();
            changeCombo();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void getCity() {
        pizzas.clear();

        if (combo.getValue() == null && combo_2.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка!");
            alert.setHeaderText("Вы не выбрали фильтр");
            alert.setContentText("Выберите город или название пиццы из предложенного списка!");

            alert.showAndWait();
        }
        else {
            //сортировка только по городу
            if (combo_2.getValue() == null) {

                try {
                    this.connection = DriverManager.getConnection(DB_URL, USER, PASS);
                    Statement statement = connection.createStatement();
                    String str = combo.getValue();

                    ResultSet resultSet = statement.executeQuery("SELECT pizzeria.num, pizzeria.city, pizza.name_pizza, pizza_size.name_size, pizza_size.price, pizza_size.mass FROM pizzeria JOIN pizza ON pizzeria.num = pizza.pizza_number JOIN pizza_size ON pizza.id = pizza_size.pizza_id WHERE pizzeria.city = '" + str + "' ");
                    //ResultSet resultSet = statement.executeQuery("SELECT * FROM products");
                    while (resultSet.next()) {
                        TableClass pizza = new TableClass();

                        pizza.setNum(resultSet.getInt("num"));
                        pizza.setAddress_city(resultSet.getString("city"));
                        pizza.setName_pizza(resultSet.getString("name_pizza"));
                        pizza.setName_size(resultSet.getString("name_size"));
                        pizza.setPrice(resultSet.getDouble("price"));
                        pizza.setMass(resultSet.getInt("mass"));

                        pizzas.add(pizza);
                    }

                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
            else {
                //сортировка только по названию пиццы
                if (combo.getValue() == null) {

                    try {
                        this.connection = DriverManager.getConnection(DB_URL, USER, PASS);
                        Statement statement = connection.createStatement();
                        String str = combo_2.getValue();

                        ResultSet resultSet = statement.executeQuery("SELECT pizzeria.num, pizzeria.city, pizza.name_pizza, pizza_size.name_size, pizza_size.price, pizza_size.mass FROM pizzeria JOIN pizza ON pizzeria.num = pizza.pizza_number JOIN pizza_size ON pizza.id = pizza_size.pizza_id WHERE pizza.name_pizza = '" + str + "' ");
                        //ResultSet resultSet = statement.executeQuery("SELECT * FROM products");
                        while (resultSet.next()) {
                            TableClass pizza = new TableClass();

                            pizza.setNum(resultSet.getInt("num"));
                            pizza.setAddress_city(resultSet.getString("city"));
                            pizza.setName_pizza(resultSet.getString("name_pizza"));
                            pizza.setName_size(resultSet.getString("name_size"));
                            pizza.setPrice(resultSet.getDouble("price"));
                            pizza.setMass(resultSet.getInt("mass"));

                            pizzas.add(pizza);
                        }

                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                }

                else {
                    if (combo.getValue() != null && combo_2.getValue() != null) {

                        try {
                            this.connection = DriverManager.getConnection(DB_URL, USER, PASS);
                            Statement statement = connection.createStatement();
                            String city_str = combo.getValue();
                            String name_str = combo_2.getValue();

                            ResultSet resultSet = statement.executeQuery("SELECT pizzeria.num, pizzeria.city, pizza.name_pizza, pizza_size.name_size, pizza_size.price, pizza_size.mass FROM pizzeria JOIN pizza ON pizzeria.num = pizza.pizza_number JOIN pizza_size ON pizza.id = pizza_size.pizza_id WHERE pizza.name_pizza = '" + name_str + "' AND  pizzeria.city = '" + city_str + "' ");
                            //ResultSet resultSet = statement.executeQuery("SELECT * FROM products");
                            while (resultSet.next()) {
                                TableClass pizza = new TableClass();

                                pizza.setNum(resultSet.getInt("num"));
                                pizza.setAddress_city(resultSet.getString("city"));
                                pizza.setName_pizza(resultSet.getString("name_pizza"));
                                pizza.setName_size(resultSet.getString("name_size"));
                                pizza.setPrice(resultSet.getDouble("price"));
                                pizza.setMass(resultSet.getInt("mass"));

                                pizzas.add(pizza);
                            }

                        } catch (SQLException e) {
                            e.printStackTrace();
                        }

                    }
                }
            }
        }
    }

    public void getAll() {

        pizzas.clear();

            try {
                this.connection = DriverManager.getConnection(DB_URL, USER, PASS);
                Statement statement = connection.createStatement();
                String str = combo.getValue();

                ResultSet resultSet = statement.executeQuery("SELECT pizzeria.num, pizzeria.city, pizza.name_pizza, pizza_size.name_size, pizza_size.price, pizza_size.mass FROM pizzeria JOIN pizza ON pizzeria.num = pizza.pizza_number JOIN pizza_size ON pizza.id = pizza_size.pizza_id");
                while (resultSet.next()) {
                    TableClass pizza = new TableClass();

                    pizza.setNum(resultSet.getInt("num"));
                    pizza.setAddress_city(resultSet.getString("city"));
                    pizza.setName_pizza(resultSet.getString("name_pizza"));
                    pizza.setName_size(resultSet.getString("name_size"));
                    pizza.setPrice(resultSet.getDouble("price"));
                    pizza.setMass(resultSet.getInt("mass"));

                    pizzas.add(pizza);
                }

            }
            catch (SQLException e) {
                e.printStackTrace();
            }
    }
}