package orlovskyi.service.impl;

import orlovskyi.dao.UserDao;
import orlovskyi.entity.User;
import orlovskyi.service.UserService;

import java.util.List;

public class DefaultUserService implements UserService {
    UserDao userDao;

    public DefaultUserService(UserDao userDao){
        this.userDao = userDao;
    }

    @Override
    public void addUser(User user) {
        userDao.addUser(user);
    }

    @Override
    public void removeUser(long userId) {
        userDao.removeUser(userId);
    }

    @Override
    public void editUser(User user) {
        userDao.editUser(user);
    }

    @Override
    public List<User> selectAllUsers() {
        return userDao.selectAllUsers();
    }

    @Override
    public List<User> selectSearchedUsers(String searchWord) {
        return userDao.selectSearchedUsers(searchWord);
    }
}
