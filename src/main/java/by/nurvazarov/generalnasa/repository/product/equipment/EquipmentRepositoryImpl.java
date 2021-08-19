package by.nurvazarov.generalnasa.repository.product.equipment;

import by.nurvazarov.generalnasa.model.product.equipment.Equipment;
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
public class EquipmentRepositoryImpl implements EquipmentRepositoryCustom {

    @PersistenceContext
    EntityManager em;

    @Override
    public List<Equipment> searchEquipment(String searchString) {

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Equipment> cq = cb.createQuery(Equipment.class);
        Root<Equipment> root = cq.from(Equipment.class);

        List<Predicate> predicates = new ArrayList<>();

        predicates.add(cb.like(root.get("name"), "%" + searchString + "%"));

        cq.distinct(true).where(cb.or(predicates.toArray(new Predicate[0])));

        return em.createQuery(cq).getResultList();
    }
}
