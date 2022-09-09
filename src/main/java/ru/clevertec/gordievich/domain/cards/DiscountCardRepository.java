package ru.clevertec.gordievich.domain.cards;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiscountCardRepository extends CrudRepository<DiscountCard, Integer> {

    Optional<DiscountCard> findFirstByCardName(String cardName);

}

