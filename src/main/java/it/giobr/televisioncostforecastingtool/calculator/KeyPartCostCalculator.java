package it.giobr.televisioncostforecastingtool.calculator;

import it.giobr.televisioncostforecastingtool.enumeration.Category;
import it.giobr.televisioncostforecastingtool.exception.BusinessException;
import it.giobr.televisioncostforecastingtool.model.KeyPart;
import it.giobr.televisioncostforecastingtool.service.KeyPartService;

import java.math.BigDecimal;
import java.time.YearMonth;

public class KeyPartCostCalculator {

    private static KeyPartCostCalculator instance;

    private final KeyPartService keyPartService;
    private final MonetaryValueExchangeCalculator monetaryValueExchangeCalculator;

    private KeyPartCostCalculator() {
        this.keyPartService = KeyPartService.getInstance();
        this.monetaryValueExchangeCalculator = MonetaryValueExchangeCalculator.getInstance();
    }

    public BigDecimal calculate(Category category, String name, YearMonth yearMonth) {
        return KeyPartService.getInstance().findByCategoryAndNameAndYearMonth(category, name, yearMonth)
                .map(KeyPart::getCost)
                .map(cost -> monetaryValueExchangeCalculator.calculate(cost, yearMonth))
                .orElseThrow(() -> new BusinessException("Key part " + category.toString().toLowerCase() + " not found"));
    }

    public static KeyPartCostCalculator getInstance() {
        if (instance == null) {
            instance = new KeyPartCostCalculator();
        }

        return instance;
    }
}
