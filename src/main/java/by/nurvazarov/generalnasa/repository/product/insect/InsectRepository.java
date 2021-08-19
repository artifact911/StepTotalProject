package by.nurvazarov.generalnasa.repository.product.insect;

import by.nurvazarov.generalnasa.model.product.insect.Insect;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InsectRepository extends JpaRepository<Insect, Long>, InsectRepositoryCustom {
}
