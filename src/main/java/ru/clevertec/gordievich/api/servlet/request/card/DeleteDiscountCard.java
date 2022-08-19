package ru.clevertec.gordievich.api.servlet.request.card;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.clevertec.gordievich.api.servlet.handling.RequestType;
import ru.clevertec.gordievich.api.servlet.handling.ServiceConsumer;
import ru.clevertec.gordievich.domain.cards.DiscountCardDao;
import ru.clevertec.gordievich.infrastructure.exceptions.DaoException;
import ru.clevertec.gordievich.infrastructure.exceptions.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.apache.http.HttpStatus.SC_BAD_REQUEST;
import static org.apache.http.HttpStatus.SC_NO_CONTENT;
import static ru.clevertec.gordievich.api.servlet.handling.RequestType.DISCOUNT_CARD_DELETE;

@Component
@RequiredArgsConstructor
public class DeleteDiscountCard implements ServiceConsumer {

    private final DiscountCardDao discountCardDao;

    @Override
    public void accept(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        try {
            String id = request.getParameter("name");
            boolean deleteResult = discountCardDao.deleteByName(id);
            response.setStatus(deleteResult ? SC_NO_CONTENT : SC_BAD_REQUEST);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public RequestType requestType() {
        return DISCOUNT_CARD_DELETE;
    }
}

