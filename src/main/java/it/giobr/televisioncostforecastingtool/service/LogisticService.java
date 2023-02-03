package it.giobr.televisioncostforecastingtool.service;

import it.giobr.televisioncostforecastingtool.enumeration.Country;
import it.giobr.televisioncostforecastingtool.model.Logistic;
import it.giobr.televisioncostforecastingtool.model.MonetaryValue;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static it.giobr.televisioncostforecastingtool.enumeration.Country.*;
import static it.giobr.televisioncostforecastingtool.enumeration.Currency.USD;

public class LogisticService {

    private final List<Logistic> data;

    private static LogisticService instance;

    private LogisticService() {
        data = List.of(
                new Logistic(BRAZIL, CHINA, new MonetaryValue(USD,  new BigDecimal("0.17"))),
                new Logistic(CHINA, BRAZIL, new MonetaryValue(USD,  new BigDecimal("0.15"))),
                new Logistic(BRAZIL, UNITED_STATES, new MonetaryValue(USD,  new BigDecimal("0.1"))),
                new Logistic(UNITED_STATES, BRAZIL, new MonetaryValue(USD,  new BigDecimal("0.15"))),
                new Logistic(CHINA, UNITED_STATES, new MonetaryValue(USD,  new BigDecimal("0.07"))),
                new Logistic(UNITED_STATES, CHINA, new MonetaryValue(USD,  new BigDecimal("0.1")))
        );
    }

    public Optional<Logistic> findByOriginAndDestination(Country origin, Country destination) {
        return data
                .stream()
                .filter(logistic -> logistic.getOrigin().equals(origin))
                .filter(logistic -> logistic.getDestination().equals(destination))
                .findAny();
    }

    public static LogisticService getInstance() {
        if (instance == null) {
            instance = new LogisticService();
        }

        return instance;
    }
}
