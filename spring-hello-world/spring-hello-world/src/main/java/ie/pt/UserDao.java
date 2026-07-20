package ie.pt;

import java.util.List;

public interface UserDao {
    List<User> getUsers();
    User getUser(int id);
    User addUser(User user);
    User updateUser(User user);
    boolean deleteUser(int id);
    void close();
}
