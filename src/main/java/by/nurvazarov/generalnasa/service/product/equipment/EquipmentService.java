package by.nurvazarov.generalnasa.service.product.equipment;

import by.nurvazarov.generalnasa.model.product.equipment.Equipment;
import by.nurvazarov.generalnasa.model.user.Role;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

public interface EquipmentService {

    List<Equipment> getEquipments();

    Equipment createNewEquipment(Equipment equipment, MultipartFile maintenanceFile);

    Equipment getEquipmentByPid(Long pid);

    Equipment updateEquipment(Equipment equipment);

    void deleteEquipmentById(Long pid);

    List<Equipment> getSearchEquipment(String searchString);

    byte[] getImg(Long pid);
}
