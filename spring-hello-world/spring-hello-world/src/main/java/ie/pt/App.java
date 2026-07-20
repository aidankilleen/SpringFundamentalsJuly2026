package ie.pt;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Spring Framework" );

        ApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");

        // the "context" is the spring mechanism that will instantiate objects for us
        String str = ctx.getBean(String.class);
        System.out.println(str);

        // User u = new User(1, "Alice", "alice@gmail.com", true);

        User u = (User)ctx.getBean("u2");
        System.out.println(u);


        // Coding to interfaces NOT implementations
        // this code doesn't know which type of dao it is working with
        UserDao dao = ctx.getBean(UserDao.class);

        List<User> users = dao.getUsers();

        for (User user : users) {
            System.out.println(user);
        }

        dao.close();
    }
}
