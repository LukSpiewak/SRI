package pl.pja.sri.domain;

import org.springframework.stereotype.Component;
import pl.pja.sri.domain.model.User;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class UserDomainService {

    private final UserRepoPort userRepo;

    public UserDomainService(UserRepoPort userRepo) {
        this.userRepo = userRepo;
    }

    public User findUserById(Long id) {
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return userRepo.findUserById(id);
    }

    public List<User> getAllUser() {
        return userRepo.getAllUser();
    }

    public void addUser(User user) {
        userRepo.addUser(user);
    }

    public void updateUser(Long id, User user) {
        user.setUpdateDate(LocalDateTime.now());
        userRepo.updateUser(id, user);
    }
}
