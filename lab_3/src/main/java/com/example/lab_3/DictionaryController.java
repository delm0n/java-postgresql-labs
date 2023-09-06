package com.example.lab_3;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class DictionaryController implements Initializable {

    static final String DB_URL = "jdbc:postgresql://localhost:5432/java_demo?currentSchema=public";
    static final String USER = "postgres";
    static final String PASS = "123";
    private Connection connection;

    private ObservableList<Addish> addishes = FXCollections.observableArrayList();

    @FXML //  fx:id="combo"
    private ComboBox<String> combo;

    @FXML
    private Hyperlink toAddish;

    @FXML
    private TableView<Addish> tableAddish;

    @FXML
    private TableColumn<Addish, Integer> idCol;

    @FXML
    private TableColumn<Addish, String> nameCol;

    @FXML
    private TableColumn<Addish, Double> priceCol;

    @FXML
    private TableColumn<Addish, String> typeCol;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        //initComboBox();

        combo.getItems().addAll("Напитки", "Салаты", "Горячее", "Закуски");

        idCol.setCellValueFactory(new PropertyValueFactory<Addish, Integer>("id"));
        nameCol.setCellValueFactory(new PropertyValueFactory<Addish, String>("name"));
        priceCol.setCellValueFactory(new PropertyValueFactory<Addish, Double>("price"));
        typeCol.setCellValueFactory(new PropertyValueFactory<Addish, String>("type"));

        tableAddish.setItems(addishes);

    }

    private void initComboBox() {
        try {

            final String DB_URL = "jdbc:postgresql://localhost:5432/java_demo?currentSchema=public";
            //final String DB_URL = "jdbc:postgresql://localhost:5432/java_lab2?currentSchema=public";
            final String USER = "postgres";
            final String PASS = "123";
            Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT id FROM addish");
            //ResultSet resultSet = statement.executeQuery("SELECT * FROM products");
            while (resultSet.next()) {
                combo.getItems().add(String.valueOf(resultSet.getInt("id")));
            }



        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void searchID() {

        addishes.clear();

        if (combo.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка!");
            alert.setHeaderText("Вы не выбрали ID");
            alert.setContentText("Выберите ID из предложенного списка!");

            alert.showAndWait();
        }
        else {
            try {
                this.connection = DriverManager.getConnection(DB_URL, USER, PASS);
                Statement statement = connection.createStatement();

                ResultSet resultSet = statement.executeQuery("SELECT * FROM addish WHERE id = " + Integer.valueOf(combo.getValue()));
                //ResultSet resultSet = statement.executeQuery("SELECT * FROM products");
                while (resultSet.next()) {
                    Addish addish = new Addish();

                    addish.setId(resultSet.getInt("id"));
                    addish.setName(resultSet.getString("name"));
                    addish.setPrice(resultSet.getDouble("price"));
                    addish.setType(resultSet.getString("type")); //???\

                    addishes.add(addish);
                }

            }
            catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }

    public void whereType() {

        addishes.clear();

        if (combo.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка!");
            alert.setHeaderText("Вы не выбрали ID");
            alert.setContentText("Выберите ID из предложенного списка!");

            alert.showAndWait();
        }
        else {
            try {
                this.connection = DriverManager.getConnection(DB_URL, USER, PASS);
                Statement statement = connection.createStatement();

//                String sql = "SELECT * FROM addish WHERE type =  ?";
//                PreparedStatement preparedStatement = connection.prepareStatement(sql);
//                preparedStatement.setString(1, combo.getValue());
                String str = combo.getValue();

                ResultSet resultSet = statement.executeQuery("SELECT * FROM addish WHERE type = '" + str + "' ");
                //ResultSet resultSet = statement.executeQuery("SELECT * FROM products");
                while (resultSet.next()) {
                    Addish addish = new Addish();

                    addish.setId(resultSet.getInt("id"));
                    addish.setName(resultSet.getString("name"));
                    addish.setPrice(resultSet.getDouble("price"));
                    addish.setType(resultSet.getString("type")); //???\

                    addishes.add(addish);
                }

            }
            catch (SQLException e) {
                e.printStackTrace();
            }


        }

    }

    @FXML
    public void openAddish(ActionEvent event) throws IOException {

        Stage stage = (Stage) toAddish.getScene().getWindow();
        stage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Зуев, 3 лабораторная работа");
        stage.setScene(new Scene(root1));
        stage.show();
    }
}
