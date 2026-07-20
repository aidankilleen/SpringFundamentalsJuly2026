package ie.pt;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class LooseCouplingInvestigation {

    public static void main(String[] args) {

        System.out.println("Loose Coupling Investigation");

        ApplicationContext ctx = new AnnotationConfigApplicationContext(
                AppConfig.class);

        // Coding to interfaces NOT implementations
        // this code doesn't know which type of dao it is working with
        // UserDao dao = (UserDao)ctx.getBean("sqlite");
        UserDao dao = ctx.getBean(UserDao.class);

        List<User> users = dao.getUsers();

        for (User user : users) {
            System.out.println(user);
        }

        dao.close();


    }
}
