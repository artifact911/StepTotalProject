package by.nurvazarov.generalnasa.repository.product.snake;

import by.nurvazarov.generalnasa.model.product.snake.Snake;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SnakeRepository extends JpaRepository<Snake, Long>, SnakeRepositoryCustom{

    @Query("SELECT e.image FROM Snake e WHERE e.pid = :pid")
    byte[] getImg(@Param("pid") Long pid);
}
