package by.nurvazarov.generalnasa.repository.order;

import by.nurvazarov.generalnasa.model.order.OrderByUser;

import java.util.List;

public interface OrderRepositoryCustom {

    List<OrderByUser> searchOrder(String searchString);
}
