package com.example.lab_2;

public class Pizza {
    public int id;
    public String name;
    public double price;
    public int mass;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public Pizza() {}

    public void setPrice(double price) {
        this.price = price;
    }
    public double getPrice() {
        return price;
    }


    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }


    public int getMass() {
        return mass;
    }
    public void setMass(int mass) {
        this.mass = mass;
    }


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
