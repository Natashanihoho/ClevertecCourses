package ru.clevertec.gordievich.entity;

public class Position {

    private Product product;
    private int requiredNumber;
    private double total;
    private double discount = 0;

    public Position(Product product, int requiredNumber) {
        this.product = product;
        this.requiredNumber = requiredNumber;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public int getRequiredNumber() {
        return requiredNumber;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Product getProduct() {
        return product;
    }

}



