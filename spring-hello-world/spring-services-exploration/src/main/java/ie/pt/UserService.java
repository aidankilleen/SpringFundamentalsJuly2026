package ie.pt;

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

    @Autowired
    UserDao dao;

    List<User> getActiveUsers() {

        return dao.getUsers()
                .stream()
                .filter(User::isActive)
                .toList();
    }
}
