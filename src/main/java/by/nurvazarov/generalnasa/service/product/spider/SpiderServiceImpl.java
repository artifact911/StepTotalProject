package by.nurvazarov.generalnasa.service.product.spider;

import by.nurvazarov.generalnasa.model.product.spider.Spider;
import by.nurvazarov.generalnasa.repository.product.spider.SpiderRepository;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class SpiderServiceImpl implements SpiderService{

    private final SpiderRepository spiderRepository;

    @Autowired
    public SpiderServiceImpl(SpiderRepository spiderRepository) {
        this.spiderRepository = spiderRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Spider> getSpiders() {
        return spiderRepository.findAll();
    }

    @Override
    @Transactional
    public Spider createNewSpider(Spider spider, MultipartFile maintenanceFile) throws ServiceException {
        if (!maintenanceFile.isEmpty()) {
            try {
                byte[] fileBytes = maintenanceFile.getBytes();
                spider.setImage(fileBytes);
            } catch (IOException e) {
                throw new ServiceException("Ошибка добавления картинки");
            }
        }
        return spiderRepository.save(spider);
    }

    @Override
    @Transactional(readOnly = true)
    public Spider getSpiderByPid(Long pid) {
        return spiderRepository.getById(pid);
    }

    @Override
    @Transactional
    public Spider updateSpider(Spider spider) {
        return spiderRepository.save(spider);
    }

    @Override
    @Transactional
    public void deleteSpiderById(Long pid) {
        spiderRepository.deleteById(pid);

    }

    @Override
    @Transactional(readOnly = true)
    public List<Spider> getSearchSpider(String searchString) {
        return spiderRepository.searchSpider(searchString);
    }
}
