package robert.repositories;

import java.io.File;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by robert on 29.05.16.
 */
public class FileRepositoryImpl implements FileRepository {
    private static final Map<String, String> files = new ConcurrentHashMap<>(50);

    @Override
    public void addNewFile(String owner, String fileName) {
        while (files.putIfAbsent(owner, fileName) != null) {
            try { // old file already deleted?
                System.out.println("Some file waits for being deleted");
                Thread.sleep(1_000);
            } catch (InterruptedException e) {
            } finally {
                this.deleteFile(owner);
                System.out.println("Forced delete of file.");
                try {
                    Thread.sleep(10); // give cleaning thread time do remove file form the map before recursive call
                } catch (InterruptedException e) {
                }
            }
        }
    }

    @Override
    public void deleteFile(String owner) {
        new Thread(new DeleteTask(owner)).start();
        System.out.println("Cleaning thread started");
    }

    @Override
    public String getFileName(String owner) {
        return files.get(owner);
    }

    private class DeleteTask implements Runnable {
        private final String owner;

        public DeleteTask(String owner) {
            this.owner = owner;
        }

        @Override
        public void run() {
            String fileToRemove = files.remove(owner);
            try {
                Thread.sleep(15_000); // wait before delete. Give user chance to download & mailer to send the file
                File file = new File(fileToRemove);
                file.delete();
                System.out.println("Deleted file of " + owner);
            } catch (Exception e) {
                System.out.println("File deleting error");
            } finally {
                System.out.println("Cleaning thread finished");
            }
        }
    }
}
