package by.nurvazarov.generalnasa.repository.product.spider;

import by.nurvazarov.generalnasa.model.product.spider.Spider;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class SpiderRepositoryImpl implements SpiderRepositoryCustom {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Spider> searchSpider(String searchString) {
        Session session = em.unwrap(Session.class);
        Criteria criteria = session.createCriteria(Spider.class);

        Disjunction disjunction = Restrictions.or(

                Restrictions.ilike("name", searchString, MatchMode.ANYWHERE),
                Restrictions.ilike("type", searchString, MatchMode.ANYWHERE),
                Restrictions.ilike("toxic", searchString, MatchMode.ANYWHERE),
                Restrictions.ilike("danger", searchString, MatchMode.ANYWHERE),
                Restrictions.ilike("sex", searchString, MatchMode.ANYWHERE)
        );
        criteria.add(disjunction);
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        return criteria.list();
    }
}
