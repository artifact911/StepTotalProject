package by.nurvazarov.generalnasa.repository.product.lizard;

import by.nurvazarov.generalnasa.model.product.lizard.Lizard;

import java.util.List;

public interface LizardRepositoryCustom {

    List<Lizard> searchLizard(String searchString);
}
