package by.nurvazarov.generalnasa.service.product.lizard;

import by.nurvazarov.generalnasa.model.product.lizard.Lizard;
import by.nurvazarov.generalnasa.repository.product.lizard.LizardRepository;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class LizardServiceImpl implements LizardService {

    private final LizardRepository lizardRepository;

    @Autowired
    public LizardServiceImpl(LizardRepository lizardRepository) {
        this.lizardRepository = lizardRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Lizard> getLizards() {
        return lizardRepository.findAll();
    }

    @Override
    @Transactional
    public Lizard createNewLizard(Lizard lizard, MultipartFile maintenanceFile) {
        if (!maintenanceFile.isEmpty()) {
            try {
                byte[] fileBytes = maintenanceFile.getBytes();
                lizard.setImage(fileBytes);
            } catch (IOException e) {
                throw new ServiceException("Ошибка добавления картинки");
            }
        }
        return lizardRepository.save(lizard);
    }

    @Override
    @Transactional(readOnly = true)
    public Lizard getLizardByPid(Long pid) {
        return lizardRepository.getById(pid);
    }

    @Override
    @Transactional
    public Lizard updateLizard(Lizard lizard) {
        return lizardRepository.save(lizard);
    }

    @Override
    @Transactional
    public void deleteLizardById(Long pid) {
        lizardRepository.deleteById(pid);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Lizard> getSearchLizard(String searchString) {
        return lizardRepository.searchLizard(searchString);
    }
}
