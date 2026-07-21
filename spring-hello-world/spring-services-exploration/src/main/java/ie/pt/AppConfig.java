package ie.pt;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
@ComponentScan("ie.pt")
@PropertySource("classpath:app.properties")
public class AppConfig {

    @Value("${database.file}")
    String databaseFile;

    @Value("${database.driverClassName}")
    String driverClassName;

    /*
    @Bean
    public String getBean() {
        return "is this working?";
    }
    */

    @Bean
    DriverManagerDataSource getDataSource() {
        DriverManagerDataSource ds = new DriverManagerDataSource();

        ds.setDriverClassName(driverClassName);
        String url = "jdbc:sqlite:" + databaseFile;
        //System.out.println("Opening:" + url);

        ds.setUrl(url);
        return ds;
    }

    @Bean
    JdbcTemplate getJdbcTemplate() {
        JdbcTemplate jdbc = new JdbcTemplate(getDataSource());
        return jdbc;
    }

    //@Bean
    UserDao getUserDao() {
        // if you have an object that requires parameters
        // you can instantiate it using @Bean
        // and provide the parameters yourself
        UserDao dao = new SqliteUserDao(databaseFile);
        return dao;
    }
}
