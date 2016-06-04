package robert.services.api;

import com.itextpdf.text.Image;
import robert.responses.InvoiceTemplate;

/**
 * Created by robert on 28.05.16.
 */
public interface InvoiceGenerator {
    String generateInvoice(InvoiceTemplate template, Image image);
}
