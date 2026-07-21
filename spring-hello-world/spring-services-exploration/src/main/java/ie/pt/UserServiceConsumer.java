package ie.pt;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class UserServiceConsumer {

    public static void main(String[] args) {

        ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = ctx.getBean(UserService.class);

        userService
                .getActiveUsers()
                .forEach(System.out::println);

        // without spring
        // Every person using this class
        // would need to instantiate:
        //  DataSource
        //  JdbcTemplate
        //  JdbcTemplateUserDao
        //  UserService

        // with spring we just request a UserService
        // and start using it

        //
    }
}
