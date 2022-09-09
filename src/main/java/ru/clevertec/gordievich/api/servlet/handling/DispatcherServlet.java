package ru.clevertec.gordievich.api.servlet.handling;

import static javax.servlet.http.HttpServletResponse.SC_BAD_REQUEST;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import ru.clevertec.gordievich.infrastructure.exceptions.ExceptionInfo;
import ru.clevertec.gordievich.infrastructure.exceptions.ServiceException;

@Component
@RequiredArgsConstructor
public class DispatcherServlet implements BiConsumer<HttpServletRequest, HttpServletResponse> {

    private final List<ServiceConsumer> serviceConsumers;
    private final Map<RequestType, ServiceConsumer> providers = new HashMap<>();

    @PostConstruct
    public void init() {
        serviceConsumers.forEach(serviceConsumer -> providers.put(serviceConsumer.requestType(), serviceConsumer));
    }

    @Override
    public void accept(HttpServletRequest request, HttpServletResponse response) {
        try {
            RequestType requestType = RequestType.byEndpointAndMethod(request.getParameter("type"), request.getMethod());
            ServiceConsumer serviceConsumer = providers.get(requestType);
            serviceConsumer.accept(request, response);
        } catch (ServiceException e) {
            response.setStatus(SC_BAD_REQUEST);
            try (PrintWriter printWriter = response.getWriter()) {
                ExceptionInfo exceptionInfo = ExceptionInfo.builder()
                        .message(e.getMessage())
                        .stackTrace(
                                Arrays.stream(e.getStackTrace())
                                        .map(StackTraceElement::toString)
                                        .toArray(String[]::new)
                        ).build();
                printWriter.write(new Gson().toJson(exceptionInfo));
            } catch (IOException ioException) {
                response.setStatus(SC_BAD_REQUEST);
            }
        }
    }
}
