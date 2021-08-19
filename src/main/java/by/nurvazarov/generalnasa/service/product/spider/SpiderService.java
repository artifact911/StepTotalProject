package by.nurvazarov.generalnasa.service.product.spider;

import by.nurvazarov.generalnasa.model.product.spider.Spider;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface SpiderService {

    List<Spider> getSpiders();

    Spider createNewSpider(Spider spider, MultipartFile maintenanceFile);

    Spider getSpiderByPid(Long pid);

    Spider updateSpider(Spider spider);

    void deleteSpiderById(Long pid);

    List<Spider> getSearchSpider(String searchString);
}
