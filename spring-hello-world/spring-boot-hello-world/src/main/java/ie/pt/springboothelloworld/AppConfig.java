package ie.pt.springboothelloworld;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
}
