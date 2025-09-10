package by.cher.service;

import by.cher.dao.UserDao;
import by.cher.dto.CreateUserDto;
import by.cher.dto.UserDto;
import by.cher.entity.User;
import by.cher.exception.ValidationException;
import by.cher.mapper.CreateUserMapper;
import by.cher.mapper.UserMapper;
import by.cher.validator.CreateUserValidator;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserService {
    private static final UserService INSTANCE = new UserService();
    private final CreateUserMapper createUserMapper = CreateUserMapper.getInstance();
    private final UserDao userDao = UserDao.getInstance();
    private final CreateUserValidator createUserValidator = CreateUserValidator.getInstance();
    private final UserMapper userMapper = UserMapper.getINSTANCE();

    public Optional<UserDto> login(String email, String password) {
        return userDao.findByEmailAndPassword(email, password).map(userMapper::mapFrom);
    }
    public Integer create(CreateUserDto createUserDto) {
        var validationResult = createUserValidator.isValid(createUserDto);
        if (!validationResult.isValid()) {
            throw new ValidationException(validationResult.getErrors());
        }

        User user = createUserMapper.mapFrom(createUserDto);
        userDao.save(user);
        return user.getId();
    }

    public static UserService getInstance() {
        return INSTANCE;
    }
}
