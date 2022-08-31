package ru.clevertec.gordievich.api.servlet.request.product;

import static org.apache.http.HttpStatus.SC_BAD_REQUEST;
import static org.apache.http.HttpStatus.SC_INTERNAL_SERVER_ERROR;
import static org.apache.http.HttpStatus.SC_OK;
import static ru.clevertec.gordievich.api.servlet.handling.RequestType.PRODUCT_UPDATE;

import java.io.BufferedReader;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
public class UpdateProduct implements ServiceConsumer {

    private final ProductRepository productRepository;

    @Override
    public void accept(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        try (BufferedReader bufferedReader = request.getReader()) {
            Integer id = Integer.parseInt(request.getParameter("id"));
            Product product = new Gson().fromJson(bufferedReader, Product.class);
            productRepository.findById(id)
                    .map(productToUpdate -> {
                                productToUpdate.setDescription(product.getDescription());
                                productToUpdate.setPrice(product.getPrice());
                                productToUpdate.setAvailableNumber(product.getAvailableNumber());
                                productToUpdate.setSpecialOffer(product.isSpecialOffer());
                                response.setStatus(SC_OK);
                                return productToUpdate;
                            }
                    ).ifPresentOrElse(
                            productRepository::save,
                            () -> response.setStatus(SC_BAD_REQUEST)
                    );
        } catch (Exception e) {
            response.setStatus(SC_INTERNAL_SERVER_ERROR);
            throw new ServiceException(e);
        }
    }

    @Override
    public RequestType requestType() {
        return PRODUCT_UPDATE;
    }
}
