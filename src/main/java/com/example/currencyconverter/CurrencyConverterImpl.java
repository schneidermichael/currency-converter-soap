package com.example.currencyconverter;

import org.example.currencyconverter.*;

import javax.jws.WebService;

import static com.example.currencyconverter.CurrencyConverterUtility.getCurrencies;

/**
 * @author michaelschneider
 */
@WebService(targetNamespace = "http://example.org/CurrencyConverter", portName = "CurrencyConverterPort", serviceName = "CurrencyConverterService", endpointInterface = "org.example.currencyconverter.CurrencyConverterInterface")
public class CurrencyConverterImpl implements CurrencyConverterInterface {

    private static final int ONE_CHARACTER = 1;
    private static final int TWO_CHARACTERS = 2;

    /**
     *
     * @param parameters
     * @return
     * @throws InvalidInputError
     */
    public CurrencyItem currencyPerCode(String parameters) throws InvalidInputError {

        CurrenciesListResponse currencies = getCurrencies();
        CurrencyItem currency = new CurrencyItem();
        boolean match = false;

        if (parameters.isEmpty()) {
            throw new InvalidInputError("Parameter is empty");
        } else if (parameters.length() == ONE_CHARACTER) {
            throw new InvalidInputError("Parameter has one character, only alphabetic code with three character are allowed");
        } else if (parameters.length() == TWO_CHARACTERS) {
            throw new InvalidInputError("Parameter has two character, only alphabetic code with three character are allowed");
        } else {
            for (CurrencyItem c : currencies.getCurrencyItem()) {
                if (c.getCurrency().contains(parameters)) {
                    currency.setCurrency(c.getCurrency());
                    currency.setRate(c.getRate());
                    match = true;
                }
            }
        }

        if (Boolean.FALSE.equals(match)) {
            throw new InvalidInputError(
                    "Alphabetic code isn't available - " + parameters);
        }

        return currency;
    }

    /**
     *
     * @param parameters
     * @return
     */
    public CurrenciesListResponse currenciesList(CurrenciesListRequest parameters) {
        return getCurrencies();
    }

}
