package robert.other;

import com.itextpdf.text.Anchor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
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
    public Document generateInvoice(InvoiceTemplate template) {
        Document document = new Document();
        String fileName = "Invoice " + Calendar.getInstance().getTime().toString() + ".pdf";
        try {
            PdfWriter.getInstance(document,
                    new FileOutputStream(fileName));
            document.open();
            Paragraph paragraph1 = new Paragraph("Invoice");

            PdfPTable table = new PdfPTable(5);
            for (SimpleService s : template.getSelectedServices()) {
                table.addCell(new PdfPCell(new Paragraph(s.getName())));
                table.addCell(new PdfPCell(new Paragraph(s.getSymbol())));
                table.addCell(new PdfPCell(new Paragraph(String.valueOf(s.getNettoValue()))));
                table.addCell(new PdfPCell(new Paragraph(String.valueOf(s.getVatPercentage()))));
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
}
