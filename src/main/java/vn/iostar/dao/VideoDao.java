package vn.iostar.dao;
import vn.iostar.models.Video;
import java.util.List;

public interface VideoDao {
	boolean add(Video v);
    boolean update(Video v);
    boolean delete(int id);
    Video findById(int id);
    List<Video> getAll();
    List<Video> getByUserId(int userid);

    // Search
    List<Video> searchByTitle(String keyword);
    List<Video> searchByDescription(String keyword);

}
