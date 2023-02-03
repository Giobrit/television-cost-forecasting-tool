package it.giobr.televisioncostforecastingtool.calculator;

import it.giobr.televisioncostforecastingtool.enumeration.Country;
import it.giobr.televisioncostforecastingtool.exception.BusinessException;
import it.giobr.televisioncostforecastingtool.model.Factory;
import it.giobr.televisioncostforecastingtool.model.Logistic;
import it.giobr.televisioncostforecastingtool.service.FactoryService;
import it.giobr.televisioncostforecastingtool.service.LogisticService;

import java.math.BigDecimal;
import java.time.YearMonth;

public class LogisticCostCalculator {

    private static LogisticCostCalculator instance;

    private final LogisticService logisticService;
    private final MonetaryValueExchangeCalculator monetaryValueExchangeCalculator;

    private LogisticCostCalculator() {
        this.logisticService = LogisticService.getInstance();
        this.monetaryValueExchangeCalculator = MonetaryValueExchangeCalculator.getInstance();
    }

    public BigDecimal calculate(Country origin, Country destination, YearMonth yearMonth) {
        return logisticService.findByOriginAndDestination(origin, destination)
                .map(Logistic::getCost)
                .map(cost -> monetaryValueExchangeCalculator.calculate(cost, yearMonth))
                .orElseThrow(() -> new BusinessException("Factory not found"));
    }

    public static LogisticCostCalculator getInstance() {
        if (instance == null) {
            instance = new LogisticCostCalculator();
        }

        return instance;
    }
}
