package by.nurvazarov.generalnasa.service.product.snake;

import by.nurvazarov.generalnasa.model.product.snake.Snake;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface SnakeService {

    List<Snake> getSnakes();

    Snake createNewSnake(Snake snake, MultipartFile maintenanceFile);

    Snake getSnakeByPid(Long pid);

    Snake updateSnake(Snake snake);

    void deleteSnakeById(Long pid);

    List<Snake> getSearchSnake(String searchString);

    byte[] getImg(Long pid);
}
