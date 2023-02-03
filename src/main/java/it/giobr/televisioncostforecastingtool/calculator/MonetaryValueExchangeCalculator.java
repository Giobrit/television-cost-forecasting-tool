package it.giobr.televisioncostforecastingtool.calculator;

import it.giobr.televisioncostforecastingtool.exception.BusinessException;
import it.giobr.televisioncostforecastingtool.model.MonetaryValue;
import it.giobr.televisioncostforecastingtool.service.ExchangeService;

import java.math.BigDecimal;
import java.time.Year;
import java.time.YearMonth;

import static it.giobr.televisioncostforecastingtool.enumeration.Currency.USD;

public class MonetaryValueExchangeCalculator {

    private static MonetaryValueExchangeCalculator instance;

    private final ExchangeService exchangeService;


    private MonetaryValueExchangeCalculator() {
        exchangeService = ExchangeService.getInstance();
    }

    public BigDecimal calculate(MonetaryValue monetaryValue, YearMonth yearMonth) {
        if (USD.equals(monetaryValue.getCurrency())){
            return monetaryValue.getValue();
        }

        var exchange = exchangeService.findByCurrencyAndYearMonth(monetaryValue.getCurrency(), yearMonth)
                .orElseThrow(() -> new BusinessException("Exchange not found"));

        return monetaryValue.toDolar(exchange).getValue();
    }

    public static MonetaryValueExchangeCalculator getInstance() {
        if (instance == null) {
            instance = new MonetaryValueExchangeCalculator();
        }

        return instance;
    }
}
