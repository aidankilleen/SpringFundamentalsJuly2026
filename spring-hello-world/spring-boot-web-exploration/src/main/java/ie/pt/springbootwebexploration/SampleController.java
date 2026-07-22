package ie.pt.springbootwebexploration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class SampleController {

    @Autowired
    UserDao dao;

    @GetMapping("/hello")
    public String hello() {
        return "Is this working?";
    }

    @GetMapping("/user")
    public User getUser() {

        User u = new User(1,
                "Alice",
                "alice@gmail.com",
                true);
        return u;
    }

    @GetMapping("/orignalusers")
    public List<User> getUsers() {

        return dao.getUsers();
    }
}
