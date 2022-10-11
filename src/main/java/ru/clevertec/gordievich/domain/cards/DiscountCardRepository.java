package ru.clevertec.gordievich.domain.cards;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiscountCardRepository extends JpaRepository<DiscountCard, Integer> {

    Optional<DiscountCard> findFirstByCardName(String cardName);

}

