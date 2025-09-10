package by.javaguru.je.jdbc.dao;

import by.javaguru.je.jdbc.entity.Pizzeria;
import by.javaguru.je.jdbc.exception.DaoException;
import by.javaguru.je.jdbc.utils.ConnectionManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PizzeriaDao implements Dao <Long, Pizzeria> {

    private final static PizzeriaDao INSTANCE = new PizzeriaDao();

    private final static String FIND_ALL_SQL = """
            select id, name, rating from pizzeria
            """;

    @Override
    public boolean update(Pizzeria pizzeria) {
        return false;
    }

    @Override
    public List<Pizzeria> findAll() {
        try (var connection = ConnectionManager.get();
             var statement = connection.prepareStatement(FIND_ALL_SQL)) {
            List<Pizzeria> rows = new ArrayList<>();

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                rows.add(buildPizzeria(resultSet));
            }

            return rows;

        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    private Pizzeria buildPizzeria(ResultSet resultSet) throws SQLException {
        return new Pizzeria(
                resultSet.getLong("id"),
                resultSet.getString("name"),
                resultSet.getBigDecimal("rating")
        );
    }

    @Override
    public Optional<Pizzeria> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Pizzeria save(Pizzeria pizzeria) {
        return null;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    public static PizzeriaDao getInstance() {
        return INSTANCE;
    }

    private PizzeriaDao() {
    }


}
