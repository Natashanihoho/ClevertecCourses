package ru.clevertec.gordievich.api.card;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public record DiscountCardRequestDto(
        @NotNull
        String cardName,
        @NotNull
        @Min(value = 1)
        @Max(value = 100)
        int discountPercent
) {
}
