package robert.other;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by robert on 30.04.16.
 */

//@Component
public class SessionData {
    private String email; // TODO: 30.04.16 make email final and make proper bean constructor
    private boolean emailSetted = false;
    private final Date time = Calendar.getInstance().getTime();

    public SessionData() {
    }

    public SessionData(String email) {
        this.email = email;
    }

    public Date getTime() {
        return time;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (emailSetted) throw new RuntimeException("Email already setted!");
        emailSetted = true;
        this.email = email;
    }

    @Override
    public String toString() {
        return "SessionData{" +
                "email='" + email + '\'' +
                ", time=" + time.toString() +
                '}';
    }
}
