package ie.pt;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


public class SqliteUserDao implements UserDao {

    private List<User> users = new ArrayList<>();

    public SqliteUserDao() {
        this.users.add(new User(10, "Zoe", "zoe@gmail.com", true));
        this.users.add(new User(11, "Yvonne", "yvonne@gmail.com", true));
        this.users.add(new User(12, "Xavier", "xavier@gmail.com", false));
        this.users.add(new User(13, "Wendy", "wendy@gmail.com", true));
    }
    public List<User> getUsers() {
        return users;
    }

    public User getUser(int id) {
        return users.stream()
                .filter(user -> user.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public User addUser(User user) {
        int nextId = users.stream()
                        .mapToInt(User::getId)
                        .max()
                        .orElse(0) + 1;
        user.setId(nextId);
        users.add(user);
        return user;
    }

    public User updateUser(User user) {
        for (int i=0; i<users.size(); i++) {
            if (users.get(i).getId() == user.getId()) {
                users.set(i, user);
            }
        }
        return user;
    }

    public boolean deleteUser(int id) {
        return users.removeIf(user->user.getId() == id);
    }

    @Override
    public void close() {
        this.users.clear();
    }

}
