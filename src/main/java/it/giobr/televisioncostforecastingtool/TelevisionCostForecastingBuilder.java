package it.giobr.televisioncostforecastingtool;

import it.giobr.televisioncostforecastingtool.calculator.*;
import it.giobr.televisioncostforecastingtool.enumeration.Category;
import it.giobr.televisioncostforecastingtool.enumeration.Country;
import it.giobr.televisioncostforecastingtool.exception.BusinessException;
import it.giobr.televisioncostforecastingtool.model.MonetaryValue;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.YearMonth;

public class TelevisionCostForecastingBuilder {

    private final KeyPartCostCalculator keyPartCostCalculator;
    private final MonetaryValueExchangeCalculator monetaryValueExchangeCalculator;
    private final WarrantyCostCalculator warrantyCostCalculator;
    private final ManufacturingCostCalculator manufacturingCostCalculator;
    private final LogisticCostCalculator logisticCostCalculator;

    private String screenName;
    private String processorName;
    private String remoteControlName;
    private String audioSystemName;
    private MonetaryValue baseUnit;
    private BigDecimal warranty;
    private YearMonth launchDate;
    private Country launchCountry;
    private Country factoryCountry;

    public TelevisionCostForecastingBuilder() {
        this.keyPartCostCalculator = KeyPartCostCalculator.getInstance();
        this.monetaryValueExchangeCalculator = MonetaryValueExchangeCalculator.getInstance();
        this.warrantyCostCalculator = WarrantyCostCalculator.getInstance();
        this.manufacturingCostCalculator = ManufacturingCostCalculator.getInstance();
        this.logisticCostCalculator = LogisticCostCalculator.getInstance();
    }

    public TelevisionCostForecastingBuilder screen(String name) {
        this.screenName = name;

        return this;
    }

    public TelevisionCostForecastingBuilder processor(String name) {
        this.processorName = name;

        return this;
    }

    public TelevisionCostForecastingBuilder remoteControl(String name) {
        this.remoteControlName = name;

        return this;
    }

    public TelevisionCostForecastingBuilder audioSystem(String name) {
        this.audioSystemName = name;

        return this;
    }

    public TelevisionCostForecastingBuilder baseUnit(MonetaryValue baseUnit) {
        if (BigDecimal.ZERO.compareTo(baseUnit.getValue()) >= 0) {
            throw new BusinessException("The Base Unit Cost must be greater than zero");
        }

        this.baseUnit = baseUnit;

        return this;
    }

    public TelevisionCostForecastingBuilder warranty(BigDecimal warranty) {
        if (BigDecimal.ZERO.compareTo(warranty) >= 0 || BigDecimal.ONE.compareTo(warranty) < 0) {
            throw new BusinessException("The warranty percentage must be greater than zero and less than or equal to 1");
        }

        this.warranty = warranty;

        return this;
    }

    public TelevisionCostForecastingBuilder launchDate(LocalDate launchDate) {
        if (launchDate == null || LocalDate.now().isBefore(launchDate)) {
            throw new BusinessException("The launch date must be greater than the current date");
        }

        this.launchDate = YearMonth.from(launchDate);

        return this;
    }

    public TelevisionCostForecastingBuilder launchCountry(Country launchCountry) {
        this.launchCountry = launchCountry;

        return this;
    }

    public TelevisionCostForecastingBuilder factoryCountry(Country factoryCountry) {
        this.factoryCountry = factoryCountry;

        return this;
    }

    public BigDecimal getForecasting() {
        var keyPartsCost = calculatePartsCosts();
        var baseUnitCost = monetaryValueExchangeCalculator.calculate(baseUnit, launchDate);
        var warrantyCost = warrantyCostCalculator.calculate(keyPartsCost, baseUnitCost, warranty);
        var manufacturingCost = manufacturingCostCalculator.calculate(factoryCountry, launchDate);
        var logisticCost = logisticCostCalculator.calculate(factoryCountry, launchCountry, launchDate);

        return keyPartsCost
                .add(baseUnitCost)
                .add(warrantyCost)
                .add(manufacturingCost)
                .add(logisticCost)
                .setScale(2, RoundingMode.HALF_EVEN);
    }

    private BigDecimal calculatePartsCosts() {
        return keyPartCostCalculator.calculate(Category.SCREEN, screenName, launchDate)
                .add(keyPartCostCalculator.calculate(Category.PROCESSOR, processorName, launchDate))
                .add(keyPartCostCalculator.calculate(Category.REMOTE_CONTROL, remoteControlName, launchDate))
                .add(keyPartCostCalculator.calculate(Category.AUDIO_SYSTEM, audioSystemName, launchDate));
    }
}
