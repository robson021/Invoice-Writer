package robert.repositories;

import java.io.File;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by robert on 29.05.16.
 */
public class FileRepositoryImpl implements FileRepository {
    private static final Map<String, String> files = new ConcurrentHashMap<>(3);

    @Override
    public void addNewFile(String owner, String fileName) {
        while (files.putIfAbsent(owner, fileName) != null) { // is old file already deleted?
            try {
                System.out.println("Some file waits for being deleted");
                Thread.sleep(100);
            } catch (InterruptedException e) {
            }
        }
    }

    @Override
    public void deleteFile(String owner) {
        new Thread(new DeleteTask(owner)).start();
        System.out.println("Thread started");
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
            String fileName = files.remove(owner);
            try {
                Thread.sleep(45_000); // wait before delete. Give user chance to download & mailer to send file
                File file = new File(fileName);
                file.delete();
                System.out.println("Deleted file of " + owner);
            } catch (Exception e) {
                System.out.println("File deleting error");
            } finally {
                System.out.println("Thread finished");
            }
        }
    }
}
