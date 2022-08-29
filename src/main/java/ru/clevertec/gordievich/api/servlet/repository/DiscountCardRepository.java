package ru.clevertec.gordievich.api.servlet.repository;

import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface DiscountCardRepository<DiscountCard, Integer> extends Repository<DiscountCard, Integer> {

    DiscountCard createDiscountCard(DiscountCard discountCard);

    Optional<DiscountCard> findByName(String name);

    boolean update(DiscountCard discountCard);

    boolean deleteByName(String name);
}
