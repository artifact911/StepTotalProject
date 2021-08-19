package by.nurvazarov.generalnasa.repository.order;

import by.nurvazarov.generalnasa.model.order.OrderByUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderByUser, Long>, OrderRepositoryCustom {
}
