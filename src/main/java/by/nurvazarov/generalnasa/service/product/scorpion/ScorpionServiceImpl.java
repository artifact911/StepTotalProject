package by.nurvazarov.generalnasa.service.product.scorpion;


import by.nurvazarov.generalnasa.model.product.scorpion.Scorpion;
import by.nurvazarov.generalnasa.repository.product.scorpion.ScorpionRepository;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ScorpionServiceImpl implements ScorpionService {

    private final ScorpionRepository scorpionRepository;

    @Autowired
    public ScorpionServiceImpl(ScorpionRepository scorpionRepository) {
        this.scorpionRepository = scorpionRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Scorpion> getScorpions() {
        return scorpionRepository.findAll();
    }

    @Override
    @Transactional
    public Scorpion createNewScorpion(Scorpion scorpion, MultipartFile maintenanceFile) throws ServiceException {
        if (!maintenanceFile.isEmpty()) {
            try {
                byte[] fileBytes = maintenanceFile.getBytes();
                scorpion.setImage(fileBytes);
            } catch (IOException e) {
                throw new ServiceException("Ошибка добавления картинки");
            }
        }
        return scorpionRepository.save(scorpion);
    }

    @Override
    @Transactional(readOnly = true)
    public Scorpion getScorpionByPid(Long pid) {
        return scorpionRepository.getById(pid);
    }

    @Override
    @Transactional
    public Scorpion updateScorpion(Scorpion scorpion) {
        return scorpionRepository.save(scorpion);
    }

    @Override
    @Transactional
    public void deleteScorpionById(Long pid) {
        scorpionRepository.deleteById(pid);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Scorpion> getSearchScorpion(String searchString) {
        return scorpionRepository.searchScorpion(searchString);
    }

    @Override
    @Transactional(readOnly = true)
    public byte[] getImg(Long pid) {
        return scorpionRepository.getImg(pid);
    }
}
