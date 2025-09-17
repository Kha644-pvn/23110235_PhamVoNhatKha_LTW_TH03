package vn.iostar.services;
import vn.iostar.models.Video;
import java.util.List;
public interface VideoService {
	boolean addVideo(Video v);
    boolean updateVideo(Video v);
    boolean deleteVideo(int id);
    Video getVideoById(int id);
    List<Video> getAllVideos();
    List<Video> getVideosByUser(int userid);

    // Search
    List<Video> searchByTitle(String keyword);
    List<Video> searchByDescription(String keyword);

}
