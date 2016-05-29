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
        while (files.containsKey(owner)) {
            System.out.println("Old file still exists. Waiting for delete");
            try {
                Thread.sleep(3_000);
            } catch (InterruptedException e) {
            }
        }
        files.put(owner, fileName);
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
            try {
                Thread.sleep(15_000);
                File file = new File(files.get(owner));
                file.delete();
                System.out.println("Deleted file of " + owner);
            } catch (Exception e) {
                System.out.println("File deleting error");
            } finally {
                files.remove(owner);
                System.out.println("Thread finished");
            }
        }
    }
}
