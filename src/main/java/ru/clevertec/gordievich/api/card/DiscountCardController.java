package ru.clevertec.gordievich.api.card;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.clevertec.gordievich.domain.cards.DiscountCardService;

import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/card")
@RequiredArgsConstructor
public class DiscountCardController {
    private final DiscountCardService discountCardService;

    @PostMapping("/create")
    public ResponseEntity<DiscountCardReadDto> create(@RequestBody @NotNull DiscountCardRequestDto discountCardRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(discountCardService.create(discountCardRequestDto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable @NotNull Integer id) {
        return discountCardService.delete(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.badRequest().build();
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<DiscountCardReadDto> findById(@PathVariable @NotNull Integer id) {
        return discountCardService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(ResponseEntity.status(HttpStatus.BAD_REQUEST)::build);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Void> updateById(@PathVariable @NotNull Integer id, @RequestBody @NotNull DiscountCardRequestDto discountCardRequestDto) {
        return discountCardService.updateById(id, discountCardRequestDto)
                ? ResponseEntity.ok().build()
                : ResponseEntity.badRequest().build();
    }

}
