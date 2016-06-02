package robert.other;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.internet.MimeMessage;

/**
 * Created by robert on 21.05.16.
 */

public class MailerImpl implements Mailer {

    @Autowired
    private JavaMailSender javaMailSender;


    @Override
    public void sendEmail(String to, String subject, String body, String file, SessionData sessionData) {
        sessionData.setMailerFinished(false);
        new Thread(new MailerTaskRunnable(to, subject, body, file, sessionData)).start();
    }


    private class MailerTaskRunnable implements Runnable {
        private final String to, subject, file;
        private String body;
        private final SessionData sessionData;

        // TODO: 02.06.16 proxy bean
        public MailerTaskRunnable(String to, String subject, String body, String file, SessionData sessionData) {
            this.to = to;
            this.subject = subject;
            this.body = body;
            this.file = file;
            this.sessionData = sessionData;
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
                sessionData.setMailerFinished(true);
                sessionData.tryCleanFile();
                System.out.println("Mailer thread finished: " + to);
            }
        }
    }

}
