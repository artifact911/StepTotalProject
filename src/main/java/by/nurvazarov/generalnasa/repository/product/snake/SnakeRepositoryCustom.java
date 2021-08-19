package by.nurvazarov.generalnasa.repository.product.snake;

import by.nurvazarov.generalnasa.model.product.snake.Snake;

import java.util.List;

public interface SnakeRepositoryCustom {

    List<Snake> searchSnake(String searchString);
}
