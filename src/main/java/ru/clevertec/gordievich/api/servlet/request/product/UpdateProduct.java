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
import static org.apache.http.HttpStatus.SC_OK;
import static ru.clevertec.gordievich.api.servlet.handling.RequestType.PRODUCT_UPDATE;

@Component
@RequiredArgsConstructor
public class UpdateProduct implements ServiceConsumer {

    private final ProductRepository productRepository;

    @Override
    public void accept(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        try (BufferedReader bufferedReader = request.getReader()) {
            Integer id = Integer.parseInt(request.getParameter("id"));
            Product product = new Gson().fromJson(bufferedReader, Product.class);
            Product productToUpdate = productRepository.findById(id).orElseThrow();
            productToUpdate.setDescription(product.getDescription());
            productToUpdate.setPrice(product.getPrice());
            productToUpdate.setAvailableNumber(product.getAvailableNumber());
            productToUpdate.setSpecialOffer(product.isSpecialOffer());
            productRepository.save(productToUpdate);
            response.setStatus(SC_OK);
        } catch (Exception e) {
            response.setStatus(SC_BAD_REQUEST);
            throw new ServiceException(e);
        }
    }

    @Override
    public RequestType requestType() {
        return PRODUCT_UPDATE;
    }
}
