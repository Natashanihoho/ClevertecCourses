package ru.clevertec.gordievich.domain.cards;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface DiscountCardRepository extends JpaRepository<DiscountCard, Integer> {

    @Transactional
    @Modifying
    @Query("""
            select d 
            from DiscountCard d
            where d.cardName = :cardName
    """)
    Optional<DiscountCard> findByName(@Param(value = "cardName") String cardName);

    @Transactional
    @Modifying
    @Query("""
            select d 
            from DiscountCard d
            where d.id= :id
    """)
    Optional<DiscountCard> byId(@Param(value = "id") Integer id);
}

