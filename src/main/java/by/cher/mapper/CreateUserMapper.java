package by.cher.mapper;

import by.cher.dto.CreateUserDto;
import by.cher.entity.Gender;
import by.cher.entity.Role;
import by.cher.entity.User;
import by.cher.utils.LocalDateFormatter;

public class CreateUserMapper implements Mapper<User, CreateUserDto> {
    private static final CreateUserMapper INSTANCE = new CreateUserMapper();

    @Override
    public User mapFrom(CreateUserDto object) {
        return User.builder()
                .name(object.getName())
                .birthday(LocalDateFormatter.format(object.getBirthday()))
                .email(object.getEmail())
                .password(object.getPassword())
                .gender(Gender.valueOf(object.getGender()))
                .role(Role.valueOf(object.getRole()))
                .build();
    }

    public static CreateUserMapper getInstance() {
        return INSTANCE;
    }

    private CreateUserMapper() {
    }
}
