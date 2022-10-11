package ru.clevertec.gordievich.api.card;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class DiscountCardReadDto {

    private Integer id;

    private String cardName;

    private int discountPercent;
}
