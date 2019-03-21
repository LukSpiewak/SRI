package pl.pja.sri.infrastructure.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode
public class UserDTO implements Serializable {

    private Long id;
    private String login;
    private String password;
    private String phoneNumber;
    private LocalDate birthDate;
    private LocalDateTime updateDate;
}
