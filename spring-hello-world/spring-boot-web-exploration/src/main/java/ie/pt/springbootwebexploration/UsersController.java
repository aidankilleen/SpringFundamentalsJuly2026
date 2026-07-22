package ie.pt.springbootwebexploration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UsersController {

    @Autowired
    UserService userService;

    @GetMapping("/users")
    List<User> getUsers() {
        return userService.getActiveUsers();
    }
    @GetMapping("/users/{id}")
    ResponseEntity<User> getUser(@PathVariable int id) {

        User user = userService.getUser(id);
        if (user == null) {
            // return a 404 (not found) status
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(user);
        }
    }
    // delete (delete)

    // post (add)

    // put (update)
}
