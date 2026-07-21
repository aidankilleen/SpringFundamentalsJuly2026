package ie.pt;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.util.List;

public class SpringJdbcInvestigation {

    public static void main(String[] args) {

        ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);

        //DriverManagerDataSource ds = ctx.getBean(DriverManagerDataSource.class);

        JdbcTemplate jdbc = ctx.getBean(JdbcTemplate.class);

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
