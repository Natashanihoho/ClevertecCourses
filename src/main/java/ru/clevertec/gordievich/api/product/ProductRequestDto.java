package ru.clevertec.gordievich.api.product;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
public record ProductRequestDto (
        @NotNull
        String description,
        @NotNull
        @Min(value = 0)
        double price,
        @Min(value = 1)
        int availableNumber,
        boolean isSpecialOffer
) {
}