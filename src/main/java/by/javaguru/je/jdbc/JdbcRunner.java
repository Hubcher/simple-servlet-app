package by.javaguru.je.jdbc;

import by.javaguru.je.jdbc.dao.MenuDao;
import java.sql.*;

import by.javaguru.je.jdbc.dao.PizzeriaDao;
import by.javaguru.je.jdbc.dto.MenuFilter;


public class JdbcRunner {

    public static void main(String[] args) throws SQLException {
        var menuDao = MenuDao.getInstance();
        var filter = new MenuFilter(null,null, 5, 0);

        var pizzeriaDao = PizzeriaDao.getInstance();
        System.out.println(pizzeriaDao.findAll());


    }
}
