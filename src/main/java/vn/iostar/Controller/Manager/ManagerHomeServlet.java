package vn.iostar.Controller.Manager;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;
import vn.iostar.models.Category;
import vn.iostar.models.User;
import vn.iostar.services.impl.CategoryServiceImpl;
@WebServlet("/manager/home")
public class ManagerHomeServlet extends HttpServlet {
	private final CategoryServiceImpl cs = new CategoryServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("account") == null) {
            resp.sendRedirect(req.getContextPath() + "/views/Login.jsp");
            return;
        }

        User u = (User) session.getAttribute("account");
        if (u.getRoleid() != 1) { // Manager
            resp.sendRedirect(req.getContextPath() + "/views/Login.jsp");
            return;
        }

        // Lấy tất cả category bằng JPA
        List<Category> list = cs.getAllCategories(); // cs dùng JPA

        // Chuyển ảnh sang Base64 cho hiển thị
        for (Category c : list) {
            if (c.getImage() != null) {
                String base64 = java.util.Base64.getEncoder().encodeToString(c.getImage());
                c.setImageBase64(base64);
            }
        }

        req.setAttribute("categories", list);
        req.setAttribute("currentUser", u);
        req.getRequestDispatcher("/views/manager/home.jsp").forward(req, resp);
    }

}
