package ie.pt.springbootwebexploration;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

//@Repository
public class InMemoryUserDao implements UserDao {

    private List<User> users = new ArrayList<>();

    public InMemoryUserDao() {
        this.users.add(new User(1, "Alice", "alice@gmail.com", false));
        this.users.add(new User(2, "Bob", "bob@gmail.com", true));
        this.users.add(new User(3, "Carol", "carol@gmail.com", false));
        this.users.add(new User(4, "Dan", "dan@gmail.com", true));
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
