package vn.iostar.services.impl;

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

}
