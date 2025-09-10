package by.cher.mapper;

import by.cher.dto.UserDto;
import by.cher.entity.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserMapper  implements Mapper<UserDto, User>{
    private static final UserMapper INSTANCE = new UserMapper();



    @Override
    public UserDto mapFrom(User user) {
        return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .birthday(user.getBirthday())
                .email(user.getEmail())
                .role(user.getRole())
                .gender(user.getGender())
                .build();
    }

    public static UserMapper getINSTANCE() {
        return INSTANCE;
    }
}
