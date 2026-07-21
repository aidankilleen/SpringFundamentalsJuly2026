package ie.pt;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Spring Services Exploration" );

        ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);

        UserDao dao = ctx.getBean(UserDao.class);

        User newUser = new User(-1,
                    "New user",
                    "new.user@gmail.com",
                    true);

        User addedUser = dao.addUser(newUser);

        System.out.println(addedUser);

        System.out.println("=====================");


        if (dao.deleteUser(999)) {
            System.out.println("999 deleted");
        }

        List<User> users = dao.getUsers();

        User u = dao.getUser(1000);
        System.out.println(u);
        System.out.println("====================");


        users.forEach(System.out::println);

        System.out.println("==================");
        User userToChange = users.get(0);
        userToChange.setName("Changed");
        userToChange.setEmail("changed@gmail.com");
        userToChange.setActive(!userToChange.isActive());

        dao.updateUser(userToChange);

        // read and display all users
        dao.getUsers()
            .forEach(System.out::println);


        dao.close();
    }
}
