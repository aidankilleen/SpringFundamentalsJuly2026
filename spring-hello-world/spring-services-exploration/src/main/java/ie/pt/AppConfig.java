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
    public String getBean() {
        return "is this working?";
    }

    @Bean
    DriverManagerDataSource getDataSource() {
        DriverManagerDataSource ds = new DriverManagerDataSource();

        ds.setDriverClassName("org.sqlite.JDBC");
        String url = "jdbc:sqlite:C:\\work\\training\\java\\users.db";
        ds.setUrl(url);

        return ds;
    }

    @Bean
    JdbcTemplate getJdbcTemplate() {
        JdbcTemplate jdbc = new JdbcTemplate(getDataSource());
        return jdbc;
    }
}
