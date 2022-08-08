package ru.clevertec.gordievich.domain.cards;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Builder
public class DiscountCard {

    @Setter
    private Long id;
    private String cardName;
    private int discountPercent;
}
