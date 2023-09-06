package com.example.lab_3;

public class Addish {
    public int id;
    public String name;
    public double price;
    public String type;

    public Addish() {

    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public Addish(int id, String name, double price, String type) {
        this.id = id;
        this.type = type;
        this.price = price;
        this.name = name;
    }

    public Addish(String name, String type, double price ) {
        this.price = price;
        this.type = type;
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format("ID: %s | Название: %s | Тип: %s | Цена: %s",
                this.id, this.name, this.type, this.price);
    }
}
