package ie.pt.springbootwebexploration;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {
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
}
