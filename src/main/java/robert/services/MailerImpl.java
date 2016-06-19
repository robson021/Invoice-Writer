package robert.services;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import robert.services.api.Mailer;

import javax.mail.internet.MimeMessage;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by robert on 21.05.16.
 */
public class MailerImpl implements Mailer {

    @Autowired
    private JavaMailSender javaMailSender;

    private Logger logger = Logger.getLogger(Mailer.class);

    private final Executor threadPool;

    public MailerImpl() {
        int n = Runtime.getRuntime().availableProcessors();
        if (n<8) {n = 8;}
        threadPool = Executors.newFixedThreadPool(n);
    }

    @Override
    public void sendEmail(String to, String subject, String body, String file) {
        threadPool.execute(new Thread(new MailerTaskRunnable(to, subject, body, file)));
    }

    @Override
    public void sendEmailToAdmin(String from, String topic, String body) {
        body = "Message form: " + from + "\n" + body;
        threadPool.execute(new Thread(new MailerTaskRunnable("invoice.writer.app@gmail.com", "Question", body, null)));
    }

    @Override
    public Thread sendInvoice(String to, String fileName) {
        Thread thread = new Thread(new MailerTaskRunnable(to, "Invoice", "[Auto-generated message]", fileName));
        threadPool.execute(thread);
        return thread;
    }


    private class MailerTaskRunnable implements Runnable {
        private final String to, subject, file;
        private String body;

        public MailerTaskRunnable(String to, String subject, String body, String file) {
            this.to = to;
            this.subject = subject;
            this.body = body;
            this.file = file;
        }

        @Override
        public void run() {
            logger.info("Mailer started. Message to: " + to);
            try {
                MimeMessage message = javaMailSender.createMimeMessage();
                MimeMessageHelper helper;

                helper = new MimeMessageHelper(message, true);
                helper.setSubject(subject);
                helper.setTo(to);
                body += "\n\n (This is an auto-generated message).";
                helper.setText(body, true);
                if (file != null) {
                    FileSystemResource f = new FileSystemResource(file);
                    helper.addAttachment(f.getFilename(), f);
                }
                javaMailSender.send(message);
            } catch (Exception e) {
                logger.error("Mailer exception.");
            } finally {
                logger.info("Mailer thread finished: " + to);
            }
        }
    }

}
