package com.laba.solvd.parsers;


import com.laba.solvd.model.Address;
import com.laba.solvd.model.Person;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Unmarshaller;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;

public class JAXBParser implements Parser {

    private static final Logger LOGGER = LogManager.getLogger(JAXBParser.class);

    @Override
    public Person parse(String path) {
        Person person = null;
        File xmlFile = new File(path);

        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Person.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            person = (Person) jaxbUnmarshaller.unmarshal(xmlFile);
            if (person != null) {
                System.out.println("Person id: " + person.getPersonId());
                System.out.println("Person First Name: " + person.getFirstName());
                System.out.println("Person Last Name: " + person.getLastName());
                System.out.println("Person Birth Date: " + person.getBirthDate());
                System.out.println("Person Email: " + person.getEmail());
                System.out.println("Person Phone: " + person.getPhone());

                Address address = person.getAddress();
                if (address != null) {
                    System.out.println("Address Id: " + address.getAddressId());
                    System.out.println("Address Street: " + address.getStreet());
                    System.out.println("Address City: " + address.getCity());
                    System.out.println("Address State: " + address.getState());
                    System.out.println("Address Zip Code: " + address.getZipCode());
                    System.out.println("Address Country: " + address.getCountry());
                } else {
                    LOGGER.warn("Person's address is null");
                }
            } else {
                LOGGER.warn("Person is null");
            }
            return person;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}