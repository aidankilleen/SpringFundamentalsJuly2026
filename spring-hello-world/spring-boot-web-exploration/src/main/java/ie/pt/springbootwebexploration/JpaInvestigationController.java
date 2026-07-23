package ie.pt.springbootwebexploration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Controller
public class JpaInvestigationController {

    @Autowired
    UserAccountRepository repo;
    
    @GetMapping("/jpa")
    String getJpaHome(Model model) {

        List<UserAccount> users = repo.findAll();

        model.addAttribute("users", users);
        System.out.println(users);
        return "jpa";
    }

    @GetMapping("/jpa/create")
    String addJpaRecord () {

        int x = (int)(Math.random()*10000);
        UserAccount ac = new UserAccount("Zoe", "zoe" + x + "@gmail.com", "kjsdhjfkjhfds", Instant.now());
        repo.save(ac);

        System.out.println(ac);
        return "redirect:/jpa";
    }

    @GetMapping("/jpa/delete/{id}")
    String deleteJpaRecord(Model model, @PathVariable Long id) {

        model.addAttribute("id", id);
        Optional<UserAccount> user = repo.findById(id);
        if (user.isEmpty()) {
            // redirect to the main page
            return "redirct:/jpa";
        } else {
            return "confirm_delete";
        }
    }

    @PostMapping("/jpa/delete/{id}")
    String doDeleteJpaRecord(Model model, @PathVariable Long id) {
        // do the delete
        Optional<UserAccount> user = repo.findById(id);

        repo.delete(user.get());
        System.out.println(user);


        model.addAttribute("title", "Success");
        model.addAttribute("message", "The user " + id + " was deleted");

//        return "show_message";
        return "redirect:/jpa";
    }

    @GetMapping("/jpa/edit/{id}")
    String editJpaRecord(Model model, @PathVariable Long id) {

        Optional<UserAccount> user = repo.findById(id);

        if (user.isEmpty()) {
            return "redirect:/jpa";
        } else {
            model.addAttribute("adding", false);
            model.addAttribute("user", user.get());
            return "user-account-form";
        }
    }
    @PostMapping("/jpa/edit/{id}")
    String doEditJpaRecord(Model model, @ModelAttribute("user") UserAccount user) {

        System.out.println(user);

        model.addAttribute("title", "Edited");
        model.addAttribute("message", "editing");
        return "show_message";
    }
}
