package it.giobr.televisioncostforecastingtool.model;

import it.giobr.televisioncostforecastingtool.enumeration.Country;
import it.giobr.televisioncostforecastingtool.enumeration.Currency;

import java.math.BigDecimal;
import java.time.YearMonth;
import java.util.Objects;

public class Exchange {
    private final Currency currency;
    private final YearMonth yearMonth;
    private final BigDecimal rate;

    public Exchange(Currency currency, YearMonth yearMonth, BigDecimal rate) {
        this.currency = currency;
        this.yearMonth = yearMonth;
        this.rate = rate;
    }

    public Currency getCurrency() {
        return currency;
    }

    public YearMonth getYearMonth() {
        return yearMonth;
    }

    public BigDecimal getRate() {
        return rate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Exchange exchange = (Exchange) o;
        return currency == exchange.currency && Objects.equals(yearMonth, exchange.yearMonth);
    }

    @Override
    public int hashCode() {
        return Objects.hash(currency, yearMonth);
    }
}
