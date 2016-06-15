package robert.services.api;

/**
 * Created by robert on 21.05.16.
 */
public interface Mailer {
    void sendEmail(String to, String subject, String body, String file);

    void sendEmailToAdmin(String from, String topic, String body);

    Thread sendInvoice(String to, String fileName);
}
