package robert.other;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import robert.responses.InvoiceTemplate;
import robert.responses.simpleentities.SimpleService;

import java.io.FileOutputStream;
import java.util.Calendar;

/**
 * Created by robert on 28.05.16.
 */
public class InvoiceGeneratorImpl implements InvoiceGenerator {
    @Override
    public Document generateInvoice(InvoiceTemplate template, Image image) {
        Document document = new Document();
        String fileName = "Invoice " + Calendar.getInstance().getTime().toString() + ".pdf";
        try {
            PdfWriter.getInstance(document,
                    new FileOutputStream(fileName));
            document.open();
            Paragraph paragraph1 = new Paragraph("Invoice");

            if (image != null) {
                image.scaleAbsolute(150f, 150f);
                document.add(image);
            }

            PdfPTable table = generateTableStructure();
            for (SimpleService s : template.getSelectedServices()) {
                table.addCell(new PdfPCell(new Paragraph(s.getName())));
                table.addCell(new PdfPCell(new Paragraph(s.getSymbol())));
                table.addCell(new PdfPCell(new Paragraph(String.valueOf(s.getNettoValue()))));
                table.addCell(new PdfPCell(new Paragraph(String.valueOf(s.getVatPercentage()))));
                table.addCell(new PdfPCell(new Paragraph(String.valueOf(s.getCount()))));
                double brutto = s.getNettoValue() * s.getCount() * s.getVatPercentage() / 100;
                brutto += s.getNettoValue() * s.getCount();
                table.addCell(new PdfPCell(new Paragraph(String.valueOf(brutto))));
            }
            document.add(table);

            Anchor link = new Anchor("Invoice Writer by Robert Nowak");
            link.setReference("http://51.254.115.19:8080/");
            paragraph1.add(link);
            document.add(paragraph1);
            document.close(); // no need to close PDFwriter?
            return document;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private PdfPTable generateTableStructure() {
        PdfPTable table = new PdfPTable(6);
        PdfPCell cell1 = new PdfPCell(new Paragraph("Name"));
        cell1.setBackgroundColor(BaseColor.GRAY);

        PdfPCell cell2 = new PdfPCell(new Paragraph("Description"));
        cell2.setBackgroundColor(BaseColor.GRAY);

        PdfPCell cell3 = new PdfPCell(new Paragraph("Netto"));
        cell3.setBackgroundColor(BaseColor.GRAY);

        PdfPCell cell4 = new PdfPCell(new Paragraph("Vat (%)"));
        cell4.setBackgroundColor(BaseColor.GRAY);

        PdfPCell cell6 = new PdfPCell(new Paragraph("Quantity"));
        cell6.setBackgroundColor(BaseColor.GRAY);

        PdfPCell cell5 = new PdfPCell(new Paragraph("Brutto"));
        cell5.setBackgroundColor(BaseColor.GRAY);
        table.addCell(cell1);
        table.addCell(cell2);
        table.addCell(cell3);
        table.addCell(cell4);
        table.addCell(cell6);
        table.addCell(cell5);
        return table;
    }
}
