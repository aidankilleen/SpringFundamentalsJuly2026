package ie.pt;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.util.List;

public class JdbcInvestigation {

    public static void main(String[] args) {
        // to connect without spring
        DriverManagerDataSource ds = new DriverManagerDataSource();

        ds.setDriverClassName("org.sqlite.JDBC");
        String url = "jdbc:sqlite:C:\\work\\training\\java\\users.db";
        ds.setUrl(url);
        JdbcTemplate jdbc = new JdbcTemplate(ds);

        RowMapper<User> userMapper =
                (rs, rowNum) -> new User(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getBoolean("active"));

        String sql = "SELECT * FROM users";
        List<User> users = jdbc.query(sql, userMapper);

        users.forEach(System.out::println);

    }
}
