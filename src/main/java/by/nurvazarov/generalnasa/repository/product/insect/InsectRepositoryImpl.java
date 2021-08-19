package by.nurvazarov.generalnasa.repository.product.insect;

import by.nurvazarov.generalnasa.model.product.insect.Insect;
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
public class InsectRepositoryImpl implements InsectRepositoryCustom{

    @PersistenceContext
    EntityManager em;

    @Override
    public List<Insect> searchInsect(String searchString) {

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Insect> cq = cb.createQuery(Insect.class);
        Root<Insect> root = cq.from(Insect.class);

        List<Predicate> predicates = new ArrayList<>();

        predicates.add(cb.like(root.get("name"), "%" + searchString + "%"));

        cq.distinct(true).where(cb.or(predicates.toArray(new Predicate[0])));

        return em.createQuery(cq).getResultList();
    }
}
