module com.example.lab_3 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    requires javafx.graphics;
    //exports com.example.lab_3 to javafx.graphics;


    opens com.example.lab_3 to javafx.fxml;
    exports com.example.lab_3;
}