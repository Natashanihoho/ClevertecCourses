package ru.clevertec.gordievich.domain.receipt;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "receipt")
public record ReceiptProperty(
        String name,
        String phone,
        String address,
        String code
) {
}
