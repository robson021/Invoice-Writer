package robert.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import robert.services.api.Mailer;

import javax.mail.internet.MimeMessage;

/**
 * Created by robert on 21.05.16.
 */

public class MailerImpl implements Mailer {

    @Autowired
    private JavaMailSender javaMailSender;


    @Override
    public void sendEmail(String to, String subject, String body, String file) {
        new Thread(new MailerTaskRunnable(to, subject, body, file)).start();
        //new MailerTaskRunnable(to, subject, body, file, sessionData).run();
    }

    @Override
    public Thread sendInvoice(String to, String fileName) {
        Thread thread = new Thread(new MailerTaskRunnable(to, "Invoice", "[Auto-generated message]", fileName));
        thread.start();
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
                System.out.println("Mailer exception.");
            } finally {
                System.out.println("Mailer thread finished: " + to);
            }
        }
    }

}
