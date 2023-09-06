package com.example.demo.task2;

public class Pizza {
    public int id;
    public String name;
    public double price;
    public int mass;


    public Pizza(int id, String name, int mass, double price) {
        this.id = id;
        this.mass = mass;
        this.price = price;
        this.name = name;
    }

    public Pizza(int mass, double price, String name) {
        this.price = price;
        this.mass = mass;
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format("ID: %s | Название: %s | Масса: %s | Цена: %s",
                this.id, this.name, this.mass, this.price);
    }
}
