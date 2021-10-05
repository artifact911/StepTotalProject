package by.nurvazarov.generalnasa.controller;

import by.nurvazarov.generalnasa.service.product.equipment.EquipmentService;
import by.nurvazarov.generalnasa.service.product.insect.InsectService;
import by.nurvazarov.generalnasa.service.product.lizard.LizardService;
import by.nurvazarov.generalnasa.service.product.scorpion.ScorpionService;
import by.nurvazarov.generalnasa.service.product.snake.SnakeService;
import by.nurvazarov.generalnasa.service.product.spider.SpiderService;
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
    private final EquipmentService equipmentService;
    private final InsectService insectService;
    private final SpiderService spiderService;
    private final LizardService lizardService;
    private final ScorpionService scorpionService;
    private final SnakeService snakeService;

    @Autowired
    public NavigationController(UserService userService, EquipmentService equipmentService, InsectService insectService, SpiderService spiderService, LizardService lizardService, ScorpionService scorpionService, SnakeService snakeService) {
        this.userService = userService;
        this.equipmentService = equipmentService;
        this.insectService = insectService;
        this.spiderService = spiderService;
        this.lizardService = lizardService;
        this.scorpionService = scorpionService;
        this.snakeService = snakeService;
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
        return "orders/order";
    }

    @GetMapping("/index")
    @PreAuthorize("hasAuthority('CUSTOMER')")
    public String getBooksForUser(Model model) {
        model.addAttribute("equipments", equipmentService.getEquipments());
        model.addAttribute("insects", insectService.getInsects());
        model.addAttribute("spiders", spiderService.getSpiders());
        model.addAttribute("lizards", lizardService.getLizards());
        model.addAttribute("scorpions", scorpionService.getScorpions());
        model.addAttribute("snakes", snakeService.getSnakes());

        return "index";
    }
}
