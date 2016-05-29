package robert.repositories;

/**
 * Created by robert on 29.05.16.
 */
public interface FileRepository {
    void addNewFile(String owner, String fileName);

    void deleteFile(String owner);

    String getFileName(String owner);
}
