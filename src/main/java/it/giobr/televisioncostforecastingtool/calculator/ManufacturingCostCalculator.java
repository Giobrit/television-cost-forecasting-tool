package it.giobr.televisioncostforecastingtool.calculator;

import it.giobr.televisioncostforecastingtool.enumeration.Country;
import it.giobr.televisioncostforecastingtool.exception.BusinessException;
import it.giobr.televisioncostforecastingtool.model.Factory;
import it.giobr.televisioncostforecastingtool.model.KeyPart;
import it.giobr.televisioncostforecastingtool.service.FactoryService;

import java.math.BigDecimal;
import java.time.YearMonth;

public class ManufacturingCostCalculator {

    private static ManufacturingCostCalculator instance;

    private final FactoryService factoryService;
    private final MonetaryValueExchangeCalculator monetaryValueExchangeCalculator;

    private ManufacturingCostCalculator() {
        this.factoryService = FactoryService.getInstance();
        this.monetaryValueExchangeCalculator = MonetaryValueExchangeCalculator.getInstance();
    }

    public BigDecimal calculate(Country country, YearMonth yearMonth) {
        return factoryService.findByCountry(country)
                .map(Factory::getManufacturingCost)
                .map(cost -> monetaryValueExchangeCalculator.calculate(cost, yearMonth))
                .orElseThrow(() -> new BusinessException("Factory not found"));
    }

    public static ManufacturingCostCalculator getInstance() {
        if (instance == null) {
            instance = new ManufacturingCostCalculator();
        }

        return instance;
    }
}
