package com.laba.solvd.parsers;

import com.laba.solvd.model.Address;
import com.laba.solvd.model.Person;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.FileInputStream;
import java.time.LocalDate;


public class StAXParser implements Parser {
    private static final Logger LOGGER = LogManager.getLogger(StAXParser.class);
    Person person = null;
    @Override
    public Person parse(String path) {

        try (FileInputStream fis = new FileInputStream(path)) {
            XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
            XMLEventReader reader = xmlInputFactory.createXMLEventReader(fis);

            while (reader.hasNext()) {
                XMLEvent nextEvent = reader.nextEvent();

                if (nextEvent.isStartElement()) {
                    StartElement startElement = nextEvent.asStartElement();
                    switch (startElement.getName().getLocalPart()) {
                        case "person":
                            person = new Person();
                            break;
                        case "personId":
                            nextEvent = reader.nextEvent();
                            person.setPersonId(Integer.parseInt(nextEvent.asCharacters().getData()));
                            break;
                        case "firstName":
                            nextEvent = reader.nextEvent();
                            person.setFirstName(nextEvent.asCharacters().getData());
                            break;
                        case "lastName":
                            nextEvent = reader.nextEvent();
                            person.setLastName(nextEvent.asCharacters().getData());
                            break;
                        case "birthDate":
                            nextEvent = reader.nextEvent();
                            person.setBirthDate(LocalDate.parse(nextEvent.asCharacters().getData()));
                            break;
                        case "email":
                            nextEvent = reader.nextEvent();
                            person.setEmail(nextEvent.asCharacters().getData());
                            break;
                        case "phone":
                            nextEvent = reader.nextEvent();
                            person.setPhone(nextEvent.asCharacters().getData());
                            break;
                        case "address":
                            person.setAddress(parseAddress(reader));
                            break;
                        default:
                            System.out.println("Element should not exist...");
                    }
                }
                if (nextEvent.isEndElement()) {
                    EndElement endElement = nextEvent.asEndElement();
                    if (endElement.getName().getLocalPart().equals("person")) {
                        System.out.println("Person id: " + person.getPersonId());
                        System.out.println("Person First Name: " + person.getFirstName());
                        System.out.println("Person Last Name: " + person.getLastName());
                        System.out.println("Person Birth Date: " + person.getBirthDate());
                        System.out.println("Person Email: " + person.getEmail());
                        System.out.println("Person Phone: " + person.getPhone());

                        System.out.println(" Address Id: " + person.getAddress().getAddressId());
                        System.out.println("Address Street: " + person.getAddress().getStreet());
                        System.out.println("Address City: " + person.getAddress().getCity());
                        System.out.println("Address State: " + person.getAddress().getState());
                        System.out.println("Address Zip Code: " +person.getAddress().getZipCode());
                        System.out.println("Address Country: " + person.getAddress().getCountry());
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("XML file was not found...");
        }
        return person;
    }

    private Address parseAddress(XMLEventReader reader){
        Address address = new Address();

        while (reader.hasNext()) {
            try{
                XMLEvent nextEvent = reader.nextEvent();

                if (nextEvent.isStartElement()) {
                    StartElement startElement = nextEvent.asStartElement();

                    switch (startElement.getName().getLocalPart()) {
                        case "addressId":
                            nextEvent = reader.nextEvent();
                            address.setAddressId(Integer.parseInt(nextEvent.asCharacters().getData()));
                            break;
                        case "street":
                            nextEvent = reader.nextEvent();
                            address.setStreet(nextEvent.asCharacters().getData());
                            break;
                        case "city":
                            nextEvent = reader.nextEvent();
                            address.setCity(nextEvent.asCharacters().getData());
                            break;
                        case "state":
                            nextEvent = reader.nextEvent();
                            address.setState(nextEvent.asCharacters().getData());
                            break;
                        case "zipCode":
                            nextEvent = reader.nextEvent();
                            address.setZipCode(Integer.parseInt(nextEvent.asCharacters().getData()));
                            break;
                        case "country":
                            nextEvent = reader.nextEvent();
                            address.setCountry(nextEvent.asCharacters().getData());
                            break;
                    }
                }
                if (nextEvent.isEndElement()) {
                    EndElement endElement = nextEvent.asEndElement();
                    if (endElement.getName().getLocalPart().equals("address")) {
                        break;
                    }
                }
            } catch (XMLStreamException e) {
                System.out.println("XML file was unreadable, processing errors occurred");
            }
        }

        return address;
    }

}
