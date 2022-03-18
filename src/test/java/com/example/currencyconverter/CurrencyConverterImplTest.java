package com.example.currencyconverter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.currencyconverter.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.Endpoint;
import javax.xml.ws.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CurrencyConverterImplTest {

    private static final Logger logger = LogManager.getLogger(CurrencyConverterImplTest.class);

    private static final QName SERVICE_NAME = new QName("http://example.org/CurrencyConverter", "CurrencyConverterInterface");
    private static final QName PORT_NAME = new QName("http://example.org/CurrencyConverter", "CurrencyConverterPort");

    private static final String ENDPOINT_ADDRESS = "http://localhost:8080/currency-converter-1.0.0/services/CurrencyConverterService";
    private static final String CURRENCY_SYMBOL = "USD";
    private static final String USERNAME = "group1";
    private static final String PASSWORD = "group1";
    private static final CurrenciesListRequest EMPTY = new CurrenciesListRequest();

    private static CurrencyConverterImpl currencyConverterImpl;
    private static Endpoint endpoint;

    @BeforeAll
    static void setUp() {
        currencyConverterImpl = new CurrencyConverterImpl();
        endpoint = Endpoint.publish(ENDPOINT_ADDRESS, new CurrencyConverterImpl());
    }

    @Test
    void currencyPerCodeTest() throws InvalidInputError {
        final CurrencyItem endpointResponse = createPort().currencyPerCode(CURRENCY_SYMBOL);
        final CurrencyItem localResponse = currencyConverterImpl.currencyPerCode(CURRENCY_SYMBOL);
        assertEquals(localResponse.getCurrency(), endpointResponse.getCurrency());
    }

    @Test
    void currenciesList() {
        final CurrenciesListResponse endpointResponse = createPort().currenciesList(EMPTY);
        final CurrenciesListResponse localResponse = currencyConverterImpl.currenciesList(EMPTY);
        assertEquals(localResponse.getCurrencyItem().get(2).getCurrency(), endpointResponse.getCurrencyItem().get(2).getCurrency());
    }

    @AfterAll
    static void tearDown() {
        endpoint.stop();
    }

    private CurrencyConverterInterface createPort() {
        Service service = Service.create(SERVICE_NAME);
        CurrencyConverterInterface port = service.getPort(PORT_NAME, CurrencyConverterInterface.class);

        BindingProvider bindingProvider = (BindingProvider) port;
        bindingProvider.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, USERNAME);
        bindingProvider.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, PASSWORD);
        bindingProvider.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, ENDPOINT_ADDRESS);
        return port;
    }
}