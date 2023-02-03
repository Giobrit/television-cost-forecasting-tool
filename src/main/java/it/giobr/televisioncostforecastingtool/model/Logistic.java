package it.giobr.televisioncostforecastingtool.model;

import it.giobr.televisioncostforecastingtool.enumeration.Country;

import java.util.Objects;

public class Logistic {
    private final Country origin;
    private final Country destination;
    private final MonetaryValue cost;

    public Logistic(Country origin, Country destination, MonetaryValue cost) {
        this.origin = origin;
        this.destination = destination;
        this.cost = cost;
    }

    public Country getOrigin() {
        return origin;
    }

    public Country getDestination() {
        return destination;
    }

    public MonetaryValue getCost() {
        return cost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Logistic logistic = (Logistic) o;
        return origin == logistic.origin && destination == logistic.destination;
    }

    @Override
    public int hashCode() {
        return Objects.hash(origin, destination);
    }
}
