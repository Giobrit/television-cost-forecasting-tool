package it.giobr.televisioncostforecastingtool.service;

import it.giobr.televisioncostforecastingtool.enumeration.Category;
import it.giobr.televisioncostforecastingtool.enumeration.Currency;
import it.giobr.televisioncostforecastingtool.model.Exchange;
import it.giobr.televisioncostforecastingtool.model.KeyPart;
import it.giobr.televisioncostforecastingtool.model.MonetaryValue;

import java.math.BigDecimal;
import java.time.YearMonth;
import java.util.List;
import java.util.Optional;

import static it.giobr.televisioncostforecastingtool.enumeration.Category.*;
import static it.giobr.televisioncostforecastingtool.enumeration.Currency.*;

public class ExchangeService {

    private final List<Exchange> data;

    private static ExchangeService instance;

    private ExchangeService() {
        data = List.of(
                new Exchange(BRL, YearMonth.of(2023, 1), new BigDecimal("0.19")),
                new Exchange(BRL, YearMonth.of(2023, 2), new BigDecimal("0.20")),
                new Exchange(BRL, YearMonth.of(2023, 3), new BigDecimal("0.21")),
                new Exchange(CNY, YearMonth.of(2023, 1), new BigDecimal("0.14")),
                new Exchange(CNY, YearMonth.of(2023, 2), new BigDecimal("0.15")),
                new Exchange(CNY, YearMonth.of(2023, 3), new BigDecimal("0.16"))
        );
    }

    public Optional<Exchange> findByCurrencyAndYearMonth(Currency currency, YearMonth yearMonth) {
        return data
                .stream()
                .filter(exchange -> exchange.getCurrency().equals(currency))
                .filter(exchange -> exchange.getYearMonth().equals(yearMonth))
                .findAny();
    }

    public static ExchangeService getInstance() {
        if (instance == null) {
            instance = new ExchangeService();
        }

        return instance;
    }
}
