package vn.iostar.dao;
import java.util.List;
import vn.iostar.models.Category;

public interface CategoryDao {
	 boolean add(Category c);
	    boolean update(Category c);
	    boolean delete(int id, int userid);
	    Category findById(int id);
	    List<Category> getAll();
	    List<Category> getByUserId(int userid);
	    boolean updateByManager(Category c);
	    boolean deleteByManager(int id);
	    
	    // === Các hàm mới (cho Admin CRUD + Search) ===
	    List<Category> searchByName(String keyword); // tìm kiếm theo tên
	    boolean deleteByAdmin(int id);               // admin xóa trực tiếp
	
	

}
