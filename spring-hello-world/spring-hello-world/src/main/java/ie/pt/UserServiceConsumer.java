package ie.pt;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class UserServiceConsumer {

    public static void main(String[] args) {

        System.out.println("User Service Consumer");
        ApplicationContext ctx = new AnnotationConfigApplicationContext(
                AppConfig.class);

        // Coding to interfaces NOT implementations
        // this code doesn't know which type of dao it is working with
        // UserDao dao = (UserDao)ctx.getBean("sqlite");
        UserService svc = ctx.getBean(UserService.class);

        List<User> users = svc.getActiveUsers();

        for (User user : users) {
            System.out.println(user);
        }

        // Without Spring
        //      DriverManagerDatasource
        // -> JdbcTemplate
        //  -> JdbcTemplateUserDao
        //  -> UserService

        // With Spring - just get the object
        // Spring takes care of the dependencies
        // UserService svc = ctx.getBean(UserService.class);


        // this is clearly beneficial at this level of complexity(simple)
        // it will become more and more beneficial as the complexity grows

        

        // dao.close();

    }
}
