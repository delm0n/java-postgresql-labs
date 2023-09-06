package com.example.lab_4;

public class TableClass {

    public int num;
    public String city;
    public String name_pizza;
    public String name_size;
    public double price;
    public int mass;

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public void setNum(int number) {
        this.num = number;
    }

    public int getMass() {
        return mass;
    }

    public int getNum() {
        return num;
    }

    public void setAddress_city(String address_city) {
        this.city = address_city;
    }

    public void setMass(int mass) {
        this.mass = mass;
    }

    public String getCity() {
        return city;
    }

    public String getName_pizza() {
        return name_pizza;
    }

    public String getName_size() {
        return name_size;
    }

    public void setName_pizza(String name_pizza) {
        this.name_pizza = name_pizza;
    }

    public void setName_size(String name_size) {
        this.name_size = name_size;
    }
}
