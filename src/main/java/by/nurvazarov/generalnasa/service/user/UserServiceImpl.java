package by.nurvazarov.generalnasa.service.user;

import by.nurvazarov.generalnasa.model.order.OrderByUser;
import by.nurvazarov.generalnasa.model.user.Role;
import by.nurvazarov.generalnasa.model.user.User;
import by.nurvazarov.generalnasa.repository.user.UserRepository;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Role> getAllRoles() {
        return userRepository.getAllRolesForUser();
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderByUser> getAllOrders(Long pid) {
        return userRepository.getAllOrdersOfUser(pid);
    }

    @Override
    @Transactional
    public User createNewUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    @Transactional(readOnly = true)
    public User getUserByPid(Long pid) {
        return userRepository.getById(pid);
    }

    @Override
    @Transactional
    public User updateUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> getUserByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    @Override
    @Transactional
    public void deleteUserById(Long pid) {
        userRepository.deleteById(pid);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> getSearchUser(String searchString) {

        if (Strings.isNotEmpty(searchString)) {
            return userRepository.searchUser(searchString);
        } else {
            return userRepository.findAll();
        }
    }
}
