package ru.clevertec.gordievich.api.servlet.request.receipt;

import static ru.clevertec.gordievich.api.servlet.handling.RequestType.RECEIPT_GET;

import java.io.IOException;
import java.io.OutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import lombok.RequiredArgsConstructor;
import ru.clevertec.gordievich.api.servlet.handling.RequestType;
import ru.clevertec.gordievich.api.servlet.handling.ServiceConsumer;
import ru.clevertec.gordievich.domain.receipt.ReceiptService;
import ru.clevertec.gordievich.infrastructure.exceptions.DaoException;
import ru.clevertec.gordievich.infrastructure.exceptions.NotEnoughProductsException;
import ru.clevertec.gordievich.infrastructure.exceptions.ServiceException;
import ru.clevertec.gordievich.infrastructure.exceptions.UnknownIdException;

@Component
@RequiredArgsConstructor
public class ReceiptPdf implements ServiceConsumer {

    private final ReceiptService receiptService;
    private final ReceiptPdfFormatter formatter;

    @Override
    public void accept(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        try (OutputStream outputStream = response.getOutputStream()) {
            response.setContentType("application/pdf");
            response.setCharacterEncoding("UTF-8");
            response.setHeader("Content-disposition", "inline; filename=\"receipt.pdf\"");
            String[] values = request.getParameterValues("value");
            String receipt = receiptService.interpret(values);
            PdfDocument pdfDocument = new PdfDocument(new PdfWriter(outputStream));
            pdfDocument.setDefaultPageSize(PageSize.A4);
            Document document = new Document(pdfDocument);
            formatter.formatReceipt(receipt).forEach(document::add);
            document.close();
        } catch (IOException | UnknownIdException | NotEnoughProductsException | DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public RequestType requestType() {
        return RECEIPT_GET;
    }
}
