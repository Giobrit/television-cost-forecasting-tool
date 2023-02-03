package it.giobr.televisioncostforecastingtool;

import it.giobr.televisioncostforecastingtool.calculator.MonetaryValueExchangeCalculator;
import it.giobr.televisioncostforecastingtool.enumeration.Currency;
import it.giobr.televisioncostforecastingtool.exception.BusinessException;
import it.giobr.televisioncostforecastingtool.model.MonetaryValue;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.YearMonth;

public class MonetaryValueExchangeCalculatorTest {
    @Test
    public void usdToUsd() {
        var valueInUsd = MonetaryValueExchangeCalculator
                .getInstance()
                .calculate(new MonetaryValue(Currency.USD, BigDecimal.TEN), YearMonth.now());

        Assertions.assertEquals(valueInUsd, BigDecimal.TEN);
    }

    @Test
    public void brlToUsd() {
        var valueInUsd = MonetaryValueExchangeCalculator
                .getInstance()
                .calculate(new MonetaryValue(Currency.BRL, BigDecimal.TEN), YearMonth.of(2023, 1));

        Assertions.assertEquals(valueInUsd, new BigDecimal("1.90"));
    }


    @Test
    public void cnyToUsd() {
        var valueInUsd = MonetaryValueExchangeCalculator
                .getInstance()
                .calculate(new MonetaryValue(Currency.CNY, BigDecimal.TEN), YearMonth.of(2023, 1));

        Assertions.assertEquals(valueInUsd, new BigDecimal("1.40"));
    }

    @Test
    public void invalidMonth() {

        var exception = Assertions.assertThrows(BusinessException.class,() ->
                MonetaryValueExchangeCalculator
                .getInstance()
                .calculate(new MonetaryValue(Currency.CNY, BigDecimal.TEN), YearMonth.of(2023, 6))
        );

        Assertions.assertEquals(exception.getMessage(), "Exchange not found");
    }
}
