package ie.pt.springboothelloworld;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.RowMapper;

@Configuration
public class AppConfig {

    @Bean
    String getMessage() {
        return "is this working?";
    }

    @Bean
    User getUser() {
        return new User(1, "Alice", "alice@gmail.com", true);
    }

    @Bean
    RowMapper<User> getRowMapper() {
        RowMapper<User> userMapper =
                (rs, rowNum) -> new User(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getBoolean("active"));
        return userMapper;
    }

}
