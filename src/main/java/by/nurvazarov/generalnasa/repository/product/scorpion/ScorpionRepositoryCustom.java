package by.nurvazarov.generalnasa.repository.product.scorpion;

import by.nurvazarov.generalnasa.model.product.scorpion.Scorpion;

import java.util.List;

public interface ScorpionRepositoryCustom {

    List<Scorpion> searchScorpion(String searchString);
}
