package ie.pt.springboothelloworld;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import java.util.List;


/*
Provide all of the business logic / rules and operations that I might need to run on my Users
including reading and writing to the db
 */

@Service    // use @Service instead of @Component for Business Logic
public class UserService {

    private static final Logger log = LoggerFactory.getLogger(UserService.class);
    @Autowired
    UserDao dao;

    UserService() {
        log.info("User Service Starting...");
        log.error("Something went wrong");
        log.warn("Something seems suspicious");
        log.debug("debugging information to help resolve issues");
    }

    User addUser(User user) {
        // potentially the addUser function could
        // do a lot more than just create the record

        return dao.addUser(user);
    }

    List<User> getActiveUsers() {

        return dao.getUsers()
                .stream()
                .filter(User::isActive)
                .toList();
    }
}
