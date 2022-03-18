package com.example.currencyconverter;

/**
 * @author michaelschneider
 * Exception thrown if an failure occured in the method loadDocument
 */
public class LoadDocumentException extends RuntimeException {
    public LoadDocumentException(String errorMessage) {
        super(errorMessage);
    }
}
