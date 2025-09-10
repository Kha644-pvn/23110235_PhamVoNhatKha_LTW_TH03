package vn.iostar.dao;
import vn.iostar.models.User;

public interface UserDao {
	  User login(String username, String password);
	    boolean checkExistUsername(String username);
	    boolean register(User u);
	    User findByUsername(String username);
	    boolean updatePassword(String username, String newPassword);

}
