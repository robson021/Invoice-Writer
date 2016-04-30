package robert.other;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by robert on 30.04.16.
 */

//@Component
public class SessionData {
    private String email;
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
