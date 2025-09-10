package by.cher.dto;


import by.cher.entity.Gender;
import by.cher.entity.Role;
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
