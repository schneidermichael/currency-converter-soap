package com.example.currencyconverter;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CurrencyConverterUtilityTest {

    @Test
    void parseDoubleTest(){
        String doubleAsString = "1.879";

        double result = CurrencyConverterUtility.parseDouble(doubleAsString);

        assertEquals(Double.parseDouble(doubleAsString), result);
    }

    @Test
    void parseDoubleIsNull(){
        String doubleAsString = null;

        double result = CurrencyConverterUtility.parseDouble(doubleAsString);

        assertEquals(0,result);
    }

    @Test
    void parseDoubleIsNotGreaterThanZero(){
        String doubleAsString = "0";

        double result = CurrencyConverterUtility.parseDouble(doubleAsString);

        assertEquals(0,result);
    }


}
