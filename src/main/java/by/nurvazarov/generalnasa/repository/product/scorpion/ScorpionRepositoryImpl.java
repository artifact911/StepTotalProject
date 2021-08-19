package by.nurvazarov.generalnasa.repository.product.scorpion;

import by.nurvazarov.generalnasa.model.product.scorpion.Scorpion;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ScorpionRepositoryImpl implements ScorpionRepositoryCustom {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Scorpion> searchScorpion(String searchString) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Scorpion> cq = cb.createQuery(Scorpion.class);
        Root<Scorpion> root = cq.from(Scorpion.class);

        List<Predicate> predicates = new ArrayList<>();

        if (searchString != null) {

            predicates.add(cb.like(root.get("name"), "%" + searchString + "%"));
            predicates.add(cb.like(root.get("danger"), "%" + searchString + "%"));
            predicates.add(cb.like(root.get("toxic"), "%" + searchString + "%"));

            cq.distinct(true).where(cb.or(predicates.toArray(new Predicate[0])));
        }

        return em.createQuery(cq).getResultList();
    }
}
