package pl.pja.sri.domain.model;

import lombok.Data;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class User {

    private Long id;

    @NotNull
    @Size(min = 1, max = 25, message = "1-25 letters and spaces")
    @Pattern(regexp = "[^0-9]*", message = "Must not contain numbers")
    private String login;

    @NotNull
    @Size(min = 5, max = 25, message = "5-25 letters and spaces")
    @Pattern(regexp = "[^\\s]*", message = "Must not contain whitespaces")
    private String password;

    @NotNull
    @Size(min = 10, max = 12, message = "10-12 Numbers")
    @Digits(fraction = 0, integer = 12, message = "Not valid")
    private String phoneNumber;

    @NotNull
    private LocalDate birthDate;

    @NotNull
    private LocalDateTime updateDate;
}
