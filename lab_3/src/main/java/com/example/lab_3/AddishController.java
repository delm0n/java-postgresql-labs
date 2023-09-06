package com.example.lab_3;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AddishController implements Initializable {

    static final String DB_URL = "jdbc:postgresql://localhost:5432/java_demo?currentSchema=public";
    static final String USER = "postgres";
    static final String PASS = "123";
    private Connection connection;

    @FXML //  fx:id="combo"
    private ComboBox<String> combo;

    @FXML
    private Hyperlink toDictionary;

    @FXML
    private TextField name;

    @FXML
    private TextField price;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        combo.getItems().addAll("Напитки", "Салаты", "Горячее", "Закуски");
    }

    @FXML
    public void openDictionary(ActionEvent event) throws IOException {

        Stage stage = (Stage) toDictionary.getScene().getWindow();
        stage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("dictionary-view.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Зуев, 3 лабораторная работа");
        stage.setScene(new Scene(root1));
        stage.show();
    }

    @FXML
    public void addNewAddish() {
        try {
            //System.out.println(name.getText() + " " + price.getText() + " " + combo.getValue());
            try {
                this.connection = DriverManager.getConnection(DB_URL, USER, PASS);

                PreparedStatement statement = this.connection
                        .prepareStatement("INSERT INTO addish (name, type, price) Values (?, ?, ?)");
                statement.setObject(1, name.getText());
                statement.setObject(2, combo.getValue());
                statement.setObject(3, Double.valueOf(price.getText()));
                statement.execute();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }

        }
        catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка!");
            alert.setHeaderText("Неверный формат введённого значения");
            alert.setContentText("Заполните все поля и проверьте на соответствие форматов!");

            alert.showAndWait();
        }
    }


}