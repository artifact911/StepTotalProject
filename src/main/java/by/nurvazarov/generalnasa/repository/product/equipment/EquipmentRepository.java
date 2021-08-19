package by.nurvazarov.generalnasa.repository.product.equipment;

import by.nurvazarov.generalnasa.model.product.equipment.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EquipmentRepository extends JpaRepository<Equipment, Long>, EquipmentRepositoryCustom {
}
