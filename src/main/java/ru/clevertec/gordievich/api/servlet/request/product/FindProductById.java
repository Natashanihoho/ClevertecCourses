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
import java.io.PrintWriter;

import static org.apache.http.HttpStatus.SC_BAD_REQUEST;
import static org.apache.http.HttpStatus.SC_OK;
import static ru.clevertec.gordievich.api.servlet.handling.RequestType.PRODUCT_GET;

@Component
@RequiredArgsConstructor
public class FindProductById implements ServiceConsumer {

    private final ProductRepository productRepository;

    @Override
    public void accept(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        try (PrintWriter writer = response.getWriter()) {
            Integer id = Integer.parseInt(request.getParameter("id"));
            Product product = productRepository.findById(id).orElseThrow();
            writer.write(new Gson().toJson(product));
            response.setStatus(SC_OK);
        } catch (Exception e) {
            response.setStatus(SC_BAD_REQUEST);
            throw new ServiceException(e);
        }
    }

    @Override
    public RequestType requestType() {
        return PRODUCT_GET;
    }
}

