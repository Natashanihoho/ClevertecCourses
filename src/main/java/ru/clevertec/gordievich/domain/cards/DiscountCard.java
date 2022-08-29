package ru.clevertec.gordievich.domain.cards;


import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "discount_card")
public class DiscountCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "card_name")
    private String cardName;
    @Column(name = "discount_percent")
    private int discountPercent;
}
