package pl.pja.sri.domain;

import pl.pja.sri.domain.model.User;

import java.util.List;

public interface UserRepoPort {

    User findUserById(Long id);

    List<User> getAllUser();

    void addUser(User user);

    void updateUser(Long id, User user);
}
