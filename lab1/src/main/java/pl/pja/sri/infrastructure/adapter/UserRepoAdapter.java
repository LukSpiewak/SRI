package pl.pja.sri.infrastructure.adapter;

import org.springframework.stereotype.Repository;
import pl.pja.sri.domain.UserRepoPort;
import pl.pja.sri.domain.model.User;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Repository
public class UserRepoAdapter implements UserRepoPort {

    private final Map<Long, User> inMemory;
    private final AtomicLong idGenerator;

    public UserRepoAdapter() {
        this.idGenerator = new AtomicLong();
        this.inMemory = new HashMap();
        long id = idGenerator.getAndIncrement();
        User tmp = new User();
        tmp.setId(id);
        tmp.setLogin("login");
        tmp.setPassword("password");
        tmp.setPhoneNumber("123123123");
        tmp.setBirthDate(LocalDate.of(2000,2,2));
        tmp.setUpdateDate(LocalDateTime.now());
        inMemory.put(id, tmp);
    }

    @Override
    public List<User> getAllUser() {
        return new ArrayList<>(inMemory.values());
    }

    @Override
    public User findUserById(Long id) {
        User user = inMemory.get(id);
        if (user == null) {
            throw new RuntimeException("User not found");
        }
        return user;
    }

    @Override
    public void addUser(User user) {
        long id = idGenerator.getAndIncrement();
        user.setId(id);
        inMemory.put(id, user);
    }

    @Override
    public void updateUser(Long id, User user) {
        inMemory.put(id, user);
    }
}
