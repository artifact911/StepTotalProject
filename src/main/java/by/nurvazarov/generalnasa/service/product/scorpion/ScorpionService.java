package by.nurvazarov.generalnasa.service.product.scorpion;

import by.nurvazarov.generalnasa.model.product.scorpion.Scorpion;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ScorpionService {

    List<Scorpion> getScorpions();

    Scorpion createNewScorpion(Scorpion scorpion, MultipartFile maintenanceFile);

    Scorpion getScorpionByPid(Long pid);

    Scorpion updateScorpion(Scorpion scorpion);

    void deleteScorpionById(Long pid);

    List<Scorpion> getSearchScorpion(String searchString);

    byte[] getImg(Long pid);
}
