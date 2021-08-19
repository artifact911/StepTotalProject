package by.nurvazarov.generalnasa.repository.product.spider;

import by.nurvazarov.generalnasa.model.product.spider.Spider;
import by.nurvazarov.generalnasa.model.user.User;

import java.util.List;

public interface SpiderRepositoryCustom {

    List<Spider> searchSpider(String searchString);
}
