package ru.clevertec.gordievich.api.servlet.request.card;

import static javax.servlet.http.HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
import static org.apache.http.HttpStatus.SC_BAD_REQUEST;
import static org.apache.http.HttpStatus.SC_NO_CONTENT;
import static ru.clevertec.gordievich.api.servlet.handling.RequestType.DISCOUNT_CARD_DELETE;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import lombok.RequiredArgsConstructor;
import ru.clevertec.gordievich.api.servlet.handling.RequestType;
import ru.clevertec.gordievich.api.servlet.handling.ServiceConsumer;
import ru.clevertec.gordievich.domain.cards.DiscountCardRepository;
import ru.clevertec.gordievich.infrastructure.exceptions.ServiceException;

@Component
@RequiredArgsConstructor
public class DeleteDiscountCard implements ServiceConsumer {

    private final DiscountCardRepository discountCardRepository;

    @Override
    public void accept(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        try {
            Integer id = Integer.parseInt(request.getParameter("id"));
            discountCardRepository.deleteById(id);
            response.setStatus(SC_NO_CONTENT);
        } catch (Exception e) {
            response.setStatus(SC_INTERNAL_SERVER_ERROR);
            throw new ServiceException(e);
        }
    }

    @Override
    public RequestType requestType() {
        return DISCOUNT_CARD_DELETE;
    }
}

