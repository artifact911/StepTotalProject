package by.nurvazarov.generalnasa.service.product.insect;

import by.nurvazarov.generalnasa.model.product.insect.Insect;
import by.nurvazarov.generalnasa.repository.product.insect.InsectRepository;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class InsectServiceImpl implements InsectService {

    private final InsectRepository insectRepository;

    @Autowired
    public InsectServiceImpl(InsectRepository insectRepository) {
        this.insectRepository = insectRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Insect> getInsects() {
        return insectRepository.findAll();
    }

    @Override
    @Transactional
    public Insect createNewInsect(Insect insect, MultipartFile maintenanceFile) {
        if (!maintenanceFile.isEmpty()) {
            try {
                byte[] fileBytes = maintenanceFile.getBytes();
                insect.setImage(fileBytes);
            } catch (IOException e) {
                throw new ServiceException("Ошибка добавления картинки");
            }
        }
        return insectRepository.save(insect);
    }

    @Override
    @Transactional(readOnly = true)
    public Insect getInsectByPid(Long pid) {
        return insectRepository.getById(pid);
    }

    @Override
    @Transactional
    public Insect updateInsect(Insect insect) {
        return insectRepository.save(insect);
    }

    @Override
    @Transactional
    public void deleteInsectById(Long pid) {
        insectRepository.deleteById(pid);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Insect> getSearchInsect(String searchString) {
        return insectRepository.searchInsect(searchString);
    }
}
