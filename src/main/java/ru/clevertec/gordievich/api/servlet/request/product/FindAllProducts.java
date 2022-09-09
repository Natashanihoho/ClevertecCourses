package ru.clevertec.gordievich.api.servlet.request.product;

import static javax.servlet.http.HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
import static javax.servlet.http.HttpServletResponse.SC_OK;
import static ru.clevertec.gordievich.api.servlet.handling.RequestType.PRODUCT_GET_ALL;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import ru.clevertec.gordievich.api.servlet.handling.RequestType;
import ru.clevertec.gordievich.api.servlet.handling.ServiceConsumer;
import ru.clevertec.gordievich.domain.products.Product;
import ru.clevertec.gordievich.domain.products.ProductRepository;
import ru.clevertec.gordievich.infrastructure.exceptions.ServiceException;

@Component
@RequiredArgsConstructor
public class FindAllProducts implements ServiceConsumer {

    private final ProductRepository productRepository;

    @Override
    public void accept(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        try (PrintWriter writer = response.getWriter()) {
            Integer pageNumber = Optional.ofNullable(request.getParameter("page"))
                    .filter(StringUtils::isNumeric)
                    .map(Integer::valueOf)
                    .filter(page -> page > 0)
                    .orElse(1);
            final List<Product> products = productRepository.findAll(PageRequest.of(pageNumber - 1, 10))
                    .get()
                    .toList();
            writer.write(new Gson().toJson(products));
            response.setStatus(SC_OK);
        } catch (IOException e) {
            response.setStatus(SC_INTERNAL_SERVER_ERROR);
            throw new ServiceException(e);
        }
    }

    @Override
    public RequestType requestType() {
        return PRODUCT_GET_ALL;
    }
}

