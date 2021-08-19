package by.nurvazarov.generalnasa.repository.product.insect;

import by.nurvazarov.generalnasa.model.product.insect.Insect;

import java.util.List;

public interface InsectRepositoryCustom {

    List<Insect> searchInsect(String searchString);
}
