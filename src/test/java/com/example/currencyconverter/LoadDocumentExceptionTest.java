package com.example.currencyconverter;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class LoadDocumentExceptionTest {

    @Test()
    void ExceptionTest(){
        assertThrows(LoadDocumentException.class, () -> {
            throw new LoadDocumentException("Failure occured");
        });
    }
}
