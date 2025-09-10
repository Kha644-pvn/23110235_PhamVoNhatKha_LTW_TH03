package vn.iostar.Controller.Category;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.iostar.models.Category;
import vn.iostar.services.impl.CategoryServiceImpl;

@WebServlet("/category/image")
public class CategoryImageServlet extends HttpServlet {
	 private final CategoryServiceImpl cs = new CategoryServiceImpl(); // Sử dụng DAO JPA bên trong service

	    @Override
	    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
	            throws ServletException, IOException {
	        try {
	            int id = Integer.parseInt(req.getParameter("id"));
	            Category c = cs.getCategoryById(id); // Gọi service, service gọi DAO JPA
	            if (c != null && c.getImage() != null) {
	                resp.setContentType("image/jpeg"); // hoặc "image/png"
	                resp.getOutputStream().write(c.getImage());
	                resp.getOutputStream().flush();
	            } else {
	                resp.sendError(HttpServletResponse.SC_NOT_FOUND);
	            }
	        } catch (NumberFormatException e) {
	            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID không hợp lệ");
	        }
	    }
	
	

}
