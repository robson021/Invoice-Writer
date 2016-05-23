package robert.other;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.transaction.NotSupportedException;
import java.io.File;

/**
 * Created by robert on 21.05.16.
 */

public class MailerImpl implements Mailer {

    @Autowired
    private JavaMailSender javaMailSender;


    @Override
    public void sendEmail(String to, String subject, String body, File file) throws NotSupportedException, MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper;

        helper = new MimeMessageHelper(message, true);
        helper.setSubject(subject);
        helper.setTo(to);
        body += "\n\n(This is an auto-generated message).";
        helper.setText(body, true);
        if (file != null) {
            // TODO: 23.05.16 file attachment
        }
        javaMailSender.send(message);
    }

    @Override
    public void sendEmail(SimpleMailMessage templateMessage) throws NotSupportedException {
        throw new NotSupportedException("Not yet supported");
    }
}
