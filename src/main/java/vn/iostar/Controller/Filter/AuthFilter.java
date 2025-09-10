package vn.iostar.Controller.Filter;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.*;
import java.io.IOException;
import vn.iostar.models.User;

@WebFilter("/*")
public class AuthFilter implements Filter{
	 @Override
	    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
	            throws IOException, ServletException {

	        HttpServletRequest req = (HttpServletRequest) request;
	        HttpServletResponse resp = (HttpServletResponse) response;

	        String path = req.getRequestURI();
	        HttpSession session = req.getSession(false);
	        User u = (session != null) ? (User) session.getAttribute("account") : null;

	        // Cho phép truy cập các trang công khai
	        if (path.contains("/login") || path.contains("/views/Login.jsp") || path.contains("/resources/")) {
	            chain.doFilter(request, response);
	            return;
	        }

	        // Kiểm tra phân quyền theo folder
	        if (path.contains("/user/") && (u == null || u.getRoleid() != 3)) {
	            resp.sendRedirect(req.getContextPath() + "/login");
	            return;
	        }
	        if (path.contains("/manager/") && (u == null || u.getRoleid() != 1)) {
	            resp.sendRedirect(req.getContextPath() + "/login");
	            return;
	        }
	        if (path.contains("/admin/") && (u == null || u.getRoleid() != 2)) {
	            resp.sendRedirect(req.getContextPath() + "/login");
	            return;
	        }

	        // Nếu mọi thứ ok thì đi tiếp
	        chain.doFilter(request, response);
	    }

}
