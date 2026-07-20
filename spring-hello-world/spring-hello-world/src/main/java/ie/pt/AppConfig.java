package ie.pt;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

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
}