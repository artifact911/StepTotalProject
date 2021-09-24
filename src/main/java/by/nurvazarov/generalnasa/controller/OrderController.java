package by.nurvazarov.generalnasa.controller;

import by.nurvazarov.generalnasa.model.order.OrderByUser;
import by.nurvazarov.generalnasa.service.order.OrderService;
import by.nurvazarov.generalnasa.service.product.equipment.EquipmentService;
import by.nurvazarov.generalnasa.service.product.insect.InsectService;
import by.nurvazarov.generalnasa.service.product.lizard.LizardService;
import by.nurvazarov.generalnasa.service.product.scorpion.ScorpionService;
import by.nurvazarov.generalnasa.service.product.snake.SnakeService;
import by.nurvazarov.generalnasa.service.product.spider.SpiderService;
import by.nurvazarov.generalnasa.service.user.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrderController {

    private static final String ORDER_TABLE = "orders/orderTable :: order_list";
    private static final String ERROR_ALERT = "fragments/alert :: alert";
    private static final String EDIT_ORDER_MODAL = "orders/modal/editOrder";
    private static final String ADD_ORDER_MODAL = "orders/modal/addOrder";


    private final OrderService orderService;
    private final UserService userService;
    private final EquipmentService equipmentService;
    private final InsectService insectService;
    private final LizardService lizardService;
    private final ScorpionService scorpionService;
    private final SnakeService snakeService;
    private final SpiderService spiderService;

    @Autowired
    public OrderController(OrderService orderService, UserService userService,
                           EquipmentService equipmentService, InsectService insectService,
                           LizardService lizardService, ScorpionService scorpionService,
                           SnakeService snakeService, SpiderService spiderService) {
        this.orderService = orderService;
        this.userService = userService;
        this.equipmentService = equipmentService;
        this.insectService = insectService;
        this.lizardService = lizardService;
        this.scorpionService = scorpionService;
        this.snakeService = snakeService;
        this.spiderService = spiderService;
    }


    @GetMapping("order")
    public String getAll(Model model) {
        List<OrderByUser> orders = orderService.getAllOrders();
        model.addAttribute("orders", orders);
        return "orders/order";
    }

    @GetMapping("/orders/addOrder")
    public String addOrder(Model model) {
        try {
            addAttributeForOrder(model);
            model.addAttribute("order", new OrderByUser());
            model.addAttribute("message", "Успешно добавлено");
            model.addAttribute("alertClass", "alert-success");
            return ADD_ORDER_MODAL;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            model.addAttribute("message", "Ошибка добавления");
            model.addAttribute("alertClass", "alert-danger");
            return ADD_ORDER_MODAL;
        }
    }

    @PostMapping("/saveOrder")
    public String saveOrder(OrderByUser order, Model model) {

        try {
            orderService.createNewOrder(order);
            model.addAttribute("orders", orderService.getAllOrders());
            model.addAttribute("message", "Успешно добавлено");
            model.addAttribute("alertClass", "alert-success");
            return ORDER_TABLE;
        } catch (Exception e) {
            model.addAttribute("orders", orderService.getAllOrders());
            model.addAttribute("message", "Ошибка добавления");
            model.addAttribute("alertClass", "alert-danger");
            return ORDER_TABLE;
        }
    }


    @GetMapping("/orders/edit")
    public String editOrder(Long pid, Model model) {
        try {
            addAttributeForOrder(model);
            OrderByUser order = orderService.getOrderByPid(pid);
            model.addAttribute("order", order);
            model.addAttribute("message", "Успешно изменено");
            model.addAttribute("alertClass", "alert-success");
            return EDIT_ORDER_MODAL;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            model.addAttribute("message", "Ошибка редактирования");
            model.addAttribute("alertClass", "alert-danger");
            return EDIT_ORDER_MODAL;
        }
    }

    @PostMapping("/orders/updateOrder")
    public String updateOrder(OrderByUser order, Model model) {
        try {
            orderService.updateOrder(order);
            model.addAttribute("orders", orderService.getAllOrders());
            model.addAttribute("message", "Успешно изменено");
            model.addAttribute("alertClass", "alert-success");
            return ORDER_TABLE;
        } catch (Exception e) {
            model.addAttribute("orders", orderService.getAllOrders());
            model.addAttribute("message", "Ошибка редактирования");
            model.addAttribute("alertClass", "alert-danger");
            return ORDER_TABLE;
        }
    }

    @RequestMapping(value = "/orders/delete", method = {RequestMethod.DELETE, RequestMethod.GET})
    public String deleteOrder(Long pid, Model model) {
        try {
            orderService.deleteOrderByPid(pid);
            model.addAttribute("orders", orderService.getAllOrders());
            model.addAttribute("message", "Успешно удалено");
            model.addAttribute("alertClass", "alert-success");
            return ORDER_TABLE;
        } catch (Exception e) {
            model.addAttribute("orders", orderService.getAllOrders());
            model.addAttribute("message", "Ошибка удаления");
            model.addAttribute("alertClass", "alert-danger");
            return ORDER_TABLE;
        }
    }

    @GetMapping("/orders/filter")
    public String filterOrder(String filterText, Model model) {
        List<OrderByUser> filterOrder;
        try {
            if (!StringUtils.isBlank(filterText)) {
                filterOrder = orderService.getSearchOrder(filterText);
            } else {
                filterOrder = orderService.getAllOrders();
            }
            model.addAttribute("orders", filterOrder);
            return ORDER_TABLE;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            model.addAttribute("orders", orderService.getAllOrders());
            model.addAttribute("message", "Произошла ошибка");
            model.addAttribute("alertClass", "alert-danger");
            return ORDER_TABLE;
        }
    }

    private void addAttributeForOrder(Model model) {
        model.addAttribute("predefinedUsers", userService.getUsers());
        model.addAttribute("predefinedEquipments", equipmentService.getEquipments());
        model.addAttribute("predefinedInsects", insectService.getInsects());
        model.addAttribute("predefinedLizards", lizardService.getLizards());
        model.addAttribute("predefinedScorpions", scorpionService.getScorpions());
        model.addAttribute("predefinedSnakes", snakeService.getSnakes());
        model.addAttribute("predefinedSpiders", spiderService.getSpiders());

    }

}
