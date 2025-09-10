package by.cher.dto;


public record MenuFilter (String pizzaName,
                          Long pizzeriaId,
                          int limit,
                          int offset){
}
