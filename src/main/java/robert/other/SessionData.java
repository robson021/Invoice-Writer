package robert.other;

import java.io.File;
import java.util.Calendar;
import java.util.Date;
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

    private String lastInvoice = null;
    private boolean userFinishedDownloading = true;
    private boolean mailerFinished = true;

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

    public void setEmail(String email) { // set once
        if (emailSetted) throw new RuntimeException("Email already setted!");
        this.email = email;
        emailSetted = true;
    }

    public String getLastInvoice() {
        return lastInvoice;
    }

    public void setLastInvoice(String lastInvoice) throws Exception {
        if (this.lastInvoice != null) {
            throw new Exception("Old invoice still in memory");
        }
        this.userFinishedDownloading = false;
        this.lastInvoice = lastInvoice;
    }

    public boolean isUserFinishedDownloading() {
        return userFinishedDownloading;
    }

    public void setUserFinishedDownloading(boolean userFinishedDownloading) {
        this.userFinishedDownloading = userFinishedDownloading;
    }

    public boolean isMailerFinished() {
        return mailerFinished;
    }

    public void setMailerFinished(boolean mailerFinished) {
        this.mailerFinished = mailerFinished;
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

    public void tryCleanFile() {
        if (userFinishedDownloading && mailerFinished) {
            String fileToRemove = this.lastInvoice;
            lastInvoice = null;
            try {
                new Thread(new CleaningTask(fileToRemove)).start();
            } catch (Exception e) {
                System.out.println("Could not delete the file");
            }
        } else {
            System.out.println("Can not delete the file yet: " + email);
        }
    }

    private class CleaningTask implements Runnable {
        private final String file;

        public CleaningTask(String fileToRemove) {
            this.file = fileToRemove;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(10_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                new File(file).delete();
            } finally {
                System.out.println("Cleaning thread finished: " + email);
            }
        }
    }
}
