package ie.pt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JdbcTemplateUserDao implements UserDao {

    @Autowired
    JdbcTemplate jdbc;

    RowMapper<User> userMapper =
            (rs, rowNum) -> new User(rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getBoolean("active"));

    @Override
    public List<User> getUsers() {
        String sql = "SELECT * FROM users";
        List<User> users = jdbc.query(sql, userMapper);
        return users;
    }

    @Override
    public User getUser(int id) {
        return null;
    }

    @Override
    public User addUser(User user) {
        return null;
    }

    @Override
    public User updateUser(User user) {
        String sql = """
                UPDATE users
                SET name=?, email=?, active=?
                WHERE id=?
                """;
        return jdbc.update(
                sql,
                user.getName(),
                user.getEmail(),
                user.isActive(),
                user.getId()
        ) > 0 ? user : null;
    }

    @Override
    public boolean deleteUser(int id) {
        String sql = "DELETE FROM users WHERE id = ?";
        return jdbc.update(sql, id) > 0;
    }

    @Override
    public void close() {

    }
}
