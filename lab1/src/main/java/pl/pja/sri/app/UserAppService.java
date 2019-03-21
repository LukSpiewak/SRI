package pl.pja.sri.app;

import org.springframework.stereotype.Service;
import pl.pja.sri.domain.UserDomainService;
import pl.pja.sri.domain.model.User;
import pl.pja.sri.infrastructure.model.UserDTO;

import java.net.URI;
import java.util.List;

@Service
public class UserAppService {

    private final UserDomainService userService;

    public UserAppService(UserDomainService userService) {
        this.userService = userService;
    }

    public UserDTO findUserById(Long id) {
        User domainUser = userService.findUserById(id);
        return UserMapper.convert(domainUser);
    }

    public List<UserDTO> getAllUser() {
        List<User> users = userService.getAllUser();
        return UserMapper.convert(users);
    }

    public URI addUser(UserDTO dto) {
        User user = UserMapper.convert(dto);
        userService.addUser(user);
        return createURI(user.getId());
    }

    private URI createURI(Long id) {
        return URI.create("localhost:8080/rest/api/user/" + id);
    }

    public void updateUser(Long id, UserDTO dto) {
        User user = UserMapper.convert(dto);
        userService.updateUser(id, user);
    }
}
