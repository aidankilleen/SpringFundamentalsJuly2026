package ie.pt.springboothelloworld;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;

@SpringBootApplication
public class SpringBootHelloWorldApplication implements CommandLineRunner {

    @Value("${spring.application.name}")
    String title;

    @Autowired
    UserService userService;

    @Autowired
    JdbcTemplate jdbc;

    @Autowired
    RowMapper<User> userMapper;

    /*
    @Autowired
    ApplicationContext ctx;

    @Autowired
    User user;

    @Autowired
    UserDao dao;
    */
    public static void main(String[] args) {
        SpringApplication.run(SpringBootHelloWorldApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        System.out.println(title);

        /*
        User addedUser = userService.addUser(new User(-1, "Alice", "alice@gmail.com", true));

        System.out.println(addedUser);
        */


        System.out.println("======================");
        userService.getActiveUsers()
                .forEach(System.out::println);


        /*
        List<User> users = jdbc.query(
                "SELECT * FROM users",
                userMapper);

        users.forEach(System.out::println);
        */


        /*
        dao.getUsers().forEach(System.out::println);

        String message = ctx.getBean(String.class);
        System.out.println(message);

        User u = ctx.getBean(User.class);
        System.out.println(u);

        System.out.println(user);

         */
    }
}
