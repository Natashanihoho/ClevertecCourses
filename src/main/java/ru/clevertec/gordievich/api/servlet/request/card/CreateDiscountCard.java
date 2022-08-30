package ru.clevertec.gordievich.api.servlet.request.card;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.clevertec.gordievich.api.servlet.handling.RequestType;
import ru.clevertec.gordievich.api.servlet.handling.ServiceConsumer;
import ru.clevertec.gordievich.domain.cards.DiscountCard;
import ru.clevertec.gordievich.domain.cards.DiscountCardRepository;
import ru.clevertec.gordievich.infrastructure.exceptions.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;

import static org.apache.http.HttpStatus.SC_BAD_REQUEST;
import static org.apache.http.HttpStatus.SC_CREATED;
import static ru.clevertec.gordievich.api.servlet.handling.RequestType.DISCOUNT_CARD_POST;

@Component
@RequiredArgsConstructor
public class CreateDiscountCard implements ServiceConsumer {

    private final DiscountCardRepository discountCardRepository;

    @Override
    public void accept(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        try (BufferedReader bufferedReader = request.getReader();
             var writer = response.getWriter()) {
            DiscountCard discountCard = new Gson().fromJson(bufferedReader, DiscountCard.class);
            response.setStatus(discountCardRepository.save(discountCard) != null ? SC_CREATED : SC_BAD_REQUEST);
            writer.write(new Gson().toJson(discountCard));
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public RequestType requestType() {
        return DISCOUNT_CARD_POST;
    }
}

