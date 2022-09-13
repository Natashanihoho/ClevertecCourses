package ru.clevertec.gordievich.domain.receipt;

import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.clevertec.gordievich.infrastructure.exceptions.NotEnoughProductsException;
import ru.clevertec.gordievich.infrastructure.exceptions.ServiceException;
import ru.clevertec.gordievich.infrastructure.exceptions.UnknownIdException;

import java.io.*;

@Component
@RequiredArgsConstructor
public class Receipt {
    private final ReceiptService receiptService;
    private final ReceiptPdfFormatter formatter;

    public InputStream createPdf(String[] values) {
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            PdfDocument pdfDocument = new PdfDocument(new PdfWriter(outputStream));
            pdfDocument.setDefaultPageSize(PageSize.A4);
            Document document = new Document(pdfDocument);
            formatter.formatReceipt(receiptService.interpret(values))
                    .forEach(document::add);
            document.close();
            return new ByteArrayInputStream(outputStream.toByteArray());
        } catch (UnknownIdException | NotEnoughProductsException e) {
            throw new ServiceException(e);
        }
    }

}
