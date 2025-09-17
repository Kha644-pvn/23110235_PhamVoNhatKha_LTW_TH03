package vn.iostar.services.impl;
import java.util.List;

import vn.iostar.dao.UserDao;
import vn.iostar.dao.impl.UserDaoImpl;
import vn.iostar.models.User;
import vn.iostar.services.UserService;

public class UserServiceImpl implements UserService {
	private final UserDao dao = new UserDaoImpl();

    @Override
    public User login(String username, String password) {
        return dao.login(username, password);
    }

    @Override
    public boolean register(String username, String password, String fullname, String phone) {
        if (username == null || password == null) return false;
        if (dao.checkExistUsername(username)) return false;
        User u = new User();
        u.setUsername(username);
        u.setPassword(password);
        u.setFullname(fullname);
        u.setSdt(phone);
        return dao.register(u);
    }

    @Override
    public boolean verifyUserForReset(String username, String fullname, String phone) {
        User u = dao.findByUsername(username);
        if (u != null) {
            return u.getFullname().equalsIgnoreCase(fullname)
                    && u.getSdt().equals(phone);
        }
        return false;
    }

    @Override
    public boolean resetPassword(String username, String newPassword) {
        return dao.updatePassword(username, newPassword);
    }
    // ==== Các method CRUD cho Admin ====

    @Override
    public boolean add(User u) {
        return dao.add(u);
    }

    @Override
    public boolean update(User u) {
        return dao.update(u);
    }

    @Override
    public boolean delete(int id) {
        return dao.delete(id);
    }

    @Override
    public User findById(int id) {
        return dao.findById(id);
    }

    @Override
    public List<User> getAll() {
        return dao.getAll();
    }

    // ==== Search ====

    @Override
    public List<User> searchByUsername(String keyword) {
        return dao.searchByUsername(keyword);
    }

    @Override
    public List<User> searchByFullname(String keyword) {
        return dao.searchByFullname(keyword);
    }

}
