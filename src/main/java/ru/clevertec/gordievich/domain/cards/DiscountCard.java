package ru.clevertec.gordievich.domain.cards;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "discount_card")
public class DiscountCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "card_name")
    private String cardName;

    @Column(name = "discount_percent")
    private int discountPercent;

}
