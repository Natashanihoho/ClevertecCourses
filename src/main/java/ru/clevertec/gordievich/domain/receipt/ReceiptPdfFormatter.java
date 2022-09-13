package ru.clevertec.gordievich.domain.receipt;

import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Table;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import static com.itextpdf.layout.border.Border.NO_BORDER;
import static com.itextpdf.layout.property.TextAlignment.*;

@Component
public class ReceiptPdfFormatter {

    private static final String EMPTY = "";
    private static final String SEPARATOR = "-----------------------------------------------------------------";
    private static final float TABLE_WIDTH = 270F;
    private static final float[] TABLE_FIELD_WIDTH = {22F, 93F, 46F, 63F, 32F};
    private static final float[] TABLE_TOTAL_WIDTH = {127F, 127F};

    public List<Table> formatReceipt(String receipt) {
        List<Table> tables = new ArrayList<>();
        String[] strings = receipt.split("\n");
        tables.add(
                formatTitle(
                        Arrays.stream(strings)
                                .limit(9)
                                .collect(Collectors.joining("\n"))
                )
        );
        tables.add(addSeparators());
        Arrays.stream(strings)
                .skip(10)
                .findFirst()
                .ifPresent(value -> tables.add(formatHeader(value)));
        tables.add(addSeparators());
        tables.add(
                formatFields(
                        Arrays.stream(strings)
                                .skip(12)
                                .takeWhile(n -> !n.contains("---"))
                                .collect(Collectors.toList())
                )
        );
        tables.add(addSeparators());
        tables.add(addSeparators());
        receiptTailFormatter(strings,"Discount",value -> tables.add(formatTotalDiscount(value)));
        tables.add(addSeparators());
        receiptTailFormatter(strings,"TO PAY",value -> tables.add(formatTotalPayment(value)));
        receiptTailFormatter(strings,"THANK YOU",value -> tables.add(formatTail(value)));
        return tables;
    }

    private void receiptTailFormatter(String[] rows, String description, Consumer<String> consume) {
        Arrays.stream(rows)
                .filter(row -> row.contains(description))
                .findFirst()
                .ifPresent(consume);
    }

    private Table formatTitle(String title) {
        Table table = new Table(new float[]{TABLE_WIDTH});
        table.addCell(setTextCenter(title));
        return table;
    }

    private Table addSeparators() {
        Table table = new Table(new float[]{TABLE_WIDTH});
        table.addCell(setTextLeft(SEPARATOR));
        return table;
    }

    private Table formatHeader(String header) {
        Table table = new Table(new float[]{TABLE_WIDTH});
        table.addCell(setTextLeft(header));
        return table;
    }

    private Table formatFields(List<String> fields) {
        Table table = new Table(TABLE_FIELD_WIDTH);
        fields.forEach(field -> {
            String[] values = field.split("\s+");
            if (field.contains("discount")) {
                table.addCell(setTextLeft(EMPTY));
                table.addCell(setTextLeft(EMPTY));
                table.addCell(setTextLeft(EMPTY));
                table.addCell(setTextRight(values[1]));
                table.addCell(setTextLeft(values[2]));
            } else {
                Arrays.stream(values).forEach(v -> table.addCell(setTextLeft(v)));
            }
        });
        return table;
    }

    private Table formatTotalDiscount(String field) {
        Table table = new Table(TABLE_TOTAL_WIDTH);
        String[] values = field.split("\s+");
        table.addCell(setTextLeft(values[0]));
        table.addCell(setTextRight(values[1]));
        return table;
    }

    private Table formatTotalPayment(String field) {
        Table table = new Table(TABLE_TOTAL_WIDTH);
        String[] values = field.split("\s+");
        table.addCell(setTextLeft(values[0] + " " + values[1]));
        table.addCell(setTextRight(values[2]));
        return table;
    }

    private Table formatTail(String tail) {
        Table table = new Table(new float[]{TABLE_WIDTH});
        table.addCell(setTextCenter(tail));
        return table;
    }

    private Cell setTextCenter(String text) {
        return new Cell().add(text).setBorder(NO_BORDER)
                .setTextAlignment(CENTER);
    }

    private Cell setTextRight(String text) {
        return new Cell().add(text).setBorder(NO_BORDER)
                .setTextAlignment(RIGHT);
    }

    private Cell setTextLeft(String text) {
        return new Cell().add(text).setBorder(NO_BORDER)
                .setTextAlignment(LEFT);
    }

}
