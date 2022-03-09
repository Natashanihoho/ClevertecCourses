package ru.clevertec.gordievich.entity;

public class Product {

    private final int id;
    private final String description;
    private double price;
    private int availableNumber;
    private boolean isSpecialOffer;


    public Product(int id, String description, double price, int availableNumber, boolean isSpecialOffer) {
        this.id = id;
        this.description = description;
        this.price = price;
        this.availableNumber = availableNumber;
        this.isSpecialOffer = isSpecialOffer;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public boolean isSpecialOffer() {
        return isSpecialOffer;
    }

    public int getAvailableNumber() {
        return availableNumber;
    }

    public void setAvailableNumber(int availableNumber) {
        this.availableNumber = availableNumber;
    }

}
