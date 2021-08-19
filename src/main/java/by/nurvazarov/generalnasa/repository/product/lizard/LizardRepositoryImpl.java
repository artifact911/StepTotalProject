package by.nurvazarov.generalnasa.repository.product.lizard;

import by.nurvazarov.generalnasa.model.product.lizard.Lizard;
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
public class LizardRepositoryImpl implements LizardRepositoryCustom {

    @PersistenceContext
    EntityManager em;

    @Override
    public List<Lizard> searchLizard(String searchString) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Lizard> cq = cb.createQuery(Lizard.class);
        Root<Lizard> root = cq.from(Lizard.class);

        List<Predicate> predicates = new ArrayList<>();

        if (searchString != null) {

            predicates.add(cb.like(root.get("name"), "%" + searchString + "%"));
            predicates.add(cb.like(root.get("sex"), "%" + searchString + "%"));

            cq.distinct(true).where(cb.or(predicates.toArray(new Predicate[0])));
        }
        return em.createQuery(cq).getResultList();
    }
}
