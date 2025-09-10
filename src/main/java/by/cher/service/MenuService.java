package by.cher.service;

import by.cher.dao.MenuDao;
import by.cher.dto.MenuDto;

import java.util.List;
import java.util.stream.Collectors;

public class MenuService {

    private static final MenuService INSTANCE = new MenuService();
    private final MenuDao menuDao = MenuDao.getInstance();

    public List<MenuDto> findAllByPizzeriaId(Long pizzeriaId) {
        return menuDao.findAllByPizzeriaId(pizzeriaId).stream().map(menu ->
                new MenuDto(
                        menu.getId(),
                        menu.getPizzeriaId(),
                        menu.getPizzaName(),
                        menu.getPrice()
                )).collect(Collectors.toList());
    }

    public static MenuService getInstance() {
        return INSTANCE;
    }

    private MenuService() {
        // private constructor to prevent instantiation
    }
}
