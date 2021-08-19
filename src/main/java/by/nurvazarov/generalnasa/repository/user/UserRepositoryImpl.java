package by.nurvazarov.generalnasa.repository.user;

import by.nurvazarov.generalnasa.model.user.User;
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
public class UserRepositoryImpl implements UserRepositoryCustom {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<User> searchUser(String searchString) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<User> cq = cb.createQuery(User.class);
        Root<User> root = cq.from(User.class);

        List<Predicate> predicates = new ArrayList<>();

        if (searchString != null) {

            predicates.add(cb.like(root.get("login"), "%" + searchString + "%"));
            predicates.add(cb.like(root.get("firstName"), "%" + searchString + "%"));
            predicates.add(cb.like(root.get("middleName"), "%" + searchString + "%"));
            predicates.add(cb.like(root.get("lastName"), "%" + searchString + "%"));
            predicates.add(cb.like(root.join("roles").get("pid"), "%" + searchString + "%"));

            cq.distinct(true).where(cb.or(predicates.toArray(new Predicate[0])));
        }

        return em.createQuery(cq).getResultList();
    }
}
