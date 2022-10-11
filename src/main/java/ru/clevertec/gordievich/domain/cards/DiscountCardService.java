package ru.clevertec.gordievich.domain.cards;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.clevertec.gordievich.api.card.DiscountCardReadDto;
import ru.clevertec.gordievich.api.card.DiscountCardRequestDto;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DiscountCardService {

    private final DiscountCardRepository discountCardRepository;
    public DiscountCardReadDto create(DiscountCardRequestDto discountCardRequestDto) {
        DiscountCard discountCard = discountCardRepository.save(mapToDiscountCard(discountCardRequestDto));
        return mapToReadDto(discountCard);
    }

    public boolean delete(Integer id) {
        return discountCardRepository.findById(id)
                .map(card -> {
                    discountCardRepository.delete(card);
                    return true;
                }).orElse(false);
    }

    public Optional<DiscountCardReadDto> findById(Integer id) {
        return discountCardRepository.findById(id)
                .map(this::mapToReadDto);
    }

    @Transactional
    public boolean updateById(Integer id, DiscountCardRequestDto discountCardRequestDto) {
        return discountCardRepository.findById(id)
                .map(discountCardToUpdate -> {
                            discountCardToUpdate.setCardName(discountCardRequestDto.cardName());
                            discountCardToUpdate.setDiscountPercent(discountCardRequestDto.discountPercent());
                            return true;
                        }
                ).orElse(false);
    }

    private DiscountCardReadDto mapToReadDto(DiscountCard discountCard) {
        return DiscountCardReadDto.builder()
                .id(discountCard.getId())
                .cardName(discountCard.getCardName())
                .discountPercent(discountCard.getDiscountPercent())
                .build();
    }

    public DiscountCard mapToDiscountCard(DiscountCardRequestDto discountCardRequestDto) {
        return DiscountCard.builder()
                .discountPercent(discountCardRequestDto.discountPercent())
                .cardName(discountCardRequestDto.cardName())
                .build();
    }
}
