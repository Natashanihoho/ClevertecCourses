package ru.clevertec.gordievich.api.servlet.request.receipt;

import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Tab;
import com.itextpdf.layout.element.Table;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.itextpdf.layout.border.Border.NO_BORDER;
import static com.itextpdf.layout.property.TextAlignment.*;

public class ReceiptPdfFormatter {

    private static final String EMPTY = "";
    private static final String SEPARATOR = "-----------------------------------------------------------------";
    private static final float TABLE_WIDTH = 270F;
    private static final float[] TABLE_FIELD_WIDTH = {22F, 93F, 46F, 63F, 32F};
    private static final float[] TABLE_TOTAL_WIDTH = {127F, 127F};

    public List<Table> formatReceipt(String receipt) {
        List<Table> tables = new ArrayList<>();
        String[] strings = receipt.split("\n");

        tables.add(formatTitle(Arrays.stream(strings).limit(9).collect(Collectors.joining("\n"))));
        tables.add(addSeparators());
        tables.add(formatHeader(Arrays.stream(strings).skip(10).findFirst().get()));
        tables.add(addSeparators());
        tables.add(formatFields(Arrays.stream(strings).skip(12).takeWhile(n -> !n.contains("---")).collect(Collectors.toList())));
        tables.add(addSeparators());
        tables.add(addSeparators());
        tables.add(formatTotalDiscount(Arrays.stream(strings).filter(n -> n.contains("Discount")).findFirst().get()));
        tables.add(addSeparators());
        tables.add(formatTotalPayment(Arrays.stream(strings).filter(n -> n.contains("TO PAY")).findFirst().get()));
        tables.add(formatTail(Arrays.stream(strings).filter(n -> n.contains("THANK YOU")).findFirst().get()));
        return tables;
    }

    private Table formatTitle(String title){
        Table table = new Table(new float[]{TABLE_WIDTH});
        table.addCell(setTextCenter(title));

        return table;
    }

    private Table addSeparators(){
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
        fields.stream().forEach(field -> {
            String[] values = field.split("\s+");
            if(field.contains("discount")) {
                table.addCell(setTextLeft(EMPTY));
                table.addCell(setTextLeft(EMPTY));
                table.addCell(setTextLeft(EMPTY));
                System.out.println(values[1]);
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
