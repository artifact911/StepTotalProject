package by.nurvazarov.generalnasa.repository.product.scorpion;

import by.nurvazarov.generalnasa.model.product.scorpion.Scorpion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScorpionRepository extends JpaRepository<Scorpion, Long>, ScorpionRepositoryCustom {
}
