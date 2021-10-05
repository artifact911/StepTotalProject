package by.nurvazarov.generalnasa.repository.product.insect;

import by.nurvazarov.generalnasa.model.product.insect.Insect;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface InsectRepository extends JpaRepository<Insect, Long>, InsectRepositoryCustom {

    @Query("SELECT e.image FROM Insect e WHERE e.pid = :pid")
    byte[] getImg(@Param("pid") Long pid);
}
