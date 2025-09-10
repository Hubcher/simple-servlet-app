package by.cher.service;

import by.cher.dao.PizzeriaDao;
import by.cher.dto.PizzeriaDto;

import java.util.List;
import java.util.stream.Collectors;

public class PizzeriaService {

    private static final PizzeriaService INSTANCE = new PizzeriaService();
    // Singleton instance of PizzeriaDao
    private final PizzeriaDao pizzeriaDao = PizzeriaDao.getInstance();

    public List<PizzeriaDto> findAll() {
        return pizzeriaDao.findAll().stream().map(pizzeria ->
                new PizzeriaDto(pizzeria.getId(),
                        "%s - %s".formatted(
                                pizzeria.getName(),
                                pizzeria.getRating()
                        ))).collect(Collectors.toList());
    }


    public static PizzeriaService getInstance() {
        return INSTANCE;
    }

    private PizzeriaService() {
        // private constructor to prevent instantiation
    }
}
