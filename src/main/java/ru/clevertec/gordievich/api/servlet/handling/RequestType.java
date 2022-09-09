package ru.clevertec.gordievich.api.servlet.handling;

import static ru.clevertec.gordievich.api.servlet.handling.HttpMethod.DELETE;
import static ru.clevertec.gordievich.api.servlet.handling.HttpMethod.GET;
import static ru.clevertec.gordievich.api.servlet.handling.HttpMethod.POST;
import static ru.clevertec.gordievich.api.servlet.handling.HttpMethod.PUT;

import java.util.Arrays;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

@RequiredArgsConstructor
@Accessors(fluent = true)
@Getter
public enum RequestType {

    PRODUCT_GET("product_by_id", GET),
    PRODUCT_POST("product_create", POST),
    PRODUCT_DELETE("product_delete", DELETE),
    PRODUCT_GET_ALL("product_all", GET),
    PRODUCT_UPDATE("product_update", PUT),

    DISCOUNT_CARD_GET("discount_card_by_name", GET),
    DISCOUNT_CARD_POST("discount_card_create", POST),
    DISCOUNT_CARD_DELETE("discount_card_delete", DELETE),
    DISCOUNT_CARD_UPDATE("discount_card_update", PUT),

    RECEIPT_GET("receipt", GET);

    private final String endpoint;
    private final HttpMethod httpMethod;

    public static RequestType byEndpointAndMethod(String endpoint, String method) {
        return Arrays.stream(values())
                .filter(command -> command.endpoint.equals(endpoint))
                .filter(command -> command.httpMethod.name().equals(method))
                .findFirst()
                .orElseThrow();
    }

}
