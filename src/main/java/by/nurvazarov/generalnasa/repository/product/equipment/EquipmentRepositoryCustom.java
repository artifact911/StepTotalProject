package by.nurvazarov.generalnasa.repository.product.equipment;

import by.nurvazarov.generalnasa.model.product.equipment.Equipment;

import java.util.List;

public interface EquipmentRepositoryCustom {

    List<Equipment> searchEquipment(String searchString);
}
