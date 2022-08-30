package ru.clevertec.gordievich.domain.products;


import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private double price;

    @Column(name = "available_number")
    private int availableNumber;

    @Column(name = "is_special_offer")
    private boolean isSpecialOffer;

}
