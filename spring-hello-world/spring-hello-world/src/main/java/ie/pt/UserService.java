package ie.pt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import java.util.List;

// manage the users in the system
@Component
public class UserService {

    @Autowired
    UserDao dao;

    public UserService() {
        // we don't instantiate the objects!!
        // so don't do this
        // dao = new SqliteUserDao();

        // no need to create a context either!
        // we only need one for the whole application
        //ApplicationContext ctx = new AnnotationConfigApplicationContext(
        //        AppConfig.class);
    }

    // a simple sample of what might be in an enterprise
    // business object.
    List<User> getActiveUsers() {

        return dao.getUsers()
                .stream()
                .filter(User::isActive)
                .toList();
    }
}
