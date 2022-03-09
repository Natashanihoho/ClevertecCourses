package ru.clevertec.gordievich.shop;

import java.util.Arrays;

public enum DiscountCard {

    CARD_NOT_DEFINED(0),
    CARD1(1),
    CARD2(2),
    CARD3(3),
    CARD4(4),
    CARD5(5),
    CARD6(6),
    CARD7(7),
    CARD8(8),
    CARD9(9);

    private final int discount;

    DiscountCard(int discount) {
        this.discount = discount;
    }

    public static DiscountCard getDiscountByCard(String name) {
        return Arrays.stream(DiscountCard.values())
                .filter(x -> x.name().equals(name.toUpperCase()))
                .findFirst()
                .orElse(CARD_NOT_DEFINED);
    }

    public int getDiscount() {
        return discount;
    }

}
