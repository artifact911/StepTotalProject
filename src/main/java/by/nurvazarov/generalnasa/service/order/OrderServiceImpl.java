package by.nurvazarov.generalnasa.service.order;

import by.nurvazarov.generalnasa.model.order.OrderByUser;
import by.nurvazarov.generalnasa.repository.order.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderByUser> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    @Transactional
    public OrderByUser createNewOrder(OrderByUser orderByUser) {
        return orderRepository.save(orderByUser);
    }

    @Override
    @Transactional(readOnly = true)
    public OrderByUser getOrderByPid(Long pid) {
        return orderRepository.getById(pid);
    }

    @Override
    @Transactional
    public OrderByUser updateOrder(OrderByUser orderByUser) {
        return orderRepository.save(orderByUser);
    }

    @Override
    @Transactional
    public void deleteOrderByPid(Long pid) {
        orderRepository.deleteById(pid);
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderByUser> getSearchOrder(String searchString) {
        return orderRepository.searchOrder(searchString);
    }
}
