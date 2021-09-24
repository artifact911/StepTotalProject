package by.nurvazarov.generalnasa.repository.order;

import by.nurvazarov.generalnasa.model.order.OrderByUser;
import by.nurvazarov.generalnasa.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<OrderByUser, Long>, OrderRepositoryCustom {

    @Query("select u from User u")
    List<User> getAllUsersForOrder();


}
