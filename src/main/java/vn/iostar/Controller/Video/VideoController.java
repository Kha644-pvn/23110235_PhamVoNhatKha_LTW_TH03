package vn.iostar.Controller.Video;
import vn.iostar.models.Video;
import vn.iostar.services.VideoService;
import vn.iostar.services.impl.VideoServiceImpl;
import vn.iostar.models.User;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
@WebServlet("/admin/video")

public class VideoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private final VideoService videoService = new VideoServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String action = req.getParameter("action");
        if (action == null) action = "list";

        switch (action) {
            case "new":
                req.getRequestDispatcher("/views/admin/video/video-form.jsp").forward(req, resp);
                break;

            case "edit":
            	int idEdit = Integer.parseInt(req.getParameter("id"));
                Video vEdit = videoService.getVideoById(idEdit);
                req.setAttribute("video", vEdit);
                req.getRequestDispatcher("/views/admin/video/video-form.jsp").forward(req, resp);
                break;

            case "delete":
                int idDel = Integer.parseInt(req.getParameter("id"));
                videoService.deleteVideo(idDel);
                resp.sendRedirect(req.getContextPath() + "/admin/video?action=list");
                break;

            case "search":
                String keyword = req.getParameter("keyword");
                String type = req.getParameter("type"); // title or description
                List<Video> result;
                if ("title".equals(type)) {
                    result = videoService.searchByTitle(keyword);
                } else {
                    result = videoService.searchByDescription(keyword);
                }
                req.setAttribute("videos", result);
                req.getRequestDispatcher("/views/admin/video/video-list.jsp").forward(req, resp);
                break;

            case "list":
            default:
                List<Video> list = videoService.getAllVideos();
                req.setAttribute("videos", list);
                req.getRequestDispatcher("/views/admin/video/video-list.jsp").forward(req, resp);
                break;
        }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String idStr = req.getParameter("id");
        String title = req.getParameter("title");
        String description = req.getParameter("description");
        String url = req.getParameter("url");
        int userid = Integer.parseInt(req.getParameter("userid")); // liên kết user

        // Tạo object Video
        Video v = new Video();
        v.setTitle(title);
        v.setDescription(description);
        v.setUrl(url);

        // Gắn User vào Video
        User user = new User();
        user.setId(userid);   // chỉ cần set id, JPA sẽ hiểu đây là user đã tồn tại
        v.setUser(user);

        if (idStr == null || idStr.isEmpty()) {
            // add
            videoService.addVideo(v);
        } else {
            // update
            v.setId(Integer.parseInt(idStr));
            videoService.updateVideo(v);
        }

        resp.sendRedirect(req.getContextPath() + "/admin/video?action=list");
    }

}
