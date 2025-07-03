package com.aabuilders.Dashboard.Model;

import com.aabuilders.Dashboard.Entity.DailyChecklistEntry;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import java.io.ByteArrayOutputStream;
import java.text.NumberFormat;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.stream.Stream;

public class PdfGenerator {

    private static final DateTimeFormatter TIMESTAMP_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final NumberFormat CURRENCY_FORMATTER = NumberFormat.getNumberInstance(new Locale("en", "IN"));

    // Custom footer class for page numbers
    static class PdfFooter extends PdfPageEventHelper {
        Font footerFont = FontFactory.getFont(FontFactory.HELVETICA, 9, Font.NORMAL, BaseColor.GRAY);

        @Override
        public void onEndPage(PdfWriter writer, Document document) {
            PdfContentByte cb = writer.getDirectContent();
            Phrase footer = new Phrase("Page " + writer.getPageNumber(), footerFont);

            ColumnText.showTextAligned(cb,
                    Element.ALIGN_CENTER,
                    footer,
                    (document.right() + document.left()) / 2,
                    document.bottom() - 10, // Position below the page
                    0);
        }
    }

    // Static method for generating PDF
    public static byte[] generateChecklistPdf(List<DailyChecklistEntry> entries, int checklistNumber) throws Exception {
        Document document = new Document(PageSize.A4.rotate());
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        PdfWriter writer = PdfWriter.getInstance(document, out);
        writer.setPageEvent(new PdfFooter()); // ✅ Add footer to all pages

        document.open();

        Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 11);
        Font labelFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12);
        Font normalFont = FontFactory.getFont(FontFactory.HELVETICA, 10);

        // ===== HEADER TABLE =====
        PdfPTable header = new PdfPTable(7);
        header.setWidthPercentage(100);
        header.setWidths(new float[]{3f, 2f, 4.4f, 3f, 4f, 3.5f, 1f});

        addHeaderCell(header, "ENTRY CHECK LIST", normalFont);
        addHeaderCell(header, String.valueOf(checklistNumber), normalFont);

        int totalAmount = entries.stream().mapToInt(DailyChecklistEntry::getAmount).sum();
        addHeaderCell(header, CURRENCY_FORMATTER.format(totalAmount), labelFont);

        String currentMonth = java.time.LocalDate.now().format(DateTimeFormatter.ofPattern("MMMM"));
        addHeaderCell(header, currentMonth.toUpperCase(), normalFont);

        addHeaderCell(header, "DATE OF ENTRY :", normalFont);

        String entryDate = entries.isEmpty() ? "" : DATE_FORMATTER.format(entries.get(0).getTimestamp().toLocalDate());

        addHeaderCell(header, entryDate, labelFont);

        addHeaderCell(header, String.valueOf(entries.size()), labelFont);

        document.add(header);

        // ===== NOTE =====
        Paragraph note = new Paragraph(
                "Note: Entry Number (E.No) is a unique number generated for each entry. " +
                        "Correction-required entries should be highlighted.",
                FontFactory.getFont(FontFactory.HELVETICA, 9));
        note.setSpacingBefore(5);
        note.setSpacingAfter(10);
        document.add(note);

        // ===== DATA TABLE =====
        PdfPTable table = new PdfPTable(9);
        table.setWidthPercentage(100);
        table.setWidths(new float[]{3f, 2f, 4.4f, 3f, 3f, 1f, 1.5f, 2f, 1f});

        BaseColor borderColor = new BaseColor(150, 150, 150);

        Stream.of("Timestamp", "Date", "Site Name", "Vendor", "Contractor", "Qty", "Amount", "Category", "E.No")
                .forEach(headerTitle -> {
                    PdfPCell hcell = new PdfPCell(new Phrase(headerTitle, headFont));
                    hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    hcell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    hcell.setBorderColor(borderColor);
                    hcell.setBorderWidth(0.5f);
                    hcell.setPadding(5);
                    table.addCell(hcell);
                });

        table.setHeaderRows(1); // ✅ Repeats the first row on each page

        BaseColor lightGray = new BaseColor(200, 200, 200);

        //15/05/2025 added for alphabetical order
        entries.sort((e1, e2) -> {
            if (e1.getSiteName() == null) return -1;
            if (e2.getSiteName() == null) return 1;
            return e1.getSiteName().compareToIgnoreCase(e2.getSiteName());
        });
        for (DailyChecklistEntry e : entries) {
            table.addCell(createCell(e.getTimestamp() != null ? e.getTimestamp().format(TIMESTAMP_FORMATTER) : "", normalFont, Element.ALIGN_LEFT, lightGray));
            table.addCell(createCell(e.getDate() != null ? e.getDate().format(DATE_FORMATTER) : "", normalFont, Element.ALIGN_LEFT, lightGray));
            table.addCell(createCell(e.getSiteName(), normalFont, Element.ALIGN_LEFT, lightGray));
            table.addCell(createCell(e.getVendor(), normalFont, Element.ALIGN_LEFT, lightGray));
            table.addCell(createCell(e.getContractor(), normalFont, Element.ALIGN_LEFT, lightGray));
            table.addCell(createCell(e.getQuantity(), normalFont, Element.ALIGN_CENTER, lightGray));
            table.addCell(createCell(CURRENCY_FORMATTER.format(e.getAmount()), normalFont, Element.ALIGN_RIGHT, lightGray));
            table.addCell(createCell(e.getCategory(), normalFont, Element.ALIGN_LEFT, lightGray));
            table.addCell(createCell(String.valueOf(e.getEno()), normalFont, Element.ALIGN_LEFT, lightGray));
        }

        document.add(table);
        document.close();
        return out.toByteArray();
    }

    private static PdfPCell createCell(String text, Font font, int hAlign, BaseColor borderColor) {
        PdfPCell cell = new PdfPCell(new Phrase(text, font));
        cell.setHorizontalAlignment(hAlign);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setBorderColor(borderColor);
        cell.setBorderWidth(0.5f);
        return cell;
    }

    private static void addHeaderCell(PdfPTable table, String text, Font font) {
        PdfPCell cell = new PdfPCell(new Phrase(text, font));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setPadding(5);
        table.addCell(cell);
    }
}
