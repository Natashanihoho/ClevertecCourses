package ru.clevertec.gordievich.api.product;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.clevertec.gordievich.domain.products.Product;
import ru.clevertec.gordievich.domain.products.ProductService;

import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping("/create")
    public ResponseEntity<ProductReadDto> create(@RequestBody @NotNull ProductRequestDto productRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.create(productRequestDto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable @NotNull Integer id) {
        return productService.delete(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.badRequest().build();
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<ProductReadDto> findById(@PathVariable Integer id) {
        return productService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(ResponseEntity.status(HttpStatus.BAD_REQUEST)::build);
    }

    @GetMapping(value = {"/find_all/{pageNumber}", "/find_all"})
    public ResponseEntity<Page<Product>> findAll(@PathVariable(required = false) Integer pageNumber) {
        return ResponseEntity.ok(productService.findAll(pageNumber));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Void> updateById(@PathVariable @NotNull Integer id, @RequestBody @NotNull ProductRequestDto productRequestDto) {
        return productService.updateById(id, productRequestDto)
                ? ResponseEntity.ok().build()
                : ResponseEntity.badRequest().build();
    }
}