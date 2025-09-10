package by.cher.dao;

import by.cher.dto.MenuFilter;
import by.cher.entity.Menu;
import by.cher.exception.DaoException;
import by.cher.utils.ConnectionManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class MenuDao implements Dao<Long, Menu> {

    private final static MenuDao INSTANCE = new MenuDao();

    private final static String SAVE_SQL = """
          INSERT INTO menu (id, pizzeria_id, pizza_name, price)
          values (?, ?, ?, ?);
          """;

    private final static String DELETE_SQL = """
            delete from menu where id = ?;
            """;

    private final static String FIND_ALL_SQL = """
            select id, pizzeria_id, pizza_name, price from menu
            """;


    public final static String FIND_BY_ID_SQL = """
            select id, pizzeria_id, pizza_name, price from menu
            where id = ?;
            """;

    private final static String UPDATE_SQL = """
            UPDATE menu 
            SET
            pizzeria_id = ?,
            pizza_name = ?,
            price = ?
            where id = ?
            """;

    private final static String FIND_ALL_BY_PIZZERIA_ID = """
            select *
            from menu 
            where pizzeria_id = ?
            """;

    public List<Menu> findAll(MenuFilter filter) {
        List<Object> parameters = new ArrayList<>();
        List<String> whereSql = new ArrayList<>();

        if (filter.pizzaName() != null) {
            parameters.add("%" + filter.pizzaName() + "%");
            whereSql.add("pizza_name like ?");
        }

        if (filter.pizzeriaId() != null) {
            parameters.add(filter.pizzeriaId());
            whereSql.add("pizzeria_id = ?");
        }
        parameters.add(filter.limit());
        parameters.add(filter.offset());

        String where = whereSql.stream().collect(Collectors.joining(
                " AND ",
                parameters.size() > 2 ? " WHERE " : " ",
                " LIMIT ? OFFSET ?"
        ));

        String sql = FIND_ALL_SQL + where;
        try (var connection = ConnectionManager.get();
             var statement = connection.prepareStatement(sql)) {
            List<Menu> rows = new ArrayList<>();

            for (int i = 0; i < parameters.size(); i++) {
                statement.setObject(i + 1, parameters.get(i));

            }
            System.out.println(statement);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                rows.add(buildMenu(resultSet));
            }

            return rows;

        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }


    public List<Menu> findAllByPizzeriaId(Long id) {
        try (var connection = ConnectionManager.get();
        var statement = connection.prepareStatement(FIND_ALL_BY_PIZZERIA_ID)) {

            List<Menu> rows = new ArrayList<>();

            statement.setLong(1, id);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                rows.add(buildMenu(resultSet)
                );
            }

            return rows;


        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public boolean update(Menu menu) {
        try (var connection = ConnectionManager.get();
             var statement = connection.prepareStatement(UPDATE_SQL)) {

            statement.setLong(1, menu.getPizzeriaId());
            statement.setString(2, menu.getPizzaName());
            statement.setBigDecimal(3, menu.getPrice());
            statement.setLong(4, menu.getId());


            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public Optional<Menu> findById(Long id) {
        try (var connection = ConnectionManager.get();
             var statement = connection.prepareStatement(FIND_BY_ID_SQL)) {

            Menu row = null;

            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                row = buildMenu(resultSet);
            }

            return Optional.ofNullable(row);

        } catch (SQLException e) {
            throw new DaoException(e);
        }

    }


    public List<Menu> findAll() {
        try (var connection = ConnectionManager.get();
        var statement = connection.prepareStatement(FIND_ALL_SQL)) {
            List<Menu> rows = new ArrayList<>();

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                rows.add(buildMenu(resultSet));
            }

            return rows;

        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public Menu save(Menu menu) {
         try(Connection connection = ConnectionManager.get();
             var statement = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS) ) {

             statement.setLong(1, menu.getId());
             statement.setLong(2, menu.getPizzeriaId());
             statement.setString(3, menu.getPizzaName());
             statement.setBigDecimal(4, menu.getPrice());

             statement.executeUpdate();
             var generatedKeys = statement.getGeneratedKeys();

             if (generatedKeys.next()) {
                 menu.setId(generatedKeys.getLong("id"));
             }

             return menu;

         } catch (SQLException e) {
             throw new DaoException(e);
         }
    }

    public boolean delete(Long id) {
        try (var connection = ConnectionManager.get();
        var statement = connection.prepareStatement(DELETE_SQL)) {
            statement.setLong(1, id);

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    private Menu buildMenu(ResultSet resultSet) throws SQLException {
        return new Menu(
                        resultSet.getLong("id"),
                        resultSet.getLong("pizzeria_id"),
                        resultSet.getString("pizza_name"),
                        resultSet.getBigDecimal("price")
                );
    }

    // Private constructor to prevent instantiation
    private MenuDao() {
    }

    //Get the singleton instance
    public static MenuDao getInstance() {
        return INSTANCE;
    }
}
