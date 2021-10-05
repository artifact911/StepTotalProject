package by.nurvazarov.generalnasa.service.product.lizard;

import by.nurvazarov.generalnasa.model.product.lizard.Lizard;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface LizardService {

    List<Lizard> getLizards();

    Lizard createNewLizard(Lizard lizard, MultipartFile maintenanceFile);

    Lizard getLizardByPid(Long pid);

    Lizard updateLizard(Lizard lizard);

    void deleteLizardById(Long pid);

    List<Lizard> getSearchLizard(String searchString);

    byte[] getImg(Long pid);
}
