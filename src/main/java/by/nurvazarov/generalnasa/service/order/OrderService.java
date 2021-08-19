package by.nurvazarov.generalnasa.service.order;

import by.nurvazarov.generalnasa.model.order.OrderByUser;

import java.util.List;

public interface OrderService {

    List<OrderByUser> getAllOrders();

    OrderByUser createNewOrder(OrderByUser orderByUser);

    OrderByUser getOrderByPid(Long pid);

    OrderByUser updateOrder(OrderByUser orderByUser);

    void deleteOrderByPid(Long pid);

    List<OrderByUser> getSearchOrder(String searchString);


}
