package vn.iostar.dao;
import vn.iostar.models.User;
import java.util.List;

public interface UserDao {
	  User login(String username, String password);
	    boolean checkExistUsername(String username);
	    boolean register(User u);
	    User findByUsername(String username);
	    boolean updatePassword(String username, String newPassword);
	    
	    // ==== Bổ sung CRUD cho Admin ====
	    boolean add(User u);              // Admin thêm user
	    boolean update(User u);           // Admin cập nhật user
	    boolean delete(int id);           // Admin xóa user theo ID
	    User findById(int id);            // Tìm theo ID
	    List<User> getAll();              // Lấy tất cả user

	    // ==== Bổ sung Search ====
	    List<User> searchByUsername(String keyword);   // Tìm theo username
	    List<User> searchByFullname(String keyword);   // Tìm theo fullname

}
