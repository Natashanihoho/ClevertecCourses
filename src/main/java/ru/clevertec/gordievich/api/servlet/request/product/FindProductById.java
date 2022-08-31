package ru.clevertec.gordievich.api.servlet.request.product;

import static javax.servlet.http.HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
import static org.apache.http.HttpStatus.SC_BAD_REQUEST;
import static org.apache.http.HttpStatus.SC_OK;
import static ru.clevertec.gordievich.api.servlet.handling.RequestType.PRODUCT_GET;

import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import ru.clevertec.gordievich.api.servlet.handling.RequestType;
import ru.clevertec.gordievich.api.servlet.handling.ServiceConsumer;
import ru.clevertec.gordievich.domain.products.ProductRepository;
import ru.clevertec.gordievich.infrastructure.exceptions.ServiceException;

@Component
@RequiredArgsConstructor
public class FindProductById implements ServiceConsumer {

    private final ProductRepository productRepository;

    @Override
    public void accept(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        try (PrintWriter writer = response.getWriter()) {
            Integer id = Integer.parseInt(request.getParameter("id"));
            productRepository.findById(id)
                    .ifPresentOrElse(
                            product -> {
                                writer.write(new Gson().toJson(product));
                                response.setStatus(SC_OK);
                            },
                            () -> response.setStatus(SC_BAD_REQUEST)
                    );
        } catch (Exception e) {
            response.setStatus(SC_INTERNAL_SERVER_ERROR);
            throw new ServiceException(e);
        }
    }

    @Override
    public RequestType requestType() {
        return PRODUCT_GET;
    }
}

