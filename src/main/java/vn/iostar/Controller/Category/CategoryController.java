package vn.iostar.Controller.Category;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.servlet.*;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import vn.iostar.models.User;
import vn.iostar.models.Category;
import vn.iostar.services.CategoryService;
import vn.iostar.services.impl.CategoryServiceImpl;

@WebServlet("/category")
@MultipartConfig
public class CategoryController extends HttpServlet{
	private final CategoryService categoryService = new CategoryServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession(false);
        User currentUser = (User) session.getAttribute("account");
        if (currentUser == null) {
            resp.sendRedirect(req.getContextPath() + "/views/Login.jsp");
            return;
        }

        String action = req.getParameter("action");
        if (action == null) action = "list";

        switch (action) {
            case "add":
                req.getRequestDispatcher("/views/Category/CategoryAdd.jsp").forward(req, resp);
                break;
            case "edit":
                int editId = Integer.parseInt(req.getParameter("id"));
                Category cat = categoryService.getCategoryById(editId);
                req.setAttribute("category", cat);
                req.getRequestDispatcher("/views/Category/CategoryEdit.jsp").forward(req, resp);
                break;
            case "delete":
                doDeleteCategory(req, resp);
                break;
            case "list":
            default:
                doListCategory(req, resp, currentUser);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession(false);
        User currentUser = (User) session.getAttribute("account");
        if (currentUser == null) {
            resp.sendRedirect(req.getContextPath() + "/views/Login.jsp");
            return;
        }

        String action = req.getParameter("action");
        if ("create".equals(action)) {
            doCreateCategory(req, resp, currentUser);
        } else if ("save".equals(action)) {
            doUpdateCategory(req, resp, currentUser);
        } else {
            resp.sendRedirect(req.getContextPath() + "/category?action=list");
        }
    }

    private void doListCategory(HttpServletRequest req, HttpServletResponse resp, User u)
            throws ServletException, IOException {

        List<Category> categories = categoryService.getCategoriesForHome(u.getRoleid(), u.getId());
        req.setAttribute("categories", categories);
        req.setAttribute("currentUser", u);
        req.getRequestDispatcher("/views/Category/CategoryList.jsp").forward(req, resp);
    }

    private void doCreateCategory(HttpServletRequest req, HttpServletResponse resp, User u)
            throws IOException, ServletException {

        String name = req.getParameter("name");
        Part imagePart = req.getPart("image");
        byte[] imageBytes = null;
        if (imagePart != null && imagePart.getSize() > 0) {
            try (InputStream is = imagePart.getInputStream();
                 ByteArrayOutputStream buffer = new ByteArrayOutputStream()) {
                int nRead;
                byte[] data = new byte[1024];
                while ((nRead = is.read(data)) != -1) buffer.write(data, 0, nRead);
                imageBytes = buffer.toByteArray();
            }
        }

        // -------------------------
        // Tìm ID nhỏ nhất chưa gán
        // -------------------------
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("TH02");
        EntityManager em = emf.createEntityManager();
        int nextId = 1;
        try {
            em.getTransaction().begin();
            @SuppressWarnings("unchecked")
            List<Integer> ids = em.createNativeQuery("SELECT id FROM Category ORDER BY id ASC")
                                  .getResultList();
            for (Integer id : ids) {
                if (id != nextId) break; // nextId là số trống đầu tiên
                nextId++;
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            ex.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }

        // -------------------------
        // Tạo Category mới
        // -------------------------
        Category c = new Category();
        c.setId(nextId);  // gán ID nhỏ nhất còn trống
        c.setName(name);
        c.setImage(imageBytes);
        c.setUser(u);

        // Thêm category vào DB
        boolean ok = categoryService.addCategory(c);
        req.getSession().setAttribute("msg", ok ? "Thêm Category thành công!" : "Thêm Category thất bại!");
        resp.sendRedirect(req.getContextPath() + "/category?action=list");
    }


    private void doUpdateCategory(HttpServletRequest req, HttpServletResponse resp, User u)
            throws IOException, ServletException {

        int id = Integer.parseInt(req.getParameter("id"));
        Category existing = categoryService.getCategoryById(id);
        if (existing == null) {
            resp.sendRedirect(req.getContextPath() + "/category?action=list");
            return;
        }

        String name = req.getParameter("name");
        Part imagePart = req.getPart("image");
        if (imagePart != null && imagePart.getSize() > 0) {
            try (InputStream is = imagePart.getInputStream();
                 ByteArrayOutputStream buffer = new ByteArrayOutputStream()) {
                int nRead;
                byte[] data = new byte[1024];
                while ((nRead = is.read(data)) != -1) buffer.write(data, 0, nRead);
                existing.setImage(buffer.toByteArray());
            }
        }

        existing.setName(name);
        existing.setUser(u);
        boolean ok = categoryService.updateCategory(existing, u.getId(), getRoleName(u.getRoleid()));
        req.getSession().setAttribute("msg", ok ? "Cập nhật thành công!" : "Cập nhật thất bại!");
        resp.sendRedirect(req.getContextPath() + "/category?action=list");
    }

    private void doDeleteCategory(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        HttpSession session = req.getSession(false);
        User u = (User) session.getAttribute("account");
        int id = Integer.parseInt(req.getParameter("id"));

        boolean ok = categoryService.deleteCategory(id, u.getId(), getRoleName(u.getRoleid()));
        session.setAttribute("msg", ok ? "Xóa thành công!" : "Xóa thất bại!");
        resp.sendRedirect(req.getContextPath() + "/category?action=list");
    }

    private String getRoleName(int roleId) {
        switch (roleId) {
            case 1: return "manager";
            case 2: return "admin";
            default: return "user";
        }
    }

}
