package robert.other;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

import javax.transaction.NotSupportedException;
import java.io.File;

/**
 * Created by robert on 21.05.16.
 */

public class MailerImpl implements Mailer {

    @Autowired
    private DefaultLogger logger;
    private MailSender mailSender;


    // TODO: 21.05.16 mail service


    @Override
    public void sendEmail(String address, String title, String text, File file) throws NotSupportedException {
        throw new NotSupportedException();
    }

    @Override
    public void sendEmail(SimpleMailMessage templateMessage) {
        try {
            mailSender.send(templateMessage);
            logger.info("Mail has been sent to: " + templateMessage.getFrom());
        } catch (Exception e) {
            logger.error("Mail sender error");
        }
    }
}
