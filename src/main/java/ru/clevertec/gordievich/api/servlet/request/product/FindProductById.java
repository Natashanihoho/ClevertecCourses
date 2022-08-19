package ru.clevertec.gordievich.api.servlet.request.product;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.clevertec.gordievich.api.servlet.handling.RequestType;
import ru.clevertec.gordievich.api.servlet.handling.ServiceConsumer;
import ru.clevertec.gordievich.domain.products.Product;
import ru.clevertec.gordievich.domain.products.ProductDao;
import ru.clevertec.gordievich.infrastructure.exceptions.DaoException;
import ru.clevertec.gordievich.infrastructure.exceptions.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import static ru.clevertec.gordievich.api.servlet.handling.RequestType.PRODUCT_GET;

@Component
@RequiredArgsConstructor
public class FindProductById implements ServiceConsumer {

    private final ProductDao productDao;

    @Override
    public void accept(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        try (PrintWriter writer = response.getWriter()) {
            String id = request.getParameter("id");
            Product product = productDao.findById(Integer.parseInt(id)).orElseThrow();
            writer.write(new Gson().toJson(product));
        } catch (IOException | DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public RequestType requestType() {
        return PRODUCT_GET;
    }
}

