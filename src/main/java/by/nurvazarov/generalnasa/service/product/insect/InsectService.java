package by.nurvazarov.generalnasa.service.product.insect;

import by.nurvazarov.generalnasa.model.product.insect.Insect;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface InsectService {

    List<Insect> getInsects();

    Insect createNewInsect(Insect insect, MultipartFile maintenanceFile);

    Insect getInsectByPid(Long pid);

    Insect updateInsect(Insect insect);

    void deleteInsectById(Long pid);

    List<Insect> getSearchInsect(String searchString);

    byte[] getImg(Long pid);
}
