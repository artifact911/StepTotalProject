package by.nurvazarov.generalnasa.repository.product.lizard;

import by.nurvazarov.generalnasa.model.product.lizard.Lizard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LizardRepository extends JpaRepository<Lizard, Long>, LizardRepositoryCustom {

    @Query("SELECT e.image FROM Lizard e WHERE e.pid = :pid")
    byte[] getImg(@Param("pid") Long pid);
}
