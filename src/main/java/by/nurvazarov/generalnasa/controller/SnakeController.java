package by.nurvazarov.generalnasa.controller;

import by.nurvazarov.generalnasa.model.product.snake.Snake;
import by.nurvazarov.generalnasa.service.product.snake.SnakeService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/references/snakes")
public class SnakeController {

    private static final String SNAKE_TABLE = "references/snake/snakeTable :: snake_list";
    private static final String ERROR_ALERT = "fragments/alert :: alert";
    private static final String EDIT_SNAKE_MODAL = "references/snake/modal/editSnake";
    private static final String ADD_SNAKE_MODAL = "references/snake/modal/addSnake";

    private final SnakeService snakeService;

    @Autowired
    public SnakeController(SnakeService snakeService) {
        this.snakeService = snakeService;
    }

    @GetMapping()
    public String getAll(Model model) {
        List<Snake> snakes = snakeService.getSnakes();
        model.addAttribute("snakes", snakes);
        return "references/snake/snake";
    }

    @GetMapping("/addSnake")
    public String addSnake(Model model) {
        try {
            model.addAttribute("snake", new Snake());
            model.addAttribute("message", "Успешно добавлено");
            model.addAttribute("alertClass", "alert-success");
            return ADD_SNAKE_MODAL;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            model.addAttribute("message", "Ошибка добавления");
            model.addAttribute("alertClass", "alert-danger");
            return ADD_SNAKE_MODAL;
        }
    }

    @RequestMapping(value = "/image", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> getImage(Long pid) throws IOException {
        byte[] imageContent = snakeService.getImg(pid);
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);
        return new ResponseEntity<byte[]>(imageContent, headers, HttpStatus.OK);
    }

    @PostMapping(value = "/saveSnake", consumes = {"multipart/form-data"})
    public String saveSnake(Snake snake, Model model,
                            @RequestParam("maintenanceFile") MultipartFile maintenanceFile) {
        try {
            snakeService.createNewSnake(snake, maintenanceFile);
            model.addAttribute("snakes", snakeService.getSnakes());
            model.addAttribute("message", "Успешно добавлено");
            model.addAttribute("alertClass", "alert-success");
            return SNAKE_TABLE;
        } catch (Exception e) {
            model.addAttribute("snakes", snakeService.getSnakes());
            model.addAttribute("message", "Ошибка добавления");
            model.addAttribute("alertClass", "alert-danger");
            return SNAKE_TABLE;
        }
    }

    @GetMapping("/edit")
    public String editSnake(Long pid, Model model) {
        try {
            Snake snake = snakeService.getSnakeByPid(pid);
            model.addAttribute("snake", snake);
            model.addAttribute("message", "Успешно изменено");
            model.addAttribute("alertClass", "alert-success");
            return EDIT_SNAKE_MODAL;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            model.addAttribute("message", "Ошибка редактирования");
            model.addAttribute("alertClass", "alert-danger");
            return EDIT_SNAKE_MODAL;
        }
    }

    @PostMapping("/updateSnake")
    public String updateSnake(Snake snake, Model model) {
        try {
            snakeService.updateSnake(snake);
            model.addAttribute("snakes", snakeService.getSnakes());
            model.addAttribute("message", "Успешно изменено");
            model.addAttribute("alertClass", "alert-success");
            return SNAKE_TABLE;
        } catch (Exception e) {
            model.addAttribute("snakes", snakeService.getSnakes());
            model.addAttribute("message", "Ошибка редактирования");
            model.addAttribute("alertClass", "alert-danger");
            return SNAKE_TABLE;
        }
    }

    @RequestMapping(value = "/delete", method = {RequestMethod.DELETE, RequestMethod.GET})
    public String deleteSnake(Long pid, Model model) {
        try {
            snakeService.deleteSnakeById(pid);
            model.addAttribute("snakes", snakeService.getSnakes());
            model.addAttribute("message", "Успешно удалено");
            model.addAttribute("alertClass", "alert-success");
            return SNAKE_TABLE;
        } catch (Exception e) {
            model.addAttribute("snakes", snakeService.getSnakes());
            model.addAttribute("message", "Ошибка удаления");
            model.addAttribute("alertClass", "alert-danger");
            return SNAKE_TABLE;
        }
    }

    @GetMapping("/filter")
    public String filterSnake(String filterText, Model model) {
        List<Snake> filterSnake;
        try {
            if (!StringUtils.isBlank(filterText)) {
                filterSnake = snakeService.getSearchSnake(filterText);
            } else {
                filterSnake = snakeService.getSnakes();
            }
            model.addAttribute("snakes", filterSnake);
            return SNAKE_TABLE;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            model.addAttribute("snakes", snakeService.getSnakes());
            model.addAttribute("message", "При работе произошла ошибка");
            model.addAttribute("alertClass", "alert-danger");
            return SNAKE_TABLE;
        }
    }

}
