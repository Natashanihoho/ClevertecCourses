package ru.clevertec.gordievich.domain.products;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.clevertec.gordievich.api.product.ProductRequestDto;
import ru.clevertec.gordievich.api.product.ProductReadDto;

import javax.transaction.Transactional;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    public ProductReadDto create(ProductRequestDto productRequestDto) {
        Product product = productRepository.save(mapToProduct(productRequestDto));
        return mapToReadDto(product);
    }

    public Optional<ProductReadDto> findById(Integer id) {
        return productRepository.findById(id)
                .map(this::mapToReadDto);
    }

    public Page<Product> findAll(Integer pageNumber) {
        return Objects.isNull(pageNumber)
                ? productRepository.findAll(PageRequest.of(0, 10))
                : productRepository.findAll(PageRequest.of(pageNumber - 1, 10));
    }

    @Transactional
    public boolean updateById(Integer id, ProductRequestDto productRequestDto) {
        return productRepository.findById(id)
                .map(productToUpdate -> {
                            productToUpdate.setDescription(productRequestDto.description());
                            productToUpdate.setPrice(productRequestDto.price());
                            productToUpdate.setAvailableNumber(productRequestDto.availableNumber());
                            productToUpdate.setSpecialOffer(productRequestDto.isSpecialOffer());
                            return true;
                        }
                ).orElse(false);
    }

    public boolean delete(Integer id) {
        return productRepository.findById(id)
                .map(card -> {
                    productRepository.delete(card);
                    return true;
                }).orElse(false);
    }


    private ProductReadDto mapToReadDto(Product product) {
        return ProductReadDto.builder()
                .id(product.getId())
                .description(product.getDescription())
                .price(product.getPrice())
                .availableNumber(product.getAvailableNumber())
                .isSpecialOffer(product.isSpecialOffer())
                .build();
    }

    public Product mapToProduct(ProductRequestDto productRequestDto) {
        return Product.builder()
                .description(productRequestDto.description())
                .price(productRequestDto.price())
                .availableNumber(productRequestDto.availableNumber())
                .isSpecialOffer(productRequestDto.isSpecialOffer())
                .build();
    }
}
