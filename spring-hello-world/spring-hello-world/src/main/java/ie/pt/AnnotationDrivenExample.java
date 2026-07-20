package ie.pt;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class AnnotationDrivenExample {

    public static void main(String[] args) {

        System.out.println("Annotation Driven Spring Beans");

        ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);

        String message = ctx.getBean(String.class);
        System.out.println(message);

        User u = (User)ctx.getBean("u2");
        System.out.println(u);

        InMemoryUserDao ur = ctx.getBean(InMemoryUserDao.class);
        ur.addUser(new User(10, "Zoe", "zoe@gmail.com", false));
        ur.deleteUser(2);

        List<User> users = ur.getUsers();

        for (User user : users) {
            System.out.println(user);
        }

        u = ur.getUser(3);
        System.out.println(u);

        u.setName("CHANGED");
        ur.updateUser(u);

        users = ur.getUsers();

        for (User user : users) {
            System.out.println(user);
        }




    }
}
