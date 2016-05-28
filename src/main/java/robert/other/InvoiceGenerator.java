package robert.other;

import com.itextpdf.text.Document;
import robert.responses.InvoiceTemplate;

/**
 * Created by robert on 28.05.16.
 */
public interface InvoiceGenerator {
    Document generateInvoice(InvoiceTemplate template);
}
