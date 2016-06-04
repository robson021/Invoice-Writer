package robert.session;

import java.io.File;

/**
 * Created by robert on 04.06.16.
 */

public class FileInfo {
    private String owner, fileName;
    private boolean mailerFinished = false;
    private boolean downloadingFinished = false;
    //private boolean cleaned = true;


    public String getOwner() {
        return owner;
    }

    public String getFileName() {
        return fileName;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public boolean isMailerFinished() {
        return mailerFinished;
    }

    public void setMailerFinished(boolean mailerFinished) {
        this.mailerFinished = mailerFinished;
    }

    public boolean isDownloadingFinished() {
        return downloadingFinished;
    }

    public void setDownloadingFinished(boolean downloadingFinished) {
        this.downloadingFinished = downloadingFinished;
    }

    public synchronized boolean tryCleanFile() {
        if (mailerFinished && downloadingFinished) {
            try {
                new File(fileName).delete();
            } catch (Exception e) {
                System.out.println("Error. Could not deleted the file of " + owner);
            } finally {
                System.out.println("Cleaning done.");
                return true;
            }

        } else {
            return false;
        }
    }
}
