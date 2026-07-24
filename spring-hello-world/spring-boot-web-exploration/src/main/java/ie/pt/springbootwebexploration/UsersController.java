package ie.pt.springbootwebexploration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UsersController {

    @Autowired
    UserService userService;

    @Audit("users api requested")
    @GetMapping("")
    List<User> getUsers() {
        return userService.getActiveUsers();
    }
    @GetMapping("/{id}")
    ResponseEntity<User> getUser(@PathVariable int id) {

        User user = userService.getUser(id);
        if (user == null) {
            // return a 404 (not found) status
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(user);
        }
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteUser(@PathVariable int id) {
        if (userService.deleteUser(id)) {
            return ResponseEntity.noContent().build();

        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // post (add)
    @PostMapping("")
    ResponseEntity<User> addUser(@RequestBody User user) {

        User addedUser = userService.addUser(user);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(addedUser);
    }

    @PutMapping("/{id}")
    ResponseEntity<User> updateUser(@PathVariable int id, @RequestBody User user) {

        User updatedUser = userService.updateUser(user);
        return ResponseEntity.ok(updatedUser);
    }
}
