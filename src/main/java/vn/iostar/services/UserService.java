package vn.iostar.services;
import java.util.List;

import vn.iostar.models.User;

public interface UserService {
	User login(String username, String password);
    boolean register(String username, String password, String fullname, String sdt);
    boolean verifyUserForReset(String username, String fullname, String sdt);
    boolean resetPassword(String username, String newPassword);
    
    // ==== Bổ sung CRUD cho Admin ====
    boolean add(User u);          // thêm mới user
    boolean update(User u);       // cập nhật user
    boolean delete(int id);       // xóa user
    User findById(int id);        // tìm theo ID
    List<User> getAll();          // lấy tất cả user

    // ==== Search ====
    List<User> searchByUsername(String keyword);
    List<User> searchByFullname(String keyword);

}
