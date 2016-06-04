package robert.services.api;

import robert.session.SessionData;

/**
 * Created by robert on 21.05.16.
 */
public interface Mailer {
    void sendEmail(String to, String subject, String body, String file, SessionData sessionData);
}
