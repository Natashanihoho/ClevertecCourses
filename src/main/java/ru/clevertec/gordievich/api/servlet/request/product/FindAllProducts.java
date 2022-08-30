package ru.clevertec.gordievich.api.servlet.request.product;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.clevertec.gordievich.api.servlet.handling.RequestType;
import ru.clevertec.gordievich.api.servlet.handling.ServiceConsumer;
import ru.clevertec.gordievich.domain.products.ProductRepository;
import ru.clevertec.gordievich.infrastructure.exceptions.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static ru.clevertec.gordievich.api.servlet.handling.RequestType.PRODUCT_GET_ALL;

@Component
@RequiredArgsConstructor
public class FindAllProducts implements ServiceConsumer {

    private final ProductRepository productRepository;

    @Override
    public void accept(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
//        try (PrintWriter writer = response.getWriter()) {
//            Integer pageNumber = Optional.ofNullable(request.getParameter("page"))
//                    .map(Integer::valueOf)
//                    .orElse(1);
//            String json = new Gson().toJson(productDao.findAllByPage(pageNumber));
//            writer.write(json);
//        } catch (IOException | DaoException e) {
//            throw new ServiceException(e);
//        }
    }

    @Override
    public RequestType requestType() {
        return PRODUCT_GET_ALL;
    }
}

