package ru.clevertec.gordievich.api.servlet.repository;

import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository<Product, Integer> extends Repository<Product, Integer> {

    Product createProduct(Product product);

    Optional<Product> findById(int id);

    List<Product> findAllByPage(int page);

    boolean update(Product product);

    boolean deleteById(Long id);
}
