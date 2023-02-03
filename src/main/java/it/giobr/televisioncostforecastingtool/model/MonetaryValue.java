package it.giobr.televisioncostforecastingtool.model;

import it.giobr.televisioncostforecastingtool.enumeration.Currency;
import it.giobr.televisioncostforecastingtool.exception.BusinessException;

import java.math.BigDecimal;
import java.util.Objects;

public class MonetaryValue {
    private final Currency currency;
    private final BigDecimal value;

    public MonetaryValue(Currency currency, BigDecimal value) {
        if (currency == null || value == null) {
            throw new BusinessException("Currency or value can't be null");
        }

        this.currency = currency;
        this.value = value;
    }

    public Currency getCurrency() {
        return currency;
    }

    public BigDecimal getValue() {
        return value;
    }

    public MonetaryValue toDolar(Exchange exchange) {
        if (Currency.USD.equals(currency)) {
            return this;
        }

        return new MonetaryValue(Currency.USD, value.multiply(exchange.getRate()));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MonetaryValue that = (MonetaryValue) o;
        return currency == that.currency && Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(currency, value);
    }

    @Override
    public String toString() {
        return "MonetaryValue{" +
                "currency=" + currency +
                ", value=" + value +
                '}';
    }
}
