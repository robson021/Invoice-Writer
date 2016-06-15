package robert.session;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by robert on 30.04.16.
 */
public class SessionData {
    private static final AtomicInteger idCounter = new AtomicInteger(0);
    private final int id = idCounter.incrementAndGet();
    private String email = null;
    private boolean emailSetted = false;
    private final Date time = Calendar.getInstance().getTime();

    private UUID token = null;
    private UUID tokenToCheck = null;
    private HttpServletResponse response;

    private String lastInvoice = null;
    private Thread mailerThread = null;

    public SessionData() {
    }

    public SessionData(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) { // set once
        if (emailSetted) throw new RuntimeException("Email already setted!");
        this.email = email;
        emailSetted = true;
    }

    public UUID getToken() {
        return token;
    }

    public void setToken(UUID token) {
        this.token = token;
    }

    public void setMailerThread(Thread mailerThread) {
        if (mailerThread != null) {
            try {
                mailerThread.join();
            } catch (Exception e) {
                System.out.println("Thread join exception");
            }
        }
        this.mailerThread = mailerThread;
    }

    public UUID getTokenToCheck() {
        return tokenToCheck;
    }

    public void setTokenToCheck(String uuidToCheck, HttpServletResponse response) {
        if (uuidToCheck != null) {
            this.tokenToCheck = UUID.fromString(uuidToCheck);
        }
        this.response = response;
    }

    public int getId() {
        return id;
    }

    public String getLastInvoice() {
        return lastInvoice;
    }

    public void setLastInvoice(String lastInvoice) throws Exception {
        if (this.lastInvoice != null) {
            throw new Exception("Old invoice is not deleted!");
        } else {
            this.lastInvoice = lastInvoice;
        }
    }

    public HttpServletResponse getResponse() {
        return response;
    }

    @Override
    public String toString() {
        return "SessionData{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", time=" + time.toString() +
                '}';
    }

    public void clean() {
        new Thread(new CleaningTask(lastInvoice)).start();
    }


    private class CleaningTask implements Runnable {
        private final String file;
        public CleaningTask(String fileToRemove) {
            this.file = fileToRemove;
        }

        @Override
        public void run() {
            lastInvoice = null;
            try {
                Thread.sleep(3_000); // not necessary
                if (mailerThread != null) {
                    mailerThread.join();
                    System.out.println("Mailer finished");
                }
                new File(file).delete();
            } catch (Exception e) {
                System.out.println("Cleaning exception.");
            } finally {
                mailerThread = null;
                System.out.println("Cleaning done: " + file);
            }
        }
    }
}
