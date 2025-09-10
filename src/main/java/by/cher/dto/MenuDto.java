package by.cher.dto;

import java.math.BigDecimal;

public record MenuDto(
        Long id,
        Long PizzeriaId,
        String pizzaName,
        BigDecimal price
) {
}
