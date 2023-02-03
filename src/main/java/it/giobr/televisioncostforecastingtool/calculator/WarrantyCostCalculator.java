package it.giobr.televisioncostforecastingtool.calculator;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class WarrantyCostCalculator {

    private static WarrantyCostCalculator instance;

    private WarrantyCostCalculator() {
    }

    public BigDecimal calculate(BigDecimal keyPartsCost, BigDecimal baseUnitCost, BigDecimal warranty) {
        return keyPartsCost.add(baseUnitCost).multiply(warranty).setScale(4, RoundingMode.HALF_EVEN);
    }

    public static WarrantyCostCalculator getInstance() {
        if (instance == null) {
            instance = new WarrantyCostCalculator();
        }

        return instance;
    }
}
