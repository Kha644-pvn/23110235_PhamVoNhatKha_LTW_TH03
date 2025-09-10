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
	
	

}
