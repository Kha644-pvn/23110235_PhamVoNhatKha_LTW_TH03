package vn.iostar.Controller.Auth;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import vn.iostar.models.User;
import vn.iostar.services.UserService;
import vn.iostar.services.impl.UserServiceImpl;

@WebServlet("/user")
public class UserController extends HttpServlet {
	private final UserService service = new UserServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        String action = req.getParameter("action");

        if (action == null || action.isEmpty()) {
            resp.sendRedirect(req.getContextPath() + "/views/Login.jsp");
            return;
        }

        switch (action) {
            case "login":
                handleLogin(req, resp);
                break;
            case "register":
                handleRegister(req, resp);
                break;
            case "forgot":
                handleForgot(req, resp);
                break;
            case "reset":
                handleReset(req, resp);
                break;
            default:
                resp.sendRedirect(req.getContextPath() + "/views/Login.jsp");
                break;
        }
    }

    private void handleLogin(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        User u = service.login(username, password);

        if (u != null) {
            req.getSession().setAttribute("account", u);

         // Chuyển hướng theo role
            if (u.getRoleid() == 1) {          // Manager
                resp.sendRedirect(req.getContextPath() + "/manager/home");
            } else if (u.getRoleid() == 2) {   // Admin
                resp.sendRedirect(req.getContextPath() + "/admin/home");
            } else if (u.getRoleid() == 3) {   // User
                resp.sendRedirect(req.getContextPath() + "/user/home");
            } else {
                resp.sendRedirect(req.getContextPath() + "/views/Login.jsp");
            }
        } else {
            req.setAttribute("error", "Sai tên đăng nhập hoặc mật khẩu!");
            req.getRequestDispatcher("/views/Login.jsp").forward(req, resp);
        }
    }

    private void handleRegister(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String fullname = req.getParameter("fullname");
        String phone = req.getParameter("sdt");

        boolean ok = service.register(username, password, fullname, phone);

        if (ok) {
            req.setAttribute("success", "Đăng ký thành công! Hãy đăng nhập.");
        } else {
            req.setAttribute("error", "Đăng ký thất bại: Username đã tồn tại.");
        }
        req.getRequestDispatcher("/views/Register.jsp").forward(req, resp);
    }

    private void handleForgot(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        String username = req.getParameter("username");
        String fullname = req.getParameter("fullname");
        String phone = req.getParameter("phone");
        String newPassword = req.getParameter("newPassword");
        String confirmPassword = req.getParameter("confirmPassword");

        if (!newPassword.equals(confirmPassword)) {
            req.setAttribute("error", "Mật khẩu mới và xác nhận không khớp!");
            req.getRequestDispatcher("/views/ForgotPass.jsp").forward(req, resp);
            return;
        }

        boolean ok = service.verifyUserForReset(username, fullname, phone);
        if (!ok) {
            req.setAttribute("error", "Thông tin không khớp!");
            req.getRequestDispatcher("/views/ForgotPass.jsp").forward(req, resp);
            return;
        }

        boolean updated = service.resetPassword(username, newPassword);
        if (updated) {
            req.setAttribute("success", "Đặt lại mật khẩu thành công. Hãy đăng nhập lại.");
        } else {
            req.setAttribute("error", "Có lỗi khi đặt lại mật khẩu.");
        }
        req.getRequestDispatcher("/views/ForgotPass.jsp").forward(req, resp);
    }

    private void handleReset(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        String username = req.getParameter("username");
        String newPassword = req.getParameter("newPassword");
        String confirmPassword = req.getParameter("confirmPassword");

        if (!newPassword.equals(confirmPassword)) {
            req.setAttribute("error", "Mật khẩu mới và xác nhận không khớp!");
            req.setAttribute("username", username);
            req.getRequestDispatcher("/views/ResetPass.jsp").forward(req, resp);
            return;
        }

        boolean ok = service.resetPassword(username, newPassword);
        if (ok) {
            req.setAttribute("success", "Reset mật khẩu thành công. Hãy đăng nhập lại.");
        } else {
            req.setAttribute("error", "Reset mật khẩu thất bại! Vui lòng thử lại.");
        }
        req.setAttribute("username", username);
        req.getRequestDispatcher("/views/ResetPass.jsp").forward(req, resp);
    }

}
