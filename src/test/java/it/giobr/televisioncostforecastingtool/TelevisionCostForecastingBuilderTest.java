package it.giobr.televisioncostforecastingtool;

import it.giobr.televisioncostforecastingtool.calculator.MonetaryValueExchangeCalculator;
import it.giobr.televisioncostforecastingtool.enumeration.Currency;
import it.giobr.televisioncostforecastingtool.model.MonetaryValue;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.YearMonth;

import static it.giobr.televisioncostforecastingtool.enumeration.Country.BRAZIL;
import static it.giobr.televisioncostforecastingtool.enumeration.Country.CHINA;
import static it.giobr.televisioncostforecastingtool.enumeration.Currency.USD;

public class TelevisionCostForecastingBuilderTest {

    @Test
    public void exampleTestCase() {
        var builder = new TelevisionCostForecastingBuilder();

        var forcasting = builder
                .screen("Screen 21’")
                .processor("Processor CNX1234")
                .remoteControl("Remote Control CTRL1234")
                .audioSystem("Speakers AF5678")
                .baseUnit(new MonetaryValue(USD, new BigDecimal("10")))
                .warranty(new BigDecimal("0.1"))
                .launchDate(LocalDate.of(2023, 1, 15))
                .launchCountry(BRAZIL)
                .factoryCountry(CHINA)
                .getForecasting();

        //the logistic cost on example is not correct
        Assertions.assertEquals(forcasting, new BigDecimal("54.97"));
    }
}
