package ie.pt.springbootwebexploration;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @GetMapping("/jpa")
    String getJpaHome(Model model) {

        UserAccountDto dto = new UserAccountDto(1L, "Alice", "alice@gmail.com", "pwd", "pwd");

        System.out.println(dto.name());
        //dto.name("no setters");
        System.out.println(dto);
        UserAccountDto dto2 = new UserAccountDto(1L, "Alice", "alice@gmail.com", "pwd", "pwd");
        if (dto.equals(dto2)) {
            System.out.println("Same");
        } else {
            System.out.println("Different");
        }

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

            UserAccount u = user.get();
            UserAccountDto dto = new UserAccountDto(u.getId(),
                        u.getName(),
                        u.getEmail(),
                    "",
                    "");
            model.addAttribute("adding", false);
            model.addAttribute("userDto", dto);
            return "user-account-form";
        }
    }
    @PostMapping("/jpa/edit/{id}")
    String doEditJpaRecord(Model model,
                           @Valid @ModelAttribute("userDto") UserAccountDto userDto,
                           BindingResult bindingResult) {

        System.out.println(bindingResult);

        Optional<UserAccount> acc = repo.findById(userDto.id());

        if (acc.isEmpty()) {
            return "redirect:/jpa";
        }
        // TODO check that password and confirmPassword are the same


        // TODO verify email doesn't already exist]


        // other validations can be added as well

        if (bindingResult.hasErrors()) {
            //bindingResult.reject("something went wrong");
            model.addAttribute("adding", false);
            return "user-account-form";
        } else {
            // save the changes
            UserAccount account = acc.get();

            account.setName(userDto.name());
            account.setEmail(userDto.email());

            account.setPasswordHash(
                    passwordEncoder.encode(userDto.password()));

            account.setCreatedAt(Instant.now());
            repo.save(account);

            return "redirect:/jpa";
        }
        //System.out.println(userDto);
        /*
        Optional<UserAccount> acc = repo.findById(userDto.id());

        if (acc.isEmpty()) {
            return "redirect:/jpa";
        } else {
            UserAccount account = acc.get();

            account.setName(userDto.name());
            account.setEmail(userDto.email());

            account.setPasswordHash(
                    passwordEncoder.encode(userDto.password()));

            account.setCreatedAt(Instant.now());

            repo.save(account);


        }

        model.addAttribute("title", "Edited");
        model.addAttribute("message", "saved");
        return "show_message";

         */
    }
}
