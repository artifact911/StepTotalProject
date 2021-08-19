package by.nurvazarov.generalnasa.repository.user;

import by.nurvazarov.generalnasa.model.order.OrderByUser;
import by.nurvazarov.generalnasa.model.user.Role;
import by.nurvazarov.generalnasa.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>, UserRepositoryCustom {

    Optional<User> findByLogin(String login);

    @Query("select r from Role r")
    List<Role> getAllRolesForUser();

    @Query("SELECT distinct u FROM User u JOIN FETCH u.orderByUserList o WHERE u.pid = :pid")
    List<OrderByUser> getAllOrdersOfUser(@Param("pid") Long pid);
}
