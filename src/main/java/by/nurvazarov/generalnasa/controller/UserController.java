package by.nurvazarov.generalnasa.controller;

import by.nurvazarov.generalnasa.model.user.User;
import by.nurvazarov.generalnasa.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    private static final String USER_TABLE = "users/userTable";
    private static final String ERROR_ALERT = "fragments/alert :: alert";
    private static final String EDIT_MODAL = "users/modal/editUser";
    private static final String ADD_MODAL = "users/modal/addUser";


    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/addUser")
    public String addUser(Model model) {

        try {
            model.addAttribute("predefinedRoles", userService.getAllRoles());
            model.addAttribute("user", new User());
            model.addAttribute("message", "Пользователь успешно добавлен");
            model.addAttribute("alertClass", "alert-success");
            return ADD_MODAL;

        } catch (Exception e) {
            System.err.println(e.getMessage());
            return ADD_MODAL;
        }
    }

    @PostMapping("/saveUser")
    public String saveUser(User user, Model model) {

        try {
            userService.createNewUser(user);
            model.addAttribute("users", userService.getUsers());
            model.addAttribute("message", "Пользователь успешно добавлен");
            model.addAttribute("alertClass", "alert-success");
            return USER_TABLE;

        } catch (Exception e) {
            model.addAttribute("users", userService.getUsers());
            model.addAttribute("message", "Ошибка при сохранении пользователя");
            model.addAttribute("alertClass", "alert-danger");
            return USER_TABLE;
        }
    }


    @GetMapping("/edit")
    public String editUser(Long pid, Model model) {

        try {
            model.addAttribute("predefinedRoles", userService.getAllRoles());
            User user = userService.getUserByPid(pid);
            model.addAttribute("user", user);
            model.addAttribute("message", "Пользователь успешно редактирован");
            model.addAttribute("alertClass", "alert-success");
            return EDIT_MODAL;

        } catch (Exception e) {
            System.err.println(e.getMessage());
            return EDIT_MODAL;
        }
    }

    @PostMapping("/updateUser")
    public String updateUser(User user, Model model) {

        try {
            userService.updateUser(user);
            model.addAttribute("users", userService.getUsers());
            model.addAttribute("message", "Пользователь успешно обновлён");
            model.addAttribute("alertClass", "alert-success");
            return USER_TABLE;

        } catch (Exception e) {
            model.addAttribute("users", userService.getUsers());
            model.addAttribute("message", "Ошибка редактирования пользователя");
            model.addAttribute("alertClass", "alert-danger");
            return USER_TABLE;
        }
    }

    @RequestMapping(value = "/delete", method = {RequestMethod.DELETE, RequestMethod.GET})
    public String deleteUserById(Long pid, Model model) {

        try {
            userService.deleteUserById(pid);
            model.addAttribute("users", userService.getUsers());
            model.addAttribute("message", "Пользователь успешно удалён");
            model.addAttribute("alertClass", "alert-success");
            return USER_TABLE;

        } catch (Exception e) {
            model.addAttribute("users", userService.getUsers());
            model.addAttribute("message", "Ошибка удаления пользователя");
            model.addAttribute("alertClass", "alert-danger");
            return USER_TABLE;
        }
    }

    @GetMapping("/filter")
    public String getUsers(String filterText, Model model) {

        try {
            List<User> users = userService.getSearchUser(filterText);
            model.addAttribute("users", users);
            return USER_TABLE;

        } catch (Exception e) {
            model.addAttribute("users", userService.getUsers());
            model.addAttribute("message", "При работе с пользователями произошла ошибка");
            model.addAttribute("alertClass", "alert-danger");
            return USER_TABLE;
        }
    }

    @GetMapping("/checkLogin")
    public String checkLogin(@RequestParam("login") String login, Model model, HttpServletResponse response) {
        if (userService.getUserByLogin(login).isPresent()) {
            response.setStatus(HttpServletResponse.SC_CONFLICT);
            model.addAttribute("message", "Пользователь с таким логином уже существует");
            model.addAttribute("alertClass", "alert-danger");
        }
        return ERROR_ALERT;
    }
}
