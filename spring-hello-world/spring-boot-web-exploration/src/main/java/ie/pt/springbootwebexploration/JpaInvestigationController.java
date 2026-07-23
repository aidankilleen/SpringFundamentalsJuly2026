package ie.pt.springbootwebexploration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

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
}
