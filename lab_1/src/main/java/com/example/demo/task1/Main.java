package com.example.demo.task1;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String args[]) {
        try {
            DbHandler dbHandler = new DbHandler();

            //dbHandler.addProduct(new Product("Музей",200 , "Развлечения"));
            //dbHandler.addProduct(new Product("Экскурсия",150 , "Развлечения"));
            //dbHandler.deleteProduct(2);

            List<Product> products = dbHandler.getAllProducts();

            for (Product product: products) {
                System.out.println(product.toString());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
