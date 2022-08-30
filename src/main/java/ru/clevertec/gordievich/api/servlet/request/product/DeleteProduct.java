package ru.clevertec.gordievich.api.servlet.request.product;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.clevertec.gordievich.api.servlet.handling.RequestType;
import ru.clevertec.gordievich.api.servlet.handling.ServiceConsumer;
import ru.clevertec.gordievich.domain.products.ProductRepository;
import ru.clevertec.gordievich.infrastructure.exceptions.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.apache.http.HttpStatus.SC_BAD_REQUEST;
import static org.apache.http.HttpStatus.SC_NO_CONTENT;
import static ru.clevertec.gordievich.api.servlet.handling.RequestType.PRODUCT_DELETE;

@Component
@RequiredArgsConstructor
public class DeleteProduct implements ServiceConsumer {

    private final ProductRepository productRepository;

    @Override
    public void accept(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        try {
            Integer id = Integer.parseInt(request.getParameter("id"));
            productRepository.deleteById(id);
            response.setStatus(SC_NO_CONTENT);
        } catch (Exception e) {
            response.setStatus(SC_BAD_REQUEST);
            throw new ServiceException(e);
        }
    }

    @Override
    public RequestType requestType() {
        return PRODUCT_DELETE;
    }
}

