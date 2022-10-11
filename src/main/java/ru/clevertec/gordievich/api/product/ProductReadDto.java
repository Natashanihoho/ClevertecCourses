package ru.clevertec.gordievich.api.product;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ProductReadDto {

    private Integer id;

    private String description;

    private double price;

    private int availableNumber;

    private boolean isSpecialOffer;
}
