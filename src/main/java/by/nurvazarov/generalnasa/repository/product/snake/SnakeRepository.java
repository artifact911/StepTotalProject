package by.nurvazarov.generalnasa.repository.product.snake;

import by.nurvazarov.generalnasa.model.product.snake.Snake;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SnakeRepository extends JpaRepository<Snake, Long>, SnakeRepositoryCustom{
}
