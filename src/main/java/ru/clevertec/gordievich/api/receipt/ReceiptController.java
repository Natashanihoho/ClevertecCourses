package ru.clevertec.gordievich.api.receipt;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.clevertec.gordievich.domain.receipt.Receipt;
@RestController
@RequestMapping("/receipt")
@RequiredArgsConstructor
public class ReceiptController {

    private final Receipt receipt;

    @GetMapping(value = "/value={value}")
    public ResponseEntity<InputStreamResource> get(@PathVariable String value) {
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"receipt.pdf\"")
                .body(
                        new InputStreamResource(
                                receipt.createPdf(
                                        value.contains("&")
                                                ? value.split("&")
                                                : new String[]{value})
                        )
                );
    }
}
