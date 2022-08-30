package ru.clevertec.gordievich.api.servlet.request.product;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.clevertec.gordievich.api.servlet.handling.RequestType;
import ru.clevertec.gordievich.api.servlet.handling.ServiceConsumer;
import ru.clevertec.gordievich.domain.products.Product;
import ru.clevertec.gordievich.domain.products.ProductRepository;
import ru.clevertec.gordievich.infrastructure.exceptions.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;

import static org.apache.http.HttpStatus.SC_BAD_REQUEST;
import static org.apache.http.HttpStatus.SC_CREATED;
import static ru.clevertec.gordievich.api.servlet.handling.RequestType.PRODUCT_POST;

@Component
@RequiredArgsConstructor
public class CreateProduct implements ServiceConsumer {

    private final ProductRepository productRepository;

    @Override
    public void accept(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        try (BufferedReader bufferedReader = request.getReader();
             var writer = response.getWriter()) {
            Product product = new Gson().fromJson(bufferedReader, Product.class);
            response.setStatus(productRepository.save(product) != null ? SC_CREATED : SC_BAD_REQUEST);
            writer.write(new Gson().toJson(product));
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public RequestType requestType() {
        return PRODUCT_POST;
    }
}
