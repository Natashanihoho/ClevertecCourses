package ru.clevertec.gordievich.api.servlet.request.card;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.clevertec.gordievich.api.servlet.handling.RequestType;
import ru.clevertec.gordievich.api.servlet.handling.ServiceConsumer;
import ru.clevertec.gordievich.domain.cards.DiscountCard;
import ru.clevertec.gordievich.domain.cards.DiscountCardDao;
import ru.clevertec.gordievich.infrastructure.exceptions.DaoException;
import ru.clevertec.gordievich.infrastructure.exceptions.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

import static org.apache.http.HttpStatus.SC_BAD_REQUEST;
import static org.apache.http.HttpStatus.SC_OK;
import static ru.clevertec.gordievich.api.servlet.handling.RequestType.DISCOUNT_CARD_UPDATE;

@Component
@RequiredArgsConstructor
public class UpdateDiscountCard implements ServiceConsumer {

    private final DiscountCardDao discountCardDao;

    @Override
    public void accept(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        try (BufferedReader bufferedReader = request.getReader()) {
            DiscountCard discountCard = new Gson().fromJson(bufferedReader, DiscountCard.class);
            response.setStatus(discountCardDao.update(discountCard) ? SC_OK : SC_BAD_REQUEST);
        } catch (IOException | DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public RequestType requestType() {
        return DISCOUNT_CARD_UPDATE;
    }
}

