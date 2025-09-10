package by.cher.dao;

import by.cher.entity.Gender;
import by.cher.entity.Role;
import by.cher.entity.User;
import by.cher.exception.DaoException;
import by.cher.utils.ConnectionManager;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserDao implements Dao<Long, User> {

    private static final UserDao INSTANCE = new UserDao();

    private final static String SAVE_SQL = """
            INSERT INTO users (name, birthday, email, password, role, gender)
            VALUES (?, ?, ?, ?, ?, ?)
            """;

    private static final String GET_BY_EMAIL_AND_PASSWORD_SQL = """
            SELECT * FROM users WHERE email = ? AND password = ?
            """;

    @SneakyThrows
    public Optional<User> findByEmailAndPassword(String email, String password) {
        try (var connection = ConnectionManager.get();
             var prepareStatement = connection.prepareStatement(GET_BY_EMAIL_AND_PASSWORD_SQL)) {

            prepareStatement.setObject(1, email);
            prepareStatement.setObject(2, password);

            var resultSet = prepareStatement.executeQuery();
            User user = null;

            if (resultSet.next()) {
                user = buildEntity(resultSet);
            }
            return Optional.ofNullable(user);
        }
    }

    private User buildEntity(ResultSet resultSet) throws java.sql.SQLException {
        return User.builder()
                .id(resultSet.getObject("id", Integer.class))
                .name(resultSet.getObject("name", String.class))
                .birthday(resultSet.getObject("birthday", Date.class).toLocalDate())
                .email(resultSet.getObject("email", String.class))
                .password(resultSet.getObject("password", String.class))
                .role(Role.find(resultSet.getObject("role", String.class)).orElse(null))
                .gender(Gender.valueOf(resultSet.getObject("gender", String.class)))
                .build();
    }


    @Override
    @SneakyThrows
    public User save(User user) {
        try (var connection = ConnectionManager.get();
             var prepareStatement = connection.prepareStatement(SAVE_SQL, RETURN_GENERATED_KEYS)) {

            prepareStatement.setObject(1, user.getName());
            prepareStatement.setObject(2, user.getBirthday());
            prepareStatement.setObject(3, user.getEmail());
            prepareStatement.setObject(4, user.getPassword());
            prepareStatement.setObject(5, user.getRole().name());
            prepareStatement.setObject(6, user.getGender().name());

            prepareStatement.executeUpdate();

            var generatedKeys = prepareStatement.getGeneratedKeys();
            generatedKeys.next();
            user.setId(generatedKeys.getObject("id", Integer.class));

            return user;

        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }



    public static UserDao getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean update(User user) {
        return false;
    }

    @Override
    public List<User> findAll() {
        return List.of();
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.empty();
    }


    @Override
    public boolean delete(Long id) {
        return false;
    }
}
