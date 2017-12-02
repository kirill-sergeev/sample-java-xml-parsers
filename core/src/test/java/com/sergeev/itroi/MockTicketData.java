package com.sergeev.itroi;

import com.sergeev.itroi.entity.Aircraft;
import com.sergeev.itroi.entity.Airline;
import com.sergeev.itroi.entity.Airport;
import com.sergeev.itroi.entity.Coordinates;
import com.sergeev.itroi.entity.Country;
import com.sergeev.itroi.entity.Currency;
import com.sergeev.itroi.entity.Flight;
import com.sergeev.itroi.entity.FlightStatus;
import com.sergeev.itroi.entity.Gender;
import com.sergeev.itroi.entity.Location;
import com.sergeev.itroi.entity.Passenger;
import com.sergeev.itroi.entity.Payment;
import com.sergeev.itroi.entity.PaymentStatus;
import com.sergeev.itroi.entity.PaymentType;
import com.sergeev.itroi.entity.Price;
import com.sergeev.itroi.entity.ServiceType;
import com.sergeev.itroi.entity.Ticket;
import com.sergeev.itroi.util.wrapper.TicketArray;

import java.io.File;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Collections;

public final class MockTicketData {

    private static final String TEST_RESOURCES_PATH = "src/test/resources/";
    private static final String MAIN_RESOURCES_PATH = "src/main/resources/";

    public static TicketArray ticketArrayInstance() {
        Airline airline = new Airline();
        airline.setCode("LHA");
        airline.setValue("Lufthansa");

        Aircraft aircraft = new Aircraft();
        aircraft.setCode("MN-312XD");
        aircraft.setModel("Airbus A-380");
        aircraft.setSeats(280);

        Country arrivalCountry = new Country();
        arrivalCountry.setCode("GER");
        arrivalCountry.setValue("Germany");

        Coordinates arrivalCoordinates = new Coordinates();
        arrivalCoordinates.setLatitude(50.0124);
        arrivalCoordinates.setLongitude(8.3203);

        Location arrivalLocation = new Location();
        arrivalLocation.setCity("Frankfurt");
        arrivalLocation.setCountry(arrivalCountry);
        arrivalLocation.setCoordinates(arrivalCoordinates);

        Airport arrivalAirport = new Airport();
        arrivalAirport.setCode("FRA");
        arrivalAirport.setAirportName("Frankfurt Airport");
        arrivalAirport.setLocation(arrivalLocation);
        arrivalAirport.setTimezone(1);

        Country departureCountry = new Country();
        departureCountry.setCode("USA");
        departureCountry.setValue("United States of America");

        Coordinates departureCoordinates = new Coordinates();
        departureCoordinates.setLatitude(40.64416);
        departureCoordinates.setLongitude(-73.782);

        Location departureLocation = new Location();
        departureLocation.setCity("New York");
        departureLocation.setCountry(departureCountry);
        departureLocation.setCoordinates(departureCoordinates);

        Airport departureAirport = new Airport();
        departureAirport.setCode("JFK");
        departureAirport.setAirportName("John F. Kennedy International Airport");
        departureAirport.setLocation(departureLocation);
        departureAirport.setTimezone(-5);

        Flight flight = new Flight();
        flight.setId(1);
        flight.setDeparture(ZonedDateTime.of(2010, 1, 1, 1, 1, 1, 0, ZoneOffset.ofHours(0)));
        flight.setArrival(ZonedDateTime.of(2010, 1, 1, 6, 1, 1, 0, ZoneOffset.ofHours(0)));
        flight.setFlightStatus(FlightStatus.COMPLETED);
        flight.setAircraft(aircraft);
        flight.setArrivalAirport(arrivalAirport);
        flight.setDepartureAirport(departureAirport);

        Passenger passenger = new Passenger();
        passenger.setId(1);
        passenger.setEmail("john@gmail.com");
        passenger.setGender(Gender.MALE);
        passenger.setName("John Doe");
        passenger.setBirthdate(LocalDate.of(1990, 1, 1));

        Price price = new Price();
        price.setCurrency(Currency.EUR);
        price.setValue(220.0);

        Payment payment = new Payment();
        payment.setId(1);
        payment.setPaymentStatus(PaymentStatus.PAID);
        payment.setPaymentType(PaymentType.CREDIT_CARD);
        payment.setPrice(price);

        Ticket ticket = new Ticket();
        ticket.setId(1);
        ticket.setBookDate(LocalDate.of(2010, 1, 1));
        ticket.setSeat(1);
        ticket.setServiceType(ServiceType.BUSINESS);
        ticket.setAirline(airline);
        ticket.setFlight(flight);
        ticket.setPassenger(passenger);
        ticket.setPayment(payment);

        TicketArray tickets = new TicketArray();
        tickets.setItems(Collections.singletonList(ticket));

        return tickets;
    }

    public static String xmlStringJaxb() {
        return TestUtils.readFileToString(TEST_RESOURCES_PATH + "xml/tickets_test_jaxb.xml");
    }

    public static String xmlStringDom() {
        return TestUtils.readFileToString(TEST_RESOURCES_PATH + "xml/tickets_test_dom.xml");
    }

    public static String invalidXmlString() {
        return TestUtils.readFileToString(TEST_RESOURCES_PATH + "xml/tickets_test_invalid.xml");
    }

    public static String htmlString() {
        return TestUtils.readFileToString(TEST_RESOURCES_PATH + "html/tickets_test.html");
    }

    public static File validationSchema() {
        return new File(MAIN_RESOURCES_PATH + "xsd/tickets.xsd");
    }

    public static File transformationSchema() {
        return new File(MAIN_RESOURCES_PATH + "xsl/tickets.xsl");
    }

    public static File transformationSchemaInvalid() {
        return new File(TEST_RESOURCES_PATH + "xsl/tickets_test_invalid.xsl");
    }
}
