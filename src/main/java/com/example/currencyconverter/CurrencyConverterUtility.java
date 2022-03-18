package com.example.currencyconverter;

import org.example.currencyconverter.CurrenciesListResponse;
import org.example.currencyconverter.CurrencyItem;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.URL;

public class CurrencyConverterUtility {

    private static final String URL = "https://www.ecb.europa.eu/stats/eurofxref/eurofxref-daily.xml";

    private CurrencyConverterUtility(){
        throw new IllegalArgumentException("Utility class");
    }

    public static Document loadDocument() {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            factory.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        factory.setNamespaceAware(true);
        try {
            return factory.newDocumentBuilder().parse(new URL(URL).openStream());
        } catch (SAXException | IOException | ParserConfigurationException e) {
            e.printStackTrace();
        }
        throw new LoadDocumentException("Failure occured");
    }

    public static double parseDouble(String strNumber) {
        if (strNumber != null && strNumber.length() > 0) {
            return Double.parseDouble(strNumber);
        }
        return 0;
    }

    public static CurrenciesListResponse getCurrencies() {

        CurrenciesListResponse response = new CurrenciesListResponse();
        try {
            Document doc = loadDocument();

            NodeList nList = doc.getElementsByTagName("Cube");

            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;

                    CurrencyItem currency = new CurrencyItem();

                    if (eElement.getAttribute("currency").isEmpty()) {
                        currency.setCurrency("N/A");
                    } else
                        currency.setCurrency(eElement.getAttribute("currency"));

                    currency.setRate(parseDouble(eElement.getAttribute("rate")));

                    if (!currency.getCurrency().equals("N/A")) {
                        response.getCurrencyItem().add(currency);
                    }
                }
            }

        } catch (Exception err) {
            err.printStackTrace();
        }

        return response;
    }

}
