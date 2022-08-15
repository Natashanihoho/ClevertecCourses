package ru.clevertec.gordievich.api.servlet.request.receipt;

import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.clevertec.gordievich.api.servlet.handling.ServiceConsumer;
import ru.clevertec.gordievich.domain.receipt.ReceiptService;
import ru.clevertec.gordievich.domain.receipt.ReceiptServiceImpl;
import ru.clevertec.gordievich.infrastructure.exceptions.DaoException;
import ru.clevertec.gordievich.infrastructure.exceptions.NotEnoughProductsException;
import ru.clevertec.gordievich.infrastructure.exceptions.ServiceException;
import ru.clevertec.gordievich.infrastructure.exceptions.UnknownIdException;

import java.io.*;

public class ReceiptPdf implements ServiceConsumer {

    private final ReceiptService receiptService = ReceiptServiceImpl.getInstance();
    private final ReceiptPdfFormatter formatter = new ReceiptPdfFormatter();

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
            formatter.formatReceipt(receipt).stream().forEach(table -> document.add(table));
            document.close();
        } catch (IOException | UnknownIdException | NotEnoughProductsException | DaoException e) {
            throw new ServiceException(e);
        }
    }
}
