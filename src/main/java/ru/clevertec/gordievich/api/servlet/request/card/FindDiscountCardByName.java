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
import java.io.IOException;
import java.io.PrintWriter;

import static ru.clevertec.gordievich.api.servlet.handling.RequestType.DISCOUNT_CARD_GET;

@Component
@RequiredArgsConstructor
public class FindDiscountCardByName implements ServiceConsumer {

    private final DiscountCardDao discountCardDao;

    @Override
    public void accept(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        try (PrintWriter writer = response.getWriter()) {
            String id = request.getParameter("name");
            DiscountCard discountCard = discountCardDao.findByName(id).orElseThrow();
            writer.write(new Gson().toJson(discountCard));
        } catch (IOException | DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public RequestType requestType() {
        return DISCOUNT_CARD_GET;
    }
}

