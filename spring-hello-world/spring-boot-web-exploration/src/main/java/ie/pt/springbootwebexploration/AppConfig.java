package ie.pt.springbootwebexploration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class AppConfig {

    @Bean
    RowMapper<User> getRowMapper() {
        RowMapper<User> userMapper =
                (rs, rowNum) -> new User(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getBoolean("active"));
        return userMapper;
    }

    @Bean
    BCryptPasswordEncoder getPasswordEncoder() {

        return new BCryptPasswordEncoder();
    }
}
