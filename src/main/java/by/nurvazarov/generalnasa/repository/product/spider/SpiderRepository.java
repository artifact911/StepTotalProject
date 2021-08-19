package by.nurvazarov.generalnasa.repository.product.spider;

import by.nurvazarov.generalnasa.model.product.spider.Spider;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpiderRepository extends JpaRepository<Spider, Long>, SpiderRepositoryCustom {
}
