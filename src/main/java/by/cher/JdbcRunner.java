package by.cher;

import by.cher.dao.MenuDao;
import java.sql.*;

import by.cher.dao.PizzeriaDao;
import by.cher.dto.MenuFilter;


public class JdbcRunner {

    public static void main(String[] args) throws SQLException {
        var menuDao = MenuDao.getInstance();
        var filter = new MenuFilter(null,null, 5, 0);

        var pizzeriaDao = PizzeriaDao.getInstance();
        System.out.println(pizzeriaDao.findAll());


    }
}
