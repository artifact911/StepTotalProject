package by.nurvazarov.generalnasa.repository.order;

import by.nurvazarov.generalnasa.model.order.OrderByUser;
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
public class OrderRepositoryImpl implements OrderRepositoryCustom {

    @PersistenceContext
    EntityManager em;

    @Override
    public List<OrderByUser> searchOrder(String searchString) {

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<OrderByUser> cq = cb.createQuery(OrderByUser.class);
        Root<OrderByUser> root = cq.from(OrderByUser.class);

        List<Predicate> predicates = new ArrayList<>();

        predicates.add(cb.like(root.get("orderStatus"), "%" + searchString + "%"));
        predicates.add(cb.like(root.join("user").get("lastName"), "%" + searchString + "%"));

        cq.distinct(true).where(cb.or(predicates.toArray(new Predicate[0])));

        return em.createQuery(cq).getResultList();
    }
}
