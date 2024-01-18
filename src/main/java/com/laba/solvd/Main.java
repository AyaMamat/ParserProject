package com.laba.solvd;

import com.laba.solvd.model.Person;
import com.laba.solvd.parsers.JAXBParser;
import com.laba.solvd.parsers.JSONParser;
import com.laba.solvd.parsers.Parser;
import com.laba.solvd.parsers.StAXParser;
import com.laba.solvd.validator.Validator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    private static final Logger LOGGER = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        String xmlPath = "src/main/resources/pharmacy.xml";
        String jsonPath = "src/main/resources/pharmacy.json";
        String xsdPath = "src/main/resources/pharmacy.xsd";

        Parser parser = null;

        System.out.println("*** XML VALIDATOR ***");
        Validator.validateXMLWithXSD(xmlPath, xsdPath);

        System.out.println("\n*** STAX PARSER ***");
        parser = new StAXParser();
        Person staxPerson=parser.parse(xmlPath);

        System.out.println("\n*** JSON PARSER ***");
        parser = new JSONParser();
        Person jsonPerson = parser.parse(jsonPath);

        System.out.println("\n*** JAXB PARSER ***");
        parser = new JAXBParser();
        Person jaxbPerson = parser.parse(xmlPath);

        System.out.println("STAX Person: " + staxPerson);
        System.out.println("JSON Person: " + jsonPerson);
        System.out.println("JAXB Person: " + jaxbPerson);

        System.out.println("\nARE ALL PERSON OBJECTS THE SAME?");
        System.out.println(staxPerson.equals(jsonPerson) && jsonPerson.equals(jaxbPerson));

    }
}
