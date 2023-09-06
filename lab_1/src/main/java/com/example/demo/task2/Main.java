package com.example.demo.task2;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String args[]) {
        try {
            DbHandler dbHandler = new DbHandler();
//добавление
//            dbHandler.addPizza(new Pizza(400,200.0, "Гавайская"));
//            dbHandler.addPizza(new Pizza(500,300.0, "Охотничья"));
//            dbHandler.addPizza(new Pizza(450,250.0, "Сырная"));
//            dbHandler.addPizza(new Pizza(550,400.0, "Фирменная"));


//удаление
//            dbHandler.deletePizza(4);


//вывод пицц с ценой выше 249
//            List<Pizza> pizzas = dbHandler.getExpensivePizzas(249);


//вывод пицц в заданном ценовом диапазоне
//           List<Pizza> pizzas = dbHandler.getBeetweenPizzas(190,290);


           List<Pizza> pizzas = dbHandler.getAllPizzas();
            for (Pizza pizza: pizzas) {
                System.out.println(pizza.toString());
            }

//            dbHandler.updatePricePizza(3);
//            System.out.println("После обновления:");
//            pizzas = dbHandler.getAllPizzas();
//            for (Pizza pizza: pizzas) {
//                System.out.println(pizza.toString());
//            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
