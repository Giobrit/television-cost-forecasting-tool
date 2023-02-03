package it.giobr.televisioncostforecastingtool.service;

import it.giobr.televisioncostforecastingtool.enumeration.Country;
import it.giobr.televisioncostforecastingtool.model.Factory;
import it.giobr.televisioncostforecastingtool.model.MonetaryValue;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static it.giobr.televisioncostforecastingtool.enumeration.Country.*;
import static it.giobr.televisioncostforecastingtool.enumeration.Currency.*;

public class FactoryService {

    private final List<Factory> data;

    private static FactoryService instance;

    private FactoryService() {
        data = List.of(
                new Factory(BRAZIL, new MonetaryValue(BRL,  new BigDecimal("50"))),
                new Factory(CHINA, new MonetaryValue(CNY,  new BigDecimal("50"))),
                new Factory(UNITED_STATES, new MonetaryValue(USD,  new BigDecimal("15")))
        );
    }

    public Optional<Factory> findByCountry(Country country) {
        return data
                .stream()
                .filter(factory -> factory.getCountry().equals(country))
                .findAny();
    }

    public static FactoryService getInstance() {
        if (instance == null) {
            instance = new FactoryService();
        }

        return instance;
    }
}
