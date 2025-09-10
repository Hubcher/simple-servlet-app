package by.javaguru.je.jdbc.dto;


public record MenuFilter (String pizzaName,
                          Long pizzeriaId,
                          int limit,
                          int offset){
}
