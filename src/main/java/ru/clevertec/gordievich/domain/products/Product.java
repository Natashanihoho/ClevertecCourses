package ru.clevertec.gordievich.domain.products;


import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private double price;
    @Column(name = "available_number")
    private int availableNumber;
    @Column(name = "is_special_offer")
    private boolean isSpecialOffer;

}
