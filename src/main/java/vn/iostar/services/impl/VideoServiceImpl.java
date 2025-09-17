package vn.iostar.services.impl;
import vn.iostar.dao.VideoDao;
import vn.iostar.dao.impl.VideoDaoImpl;
import vn.iostar.models.Video;
import vn.iostar.services.VideoService;

import java.util.List;
public class VideoServiceImpl implements VideoService {
	private final VideoDao dao = new VideoDaoImpl();

    @Override
    public boolean addVideo(Video v) {
        return dao.add(v);
    }

    @Override
    public boolean updateVideo(Video v) {
        return dao.update(v);
    }

    @Override
    public boolean deleteVideo(int id) {
        return dao.delete(id);
    }
    @Override
    public Video getVideoById(int id) {
        return dao.findById(id);
    }

    @Override
    public List<Video> getAllVideos() {
        return dao.getAll();
    }

    @Override
    public List<Video> getVideosByUser(int userid) {
        return dao.getByUserId(userid);
    }

    @Override
    public List<Video> searchByTitle(String keyword) {
        return dao.searchByTitle(keyword);
    }
    @Override
    public List<Video> searchByDescription(String keyword) {
        return dao.searchByDescription(keyword);
    }

}
