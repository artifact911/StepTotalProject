package by.nurvazarov.generalnasa.repository.user;

import by.nurvazarov.generalnasa.model.user.User;

import java.util.List;

public interface UserRepositoryCustom {

    List<User> searchUser(String searchString);
}
