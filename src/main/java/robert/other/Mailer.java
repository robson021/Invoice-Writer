package robert.other;

import org.springframework.mail.SimpleMailMessage;

import javax.transaction.NotSupportedException;
import java.io.File;

/**
 * Created by robert on 21.05.16.
 */
public interface Mailer {
    void sendEmail(String address, String title, String text, File file) throws NotSupportedException;

    void sendEmail(SimpleMailMessage templateMessage);
}
