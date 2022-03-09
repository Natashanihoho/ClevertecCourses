package ru.clevertec.gordievich.shop;

import ru.clevertec.gordievich.entity.Product;
import ru.clevertec.gordievich.exceptions.NotEnoughProductsException;
import ru.clevertec.gordievich.exceptions.UnknownIdException;

import java.util.List;

public class Store {

    private static Store instance;
    private static List<Product> stockProducts;
    private final List<String> cashiers;

    private Store() {
        cashiers = List.of("Liam Neeson", "Meryl Streep", "Kate Winslet", "Will Smith");
    }

    public void loadProducts(List<Product> stockProducts) {
        this.stockProducts = stockProducts;
    }

    public List<String> getCashiers() {
        return cashiers;
    }

    public static Store getInstance() {
        if (instance == null) {
            instance = new Store();
        }
        return instance;
    }

    public Product getProduct(int id, int requiredNumber) throws UnknownIdException, NotEnoughProductsException {
        Product product = stockProducts.stream()
                .filter(x -> x.getId() == id)
                .findFirst()
                .orElseThrow(() -> new UnknownIdException("Product is not found"));

        if (product.getAvailableNumber() < requiredNumber) throw new NotEnoughProductsException("There are not enough products");
        product.setAvailableNumber(product.getAvailableNumber() - requiredNumber);
        return product;
    }
}
