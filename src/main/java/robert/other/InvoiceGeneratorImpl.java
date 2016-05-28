package robert.other;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import robert.responses.InvoiceTemplate;
import robert.responses.simpleentities.SimpleContractor;
import robert.responses.simpleentities.SimpleSalesman;
import robert.responses.simpleentities.SimpleService;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by robert on 28.05.16.
 */
public class InvoiceGeneratorImpl implements InvoiceGenerator {
    private static final int TABLE_SIZE = 6;
    @Override
    public String generateInvoice(InvoiceTemplate template, Image image) {
        Document document = new Document();
        String fileName = "Invoice " + Calendar.getInstance().getTime().toString() + ".pdf";
        try {
            PdfWriter.getInstance(document,
                    new FileOutputStream(fileName));
            document.open();

            Chunk underline = new Chunk("Invoice " + template.getInvoiceNumber());
            Paragraph element = new Paragraph(underline);
            element.setAlignment(Element.ALIGN_CENTER);
            underline.setUnderline(0.1f, -2f); //0.1 thick, -2 y-location
            document.add(element);
            document.add(new Chunk(" "));
            document.add(new Chunk(" "));

            Calendar c = Calendar.getInstance();
            SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");

            c.setTime(template.getDeadDate());
            String dead = formater.format(c.getTime());
            Paragraph pDeadline = new Paragraph("Payment deadline: " + dead);

            c.setTime(template.getSellDate());
            String sellDate = formater.format(c.getTime());
            Paragraph pSellDate = new Paragraph("Sell date: " + sellDate + ", " + template.getPlaceOfexposure());

            c.setTime(template.getExposureDate());
            String expDate = formater.format(c.getTime());
            Paragraph pExpDate = new Paragraph("Invoice date: " + expDate);

            Paragraph pFormOfPayment = new Paragraph("Form of payment: " + template.getFormOfPayment());

            // top data
            PdfPTable dateContentTable = new PdfPTable(2);
            dateContentTable.getDefaultCell().setBorder(0);
            dateContentTable.addCell(pDeadline);
            dateContentTable.addCell(pExpDate);
            dateContentTable.addCell(pFormOfPayment);
            dateContentTable.addCell(pSellDate);



            PdfPTable topTable = generateTopTable(template, image);
            document.add(topTable);

            document.add(new Chunk(" "));
            document.add(new Chunk(" "));

            document.add(dateContentTable);


            // MAIN TABLE WITH SERVICES
            double bruttoSum = 0;
            PdfPTable table = generateTableStructure();
            for (SimpleService s : template.getSelectedServices()) {
                table.addCell(new PdfPCell(new Paragraph(s.getName())));
                table.addCell(new PdfPCell(new Paragraph(s.getSymbol())));
                table.addCell(new PdfPCell(new Paragraph(String.valueOf(s.getNettoValue()))));
                table.addCell(new PdfPCell(new Paragraph(String.valueOf(s.getVatPercentage()))));
                table.addCell(new PdfPCell(new Paragraph(String.valueOf(s.getCount()))));
                table.addCell(new PdfPCell(new Paragraph(s.calculateBruttoAsString())));
                bruttoSum += s.calculateBrutto();
            }
            PdfPTable invisibleTable = new PdfPTable(TABLE_SIZE);
            for (int i = 0; i < TABLE_SIZE - 2; i++) {
                invisibleTable.addCell(new PdfPCell(new Paragraph("")));
            }
            invisibleTable.addCell(new PdfPCell(new Paragraph("Total:")));
            invisibleTable.addCell(new PdfPCell(new Paragraph(String.format("%.2f", bruttoSum) + "$")));
            invisibleTable.getDefaultCell().setBorder(0);

            document.add(new Chunk(" "));
            document.add(new Chunk(" "));
            document.add(table);
            document.add(invisibleTable);


            // FOOTER
            /*Anchor link = new Anchor("Made with 'Invoice Writer by Robert Nowak'");
            link.setFont(new Font(Font.FontFamily.HELVETICA, 8f, BaseFont.CAPHEIGHT, BaseColor.BLUE));
            link.setReference("http://51.254.115.19:8080/");
            //document.add(new Chunk(" "));
            Paragraph footer = new Paragraph(link);
            footer.setAlignment(Element.ALIGN_BOTTOM);
            //footer.setAlignment(Element.ALIGN_CENTER);
            document.add(new Chunk(" "));
            document.add(new Chunk(" "));
            document.add(footer);*/


            document.close(); // no need to close PDFwriter?
            return fileName;
        } catch (Exception e) {
            //e.printStackTrace();
        }
        return null;
    }

    private PdfPTable generateTopTable(InvoiceTemplate t, Image image) {
        PdfPTable salesmanTable = new PdfPTable(1); // nested table 1
        salesmanTable.getDefaultCell().setBorder(0);

        SimpleSalesman s = t.getSalesman();
        salesmanTable.addCell(new Paragraph(s.getName() + " " + s.getSurname()));
        salesmanTable.addCell(new Paragraph(s.getCompanyName()));
        salesmanTable.addCell(new Paragraph(s.getStreetName() + " " + s.getHomeNo() + ", " + s.getPostCode() + " " + s.getCity()));
        salesmanTable.addCell(new Paragraph("Regon: " + s.getRegon()));
        salesmanTable.addCell(new Paragraph("Nip: " + s.getNipNo()));
        salesmanTable.addCell(new Paragraph("Phone: " + s.getPhoneNo()));

        SimpleContractor c = t.getContractor();
        PdfPTable contractorTable = new PdfPTable(1);
        contractorTable.getDefaultCell().setBorder(0);

        contractorTable.addCell(new Paragraph(c.getName() + " " + c.getSurname()));
        contractorTable.addCell(new Paragraph(c.getCompanyName()));
        contractorTable.addCell(new Paragraph(c.getStreetName() + " " + c.getHomeNo() + ", " + c.getPostCode() + " " + c.getCity()));
        contractorTable.addCell(new Paragraph("Nip: " + c.getNipNo()));

        PdfPTable mainTable = new PdfPTable(3);
        mainTable.getDefaultCell().setBorder(0);

        if (image != null) {
            image.scaleAbsolute(150f, 150f);
            mainTable.addCell(image);
        } else {
            mainTable.addCell("");
        }
        mainTable.addCell(salesmanTable);
        mainTable.addCell(contractorTable);
        return mainTable;
    }

    private PdfPTable generateTableStructure() {
        PdfPTable table = new PdfPTable(TABLE_SIZE);
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
