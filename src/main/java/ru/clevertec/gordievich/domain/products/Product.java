package ru.clevertec.gordievich.domain.products;

import javax.persistence.*;

import lombok.*;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
