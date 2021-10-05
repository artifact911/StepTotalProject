package by.nurvazarov.generalnasa.repository.product.scorpion;

import by.nurvazarov.generalnasa.model.product.scorpion.Scorpion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ScorpionRepository extends JpaRepository<Scorpion, Long>, ScorpionRepositoryCustom {

    @Query("SELECT e.image FROM Scorpion e WHERE e.pid = :pid")
    byte[] getImg(@Param("pid") Long pid);
}
