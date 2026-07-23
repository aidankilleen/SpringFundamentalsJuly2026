package ie.pt.springbootwebexploration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class WebPageController {

    @Autowired
    UserService userService;

    @GetMapping("/home")
    String getHomePage() {
        return "home";
    }
    @GetMapping("/about")
    String getAboutPage() {
        return "about";
    }
    @GetMapping("/contact")
    String getContactPage() {
        return "contact";
    }

    @GetMapping("/users")
    String getUsersPage(Model model) {

        List<User> users = userService.getUsers();

        model.addAttribute("title",
                "User List");
        model.addAttribute("users", users);

        // return the name of the "view" or "template"
        return "users";
    }
}
