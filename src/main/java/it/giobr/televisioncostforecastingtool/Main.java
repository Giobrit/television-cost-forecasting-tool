package it.giobr.televisioncostforecastingtool;

import it.giobr.televisioncostforecastingtool.model.MonetaryValue;

import java.math.BigDecimal;
import java.time.LocalDate;

import static it.giobr.televisioncostforecastingtool.enumeration.Country.BRAZIL;
import static it.giobr.televisioncostforecastingtool.enumeration.Country.CHINA;
import static it.giobr.televisioncostforecastingtool.enumeration.Currency.USD;

public class Main {
    public static void main(String[] args) {
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

        System.out.println(forcasting);
    }

}