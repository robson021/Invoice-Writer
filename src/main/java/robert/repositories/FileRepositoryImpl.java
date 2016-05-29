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
        files.put(owner, fileName);
    }

    @Override
    public void deleteFile(String owner) {
        try {
            File file = new File(files.get(owner));
            file.delete();
            System.out.println("Deleted file of " + owner);
        } catch (Exception e) {
            System.out.println("Error. Could not delete the file.");
        }
    }

    @Override
    public String getFileName(String owner) {
        return files.get(owner);
    }
}
