package by.javaguru.je.jdbc.dto;


import by.javaguru.je.jdbc.entity.Gender;
import by.javaguru.je.jdbc.entity.Role;
import lombok.*;

import java.time.LocalDate;

@Value
@Builder
public class UserDto {
    Integer id;
    String name;
    LocalDate birthday;
    String email;
    Role role;
    Gender gender;
}
