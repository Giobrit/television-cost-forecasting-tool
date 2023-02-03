package it.giobr.televisioncostforecastingtool.model;

import it.giobr.televisioncostforecastingtool.enumeration.Country;

import java.math.BigDecimal;
import java.util.Objects;

public class Factory {
    private final Country country;
    private final MonetaryValue manufacturingCost;

    public Factory(Country country, MonetaryValue manufacturingCost) {
        this.country = country;
        this.manufacturingCost = manufacturingCost;
    }

    public Country getCountry() {
        return country;
    }

    public MonetaryValue getManufacturingCost() {
        return manufacturingCost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Factory factory = (Factory) o;
        return country == factory.country;
    }

    @Override
    public int hashCode() {
        return Objects.hash(country);
    }
}
