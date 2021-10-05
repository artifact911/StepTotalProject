package by.nurvazarov.generalnasa.repository.product.spider;

import by.nurvazarov.generalnasa.model.product.spider.Spider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SpiderRepository extends JpaRepository<Spider, Long>, SpiderRepositoryCustom {

    @Query("SELECT e.image FROM Spider e WHERE e.pid = :pid")
    byte[] getImg(@Param("pid") Long pid);
}
