package by.nurvazarov.generalnasa.repository.product.snake;

import by.nurvazarov.generalnasa.model.product.snake.Snake;
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
public class SnakeRepositoryImpl implements SnakeRepositoryCustom{

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Snake> searchSnake(String searchString) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Snake> cq = cb.createQuery(Snake.class);
        Root<Snake> root = cq.from(Snake.class);

        List<Predicate> predicates = new ArrayList<>();

        if (searchString != null) {

            predicates.add(cb.like(root.get("name"), "%" + searchString + "%"));
            predicates.add(cb.like(root.get("danger"), "%" + searchString + "%"));
            predicates.add(cb.like(root.get("sex"), "%" + searchString + "%"));

            cq.distinct(true).where(cb.or(predicates.toArray(new Predicate[0])));
        }

        return em.createQuery(cq).getResultList();
    }
}
