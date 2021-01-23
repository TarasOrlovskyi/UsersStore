package orlovskyi.service;

import orlovskyi.entity.User;

import java.util.List;

public interface UserService {
    void addUser(User user);
    void removeUser(long userId);
    void editUser(User user);
    List<User> selectAllUsers();
    List<User> selectSearchedUsers(String searchWord);
}
