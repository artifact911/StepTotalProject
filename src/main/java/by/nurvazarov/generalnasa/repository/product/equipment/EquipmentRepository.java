package by.nurvazarov.generalnasa.repository.product.equipment;

import by.nurvazarov.generalnasa.model.product.equipment.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EquipmentRepository extends JpaRepository<Equipment, Long>, EquipmentRepositoryCustom {

    @Query("SELECT e.image FROM Equipment e WHERE e.pid = :pid")
    byte[] getImg(@Param("pid") Long pid);
}
