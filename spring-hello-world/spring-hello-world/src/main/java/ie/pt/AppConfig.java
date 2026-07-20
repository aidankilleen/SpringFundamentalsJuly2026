package ie.pt;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
@ComponentScan("ie.pt")
public class AppConfig {

    @Bean
    public String message() {
        return "This is a spring message";
    }

    @Bean("u1")
    public User getuser1() {
        return new User(4, "Dan", "dan@gmail.com", true);
    }

    @Bean("u2")
    public User getuser2() {
        return new User(4, "Dan", "dan@gmail.com", true);
    }


    public UserDao getRepo() {
        return new InMemoryUserDao();
    }

    @Bean
    public DriverManagerDataSource getDatasource() {
        DriverManagerDataSource ds = new DriverManagerDataSource();

        ds.setDriverClassName("org.sqlite.JDBC");
        String url = "jdbc:sqlite:C:\\work\\training\\java\\users.db";
        ds.setUrl(url);
        return ds;
    }
    @Bean
    public JdbcTemplate getJdbcTemplate() {
        return new JdbcTemplate(getDatasource());
    }


}