package ru.clevertec.gordievich.domain.cards;

import ru.clevertec.gordievich.domain.products.Product;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.function.Function;

public class DiscountCardMapper implements Function<ResultSet, DiscountCard> {
    @Override
    public DiscountCard apply(ResultSet resultSet) {
        try {
            return DiscountCard.builder()
                    .name(resultSet.getString("name"))
                    .discountPercent(resultSet.getInt("discount_percent"))
                    .build();
        } catch (SQLException e) {
            throw new IllegalArgumentException("Impossible to map resultSet to DiscountCard", e);
        }
    }
}
