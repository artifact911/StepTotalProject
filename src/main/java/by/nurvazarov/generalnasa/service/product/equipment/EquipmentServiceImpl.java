package by.nurvazarov.generalnasa.service.product.equipment;

import by.nurvazarov.generalnasa.model.product.equipment.Equipment;
import by.nurvazarov.generalnasa.repository.product.equipment.EquipmentRepository;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class EquipmentServiceImpl implements EquipmentService {

    private final EquipmentRepository equipmentRepository;

    @Autowired
    public EquipmentServiceImpl(EquipmentRepository equipmentRepository) {
        this.equipmentRepository = equipmentRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Equipment> getEquipments() {
        return equipmentRepository.findAll();
    }

    @Override
    @Transactional
    public Equipment createNewEquipment(Equipment equipment, MultipartFile maintenanceFile) {
        if (!maintenanceFile.isEmpty()) {
            try {
                byte[] fileBytes = maintenanceFile.getBytes();
                equipment.setImage(fileBytes);
            } catch (IOException e) {
                throw new ServiceException("Ошибка добавления картинки");
            }
        }
        return equipmentRepository.save(equipment);
    }

    @Override
    @Transactional(readOnly = true)
    public Equipment getEquipmentByPid(Long pid) {
        return equipmentRepository.getById(pid);
    }

    @Override
    @Transactional
    public Equipment updateEquipment(Equipment equipment) {
        return equipmentRepository.save(equipment);
    }

    @Override
    @Transactional
    public void deleteEquipmentById(Long pid) {
        equipmentRepository.deleteById(pid);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Equipment> getSearchEquipment(String searchString) {
        return equipmentRepository.searchEquipment(searchString);
    }
}
