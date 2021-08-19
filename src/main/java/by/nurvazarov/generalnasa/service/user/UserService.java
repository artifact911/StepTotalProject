package by.nurvazarov.generalnasa.service.user;

import by.nurvazarov.generalnasa.model.order.OrderByUser;
import by.nurvazarov.generalnasa.model.user.Role;
import by.nurvazarov.generalnasa.model.user.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> getUsers();

    List<Role> getAllRoles();

    List<OrderByUser> getAllOrders(Long pid);

    User createNewUser(User user);

    User getUserByPid(Long pid);

    User updateUser(User user);

    Optional<User> getUserByLogin(String login);

    void deleteUserById(Long pid);

    List<User> getSearchUser(String searchString);
}
