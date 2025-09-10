package by.javaguru.je.jdbc.entity;

import java.math.BigDecimal;
import java.util.Objects;

public class Menu {

    private Long id;
    private Long pizzeriaId;
    private String pizzaName;
    private BigDecimal price;

    public Menu() {
    }

    public Menu(Long id, Long pizzeriaId, String pizzaName, BigDecimal price) {
        this.id = id;
        this.pizzeriaId = pizzeriaId;
        this.pizzaName = pizzaName;
        this.price = price;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPizzeriaId() {
        return pizzeriaId;
    }

    public void setPizzeriaId(Long pizzeriaId) {
        this.pizzeriaId = pizzeriaId;
    }

    public String getPizzaName() {
        return pizzaName;
    }

    public void setPizzaName(String pizzaName) {
        this.pizzaName = pizzaName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Menu{" +
               "id=" + id +
               ", pizzeriaId=" + pizzeriaId +
               ", pizzaName='" + pizzaName + '\'' +
               ", price=" + price +
               '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Menu menu = (Menu) o;
        return Objects.equals(id, menu.id) && Objects.equals(pizzeriaId, menu.pizzeriaId) && Objects.equals(pizzaName, menu.pizzaName) && Objects.equals(price, menu.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, pizzeriaId, pizzaName, price);
    }
}
