package robert.other;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by robert on 30.04.16.
 */

//@Component
public class SessionData {
    private static final AtomicInteger idCounter = new AtomicInteger(0);
    private final int id = idCounter.incrementAndGet();
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

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "SessionData{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", time=" + time.toString() +
                '}';
    }
}
