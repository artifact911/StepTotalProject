package by.nurvazarov.generalnasa.repository.product.lizard;

import by.nurvazarov.generalnasa.model.product.lizard.Lizard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LizardRepository extends JpaRepository<Lizard, Long>, LizardRepositoryCustom {
}
