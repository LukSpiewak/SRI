package pl.pja.sri.app;

import pl.pja.sri.domain.model.User;
import pl.pja.sri.infrastructure.model.UserDTO;

import java.util.List;
import java.util.stream.Collectors;

public class UserMapper {

    public static UserDTO convert(User user) {
        UserDTO result = new UserDTO();
        result.setId(user.getId());
        result.setLogin(user.getLogin());
        result.setPassword(user.getPassword());
        result.setPhoneNumber(user.getPhoneNumber());
        result.setBirthDate(user.getBirthDate());
        result.setUpdateDate(user.getUpdateDate());
        return result;
    }

    public static User convert(UserDTO user) {
        User result = new User();
        result.setId(user.getId());
        result.setLogin(user.getLogin());
        result.setPassword(user.getPassword());
        result.setPhoneNumber(user.getPhoneNumber());
        result.setBirthDate(user.getBirthDate());
        result.setUpdateDate(user.getUpdateDate());
        return result;
    }

    public static List<UserDTO> convert(List<User> users) {
        return users.stream()
                .map(UserMapper::convert)
                .collect(Collectors.toList());
    }
}
