package it.giobr.televisioncostforecastingtool.model;

import it.giobr.televisioncostforecastingtool.enumeration.Category;

import java.time.YearMonth;
import java.util.Objects;

public class KeyPart {
    private final  String name;
    private final Category category;
    private final YearMonth yearMonth;
    private final MonetaryValue cost;

    public KeyPart(String name, Category category, YearMonth yearMonth, MonetaryValue cost) {
        this.name = name;
        this.category = category;
        this.yearMonth = yearMonth;
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public Category getCategory() {
        return category;
    }

    public YearMonth getYearMonth() {
        return yearMonth;
    }

    public MonetaryValue getCost() {
        return cost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        KeyPart keyPart = (KeyPart) o;
        return Objects.equals(name, keyPart.name) && category == keyPart.category && Objects.equals(yearMonth, keyPart.yearMonth);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, category, yearMonth);
    }
}
