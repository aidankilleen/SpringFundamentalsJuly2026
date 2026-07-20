package ie.pt;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.sql.Driver;
import java.util.List;

public class SpringJdbcInvestigation {

    public static void main(String[] args) {

        /*
        DriverManagerDataSource ds = new DriverManagerDataSource();

        ds.setDriverClassName("org.sqlite.JDBC");
        String url = "jdbc:sqlite:C:\\work\\training\\java\\users.db";
        ds.setUrl(url);
        */

        ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        //DriverManagerDataSource ds = ctx.getBean(DriverManagerDataSource.class);

        //JdbcTemplate jdbc = new JdbcTemplate(ds);

        JdbcTemplate jdbc = ctx.getBean(JdbcTemplate.class);

        RowMapper<User> userMapper =
                (rs, rowNum) -> new User(rs.getInt("id"),
                                                     rs.getString("name"),
                                                     rs.getString("email"),
                                                     rs.getBoolean("active"));

        String sql = "UPDATE users SET name='changed', email='changed@gmail.com', active=0 WHERE id= 999";

        jdbc.update(sql);

        sql = "SELECT * FROM users";
        List<User> users = jdbc.query(sql, userMapper);

        for (User user : users) {
            System.out.println(user);
        }









    }
}
