package ie.pt;

import java.util.List;

public class UserServiceConsumerBroken {

    public static void main(String[] args) {

        //
        // dependency injection (autowiring) only works
        // on spring-managed beans
        // ie. objects that are instantiated by spring
        // do not instantiate UserService directly
        UserService us = new UserService();

        List<User> users = us.getActiveUsers();

        for (User user : users) {

            System.out.println(user);
        }
    }
}
