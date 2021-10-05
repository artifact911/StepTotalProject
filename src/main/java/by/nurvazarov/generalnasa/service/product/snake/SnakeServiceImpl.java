package by.nurvazarov.generalnasa.service.product.snake;

import by.nurvazarov.generalnasa.model.product.snake.Snake;
import by.nurvazarov.generalnasa.repository.product.snake.SnakeRepository;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class SnakeServiceImpl implements SnakeService {

    private final SnakeRepository snakeRepository;

    @Autowired
    public SnakeServiceImpl(SnakeRepository snakeRepository) {
        this.snakeRepository = snakeRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Snake> getSnakes() {
        return snakeRepository.findAll();
    }

    @Override
    @Transactional
    public Snake createNewSnake(Snake snake, MultipartFile maintenanceFile) throws ServiceException {
        if (!maintenanceFile.isEmpty()) {
            try {
                byte[] fileBytes = maintenanceFile.getBytes();
                snake.setImage(fileBytes);
            } catch (IOException e) {
                throw new ServiceException("Ошибка добавления картинки");
            }
        }
        return snakeRepository.save(snake);
    }

    @Override
    @Transactional(readOnly = true)
    public Snake getSnakeByPid(Long pid) {
        return snakeRepository.getById(pid);
    }

    @Override
    @Transactional
    public Snake updateSnake(Snake snake) {
        return snakeRepository.save(snake);
    }

    @Override
    @Transactional
    public void deleteSnakeById(Long pid) {
        snakeRepository.deleteById(pid);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Snake> getSearchSnake(String searchString) {
        return snakeRepository.searchSnake(searchString);
    }

    @Override
    @Transactional(readOnly = true)
    public byte[] getImg(Long pid) {
        return snakeRepository.getImg(pid);
    }
}
