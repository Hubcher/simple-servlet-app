package by.javaguru.je.jdbc.entity;

import java.math.BigDecimal;
import java.util.Objects;

public class Pizzeria {
    private Long id;
    private String name;
    private BigDecimal rating;

    public Pizzeria(Long id, String name, BigDecimal rating) {
        this.id = id;
        this.name = name;
        this.rating = rating;
    }

    public Pizzeria() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getRating() {
        return rating;
    }

    public void setRating(BigDecimal rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Pizzeria{" +
               "id=" + id +
               ", name='" + name + '\'' +
               ", rating=" + rating +
               '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pizzeria pizzeria = (Pizzeria) o;
        return Objects.equals(id, pizzeria.id) && Objects.equals(name, pizzeria.name) && Objects.equals(rating, pizzeria.rating);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, rating);
    }
}
