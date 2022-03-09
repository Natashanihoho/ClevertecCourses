package ru.clevertec.gordievich.service;

import ru.clevertec.gordievich.entity.Product;
import ru.clevertec.gordievich.shop.Store;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public final class StoreFactory {

    private StoreFactory() { }

    public static Store defaultStore() {
        List<Product> stockProducts = Arrays.asList(
                new Product(1, "Lollipop", 0.87, 20, false),
                new Product(2, "Popcorn", 2.10, 20, true),
                new Product(3, "Ice cream", 2.50, 20, false),
                new Product(4, "Yogurt", 1.50, 20, true),
                new Product(5, "Biscuit", 1.48, 20, true),
                new Product(6, "Cake", 18.65, 20, false),
                new Product(7, "Chocolate", 3.10, 20, false),
                new Product(8, "Croissant", 1.20, 20, false),
                new Product(9, "Jam", 3.41, 20, false),
                new Product(10, "Marshmallow", 1.48, 20, false),
                new Product(11, "Waffle", 2.63, 20, true),
                new Product(12, "Brownie", 1.79, 20, false),
                new Product(13, "Bun", 0.62, 20, true),
                new Product(14, "Eclair", 1.90, 20, false),
                new Product(15, "Gum", 0.50, 20, false),
                new Product(16, "Macaroon", 5.52, 20, false),
                new Product(17, "Marmalade", 2.70, 20, false),
                new Product(18, "Lemonade", 1.62, 20, true),
                new Product(19, "Juice", 3.00, 20, false),
                new Product(20, "Coke", 2.50, 20, false));
        Store store = Store.getInstance();
        store.loadProducts(stockProducts);
        return store;
    }

    public static Store fileStore(String path) throws IOException {
        Store store = Store.getInstance();
        List<Product> stockProducts = new ArrayList<>();

        try(Stream<String> lines = Files.lines(Path.of(path))) {
            lines.forEach(line -> {
                String[] params = line.split("-");
                int id = Integer.parseInt(params[0].trim());
                String description = params[1].trim();
                double price = Double.parseDouble(params[2].trim());
                int number = Integer.parseInt(params[3].trim());
                boolean isSpecialOffer = Boolean.parseBoolean(params[4].trim());
                stockProducts.add(new Product(id, description, price, number, isSpecialOffer));
            });
        }

        store.loadProducts(stockProducts);

        return store;
    }

}
