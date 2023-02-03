package it.giobr.televisioncostforecastingtool.service;

import it.giobr.televisioncostforecastingtool.enumeration.Category;
import it.giobr.televisioncostforecastingtool.model.KeyPart;
import it.giobr.televisioncostforecastingtool.model.MonetaryValue;

import java.math.BigDecimal;
import java.time.YearMonth;
import java.util.List;
import java.util.Optional;

import static it.giobr.televisioncostforecastingtool.enumeration.Category.*;
import static it.giobr.televisioncostforecastingtool.enumeration.Currency.*;

public class KeyPartService {

    private final List<KeyPart> data;

    private static KeyPartService instance;

    private KeyPartService() {
        data = List.of(
                new KeyPart("Screen 21’", SCREEN, YearMonth.of(2023, 1), new MonetaryValue(USD, new BigDecimal("10.00"))),
                new KeyPart("Screen 21’", SCREEN, YearMonth.of(2023, 2), new MonetaryValue(USD, new BigDecimal("12.00"))),
                new KeyPart("Screen 21’", SCREEN, YearMonth.of(2023, 3), new MonetaryValue(USD, new BigDecimal("12.00"))),
                new KeyPart("Screen 29’", SCREEN, YearMonth.of(2023, 1), new MonetaryValue(CNY, new BigDecimal("91.21"))),
                new KeyPart("Screen 29’", SCREEN, YearMonth.of(2023, 2), new MonetaryValue(CNY, new BigDecimal("100.00"))),
                new KeyPart("Screen 29’", SCREEN, YearMonth.of(2023, 3), new MonetaryValue(CNY, new BigDecimal("104.24"))),
                new KeyPart("Processor CNX1234", PROCESSOR, YearMonth.of(2023, 1), new MonetaryValue(BRL, new BigDecimal("65.00"))),
                new KeyPart("Processor CNX1234", PROCESSOR, YearMonth.of(2023, 2), new MonetaryValue(BRL, new BigDecimal("70.00"))),
                new KeyPart("Processor CNX1234", PROCESSOR, YearMonth.of(2023, 3), new MonetaryValue(BRL, new BigDecimal("65.00"))),
                new KeyPart("Processor OBJ2344", PROCESSOR, YearMonth.of(2023, 1), new MonetaryValue(CNY, new BigDecimal("58.63"))),
                new KeyPart("Processor OBJ2344", PROCESSOR, YearMonth.of(2023, 2), new MonetaryValue(CNY, new BigDecimal("58.63"))),
                new KeyPart("Processor OBJ2344", PROCESSOR, YearMonth.of(2023, 3), new MonetaryValue(CNY, new BigDecimal("60.00"))),
                new KeyPart("Remote Control CTRL1234", REMOTE_CONTROL, YearMonth.of(2023, 1), new MonetaryValue(USD, new BigDecimal("2.00"))),
                new KeyPart("Remote Control CTRL1234", REMOTE_CONTROL, YearMonth.of(2023, 2), new MonetaryValue(USD, new BigDecimal("2.50"))),
                new KeyPart("Remote Control CTRL1234", REMOTE_CONTROL, YearMonth.of(2023, 3), new MonetaryValue(USD, new BigDecimal("2.60"))),
                new KeyPart("Remote Control CTRL5678", REMOTE_CONTROL, YearMonth.of(2023, 1), new MonetaryValue(BRL, new BigDecimal("5.00"))),
                new KeyPart("Remote Control CTRL5678", REMOTE_CONTROL, YearMonth.of(2023, 2), new MonetaryValue(BRL, new BigDecimal("5.00"))),
                new KeyPart("Remote Control CTRL5678", REMOTE_CONTROL, YearMonth.of(2023, 3), new MonetaryValue(BRL, new BigDecimal("7.00"))),
                new KeyPart("Speakers AF1234", AUDIO_SYSTEM, YearMonth.of(2023, 1), new MonetaryValue(BRL, new BigDecimal("72.00"))),
                new KeyPart("Speakers AF1234", AUDIO_SYSTEM, YearMonth.of(2023, 2), new MonetaryValue(BRL, new BigDecimal("73.00"))),
                new KeyPart("Speakers AF1234", AUDIO_SYSTEM, YearMonth.of(2023, 3), new MonetaryValue(BRL, new BigDecimal("74.00"))),
                new KeyPart("Speakers AF5678", AUDIO_SYSTEM, YearMonth.of(2023, 1), new MonetaryValue(CNY, new BigDecimal("65.15"))),
                new KeyPart("Speakers AF5678", AUDIO_SYSTEM, YearMonth.of(2023, 2), new MonetaryValue(CNY, new BigDecimal("65.15"))),
                new KeyPart("Speakers AF5678", AUDIO_SYSTEM, YearMonth.of(2023, 3), new MonetaryValue(CNY, new BigDecimal("70.00")))
        );
    }

    public Optional<KeyPart> findByCategoryAndNameAndYearMonth(Category category, String name, YearMonth yearMonth) {
        return data
                .stream()
                .filter(keyPart -> keyPart.getName().equals(name))
                .filter(keyPart -> keyPart.getCategory().equals(category))
                .filter(keyPart -> keyPart.getYearMonth().equals(yearMonth))
                .findAny();
    }

    public static KeyPartService getInstance() {
        if (instance == null) {
            instance = new KeyPartService();
        }

        return instance;
    }
}
