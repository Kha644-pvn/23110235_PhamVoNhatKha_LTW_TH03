package vn.iostar.Controller.Admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

import vn.iostar.models.User;
import vn.iostar.models.Category;
import vn.iostar.services.impl.CategoryServiceImpl;

@WebServlet("/admin/home")
public class AdminHomeServlet extends HttpServlet {
	 private final CategoryServiceImpl cs = new CategoryServiceImpl();

	    @Override
	    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
	            throws ServletException, IOException {

	        HttpSession session = req.getSession(false);
	        if (session == null || session.getAttribute("account") == null) {
	            resp.sendRedirect(req.getContextPath() + "/login");
	            return;
	        }

	        User u = (User) session.getAttribute("account");

	        // Lấy danh sách Category theo roleid
	        // 1 = manager, 2 = admin, 3 = user
	        List<Category> categories = cs.getCategoriesForHome(u.getRoleid(), u.getId());

	        // Chuyển image byte[] sang Base64 để JSP hiển thị
	        for (Category c : categories) {
	            if (c.getImage() != null) {
	                String base64 = java.util.Base64.getEncoder().encodeToString(c.getImage());
	                c.setImageBase64(base64);
	            }
	        }

	        req.setAttribute("categories", categories);
	        req.setAttribute("currentUser", u); // để JSP hiển thị fullname
	        req.getRequestDispatcher("/views/admin/home.jsp").forward(req, resp);
	    }

}
