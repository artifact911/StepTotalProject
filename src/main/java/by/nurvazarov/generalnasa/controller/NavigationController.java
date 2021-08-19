package by.nurvazarov.generalnasa.controller;

import by.nurvazarov.generalnasa.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class NavigationController {

    private final UserService userService;

    @Autowired
    public NavigationController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = {"/", "/users"}, method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ADMIN')")
    public String users(Model model) {
        model.addAttribute("users", userService.getUsers());
        return "users/user";
    }

    @GetMapping("/references")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'ASSISTANT')")
    public String references() {
        return "references/references";
    }

    @GetMapping("/orders")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'ASSISTANT')")
    public String statistics() {
        return "orders";
    }

    @GetMapping("/index")
    @PreAuthorize("hasAuthority('CUSTOMER')")
    public String getBooksForUser(Model model) {
       // model.addAttribute("books", bookService.getBooks());

        return "index";
    }
}
