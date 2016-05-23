package robert.other;

import org.springframework.mail.SimpleMailMessage;

import javax.mail.MessagingException;
import javax.transaction.NotSupportedException;
import java.io.File;

/**
 * Created by robert on 21.05.16.
 */
public interface Mailer {
    void sendEmail(String to, String subject, String body, File file) throws NotSupportedException, MessagingException;

    void sendEmail(SimpleMailMessage templateMessage) throws NotSupportedException;
}
