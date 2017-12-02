package com.sergeev.itroi.parser.dom;

import com.sergeev.itroi.entity.Aircraft;
import com.sergeev.itroi.entity.Airline;
import com.sergeev.itroi.entity.Airport;
import com.sergeev.itroi.entity.Coordinates;
import com.sergeev.itroi.entity.Country;
import com.sergeev.itroi.entity.Flight;
import com.sergeev.itroi.entity.Location;
import com.sergeev.itroi.entity.Passenger;
import com.sergeev.itroi.entity.Payment;
import com.sergeev.itroi.entity.Price;
import com.sergeev.itroi.entity.Ticket;
import com.sergeev.itroi.util.XML;
import com.sergeev.itroi.util.metadata.TicketSchemaMetadata;
import com.sergeev.itroi.util.metadata.SchemaMetadata;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import javax.xml.XMLConstants;

public class TicketDomMarshaller extends AbstractDomMarshaller<com.sergeev.itroi.util.wrapper.TicketArray> {

    @Override
    protected void appendRootElement(Document document, com.sergeev.itroi.util.wrapper.TicketArray ticketArray) {
        Element bookingElement = appendElement(document, TicketSchemaMetadata.TICKET, XML.TicketArray.TYPE_NAME);

        for (SchemaMetadata prefix : TicketSchemaMetadata.values()) {
            String qualifiedName = String.join(":", XMLConstants.XMLNS_ATTRIBUTE, prefix.getPrefix());
            bookingElement.setAttributeNS(XMLConstants.XMLNS_ATTRIBUTE_NS_URI, qualifiedName, prefix.getURI());
        }

        for (Ticket ticket : ticketArray.getItems()) {
            appendTicketElement(bookingElement, ticket);
        }
    }

    private void appendTicketElement(Node parent, Ticket ticket) {
        Element ticketElement = appendElement(parent, TicketSchemaMetadata.TICKET, XML.TicketArray.TICKET);
        ticketElement.setAttribute(XML.Ticket.ID, String.valueOf(ticket.getId()));

        appendAirlineElement(ticketElement, ticket.getAirline());
        appendElementWithContent(ticketElement, TicketSchemaMetadata.TICKET, XML.Ticket.SEAT, ticket.getSeat());
        appendElementWithContent(ticketElement, TicketSchemaMetadata.TICKET, XML.Ticket.SERVICE_TYPE, ticket.getServiceType());
        appendFlightElement(ticketElement, ticket.getFlight());
        appendPassengerElement(ticketElement, ticket.getPassenger());
        appendPaymentElement(ticketElement, ticket.getPayment());
        appendElementWithContent(ticketElement, TicketSchemaMetadata.TICKET, XML.Ticket.BOOK_DATE, ticket.getBookDate());
    }

    private void appendAirlineElement(Node parent, Airline airline) {
        Element airlineElement = appendElementWithContent(parent, TicketSchemaMetadata.TICKET, XML.Ticket.AIRLINE, airline.getValue());
        airlineElement.setAttribute(XML.Airline.CODE, airline.getCode());
    }

    private void appendFlightElement(Node parent, Flight flight) {
        Element flightElement = appendElement(parent, TicketSchemaMetadata.TICKET, XML.Ticket.FLIGHT);
        flightElement.setAttribute(XML.Flight.ID, String.valueOf(flight.getId()));

        appendElementWithContent(flightElement, TicketSchemaMetadata.FLIGHT, XML.Flight.DEPARTURE, flight.getDeparture());
        appendElementWithContent(flightElement, TicketSchemaMetadata.FLIGHT, XML.Flight.ARRIVAL, flight.getArrival());
        appendElementWithContent(flightElement, TicketSchemaMetadata.FLIGHT, XML.Flight.STATUS, flight.getFlightStatus());
        appendAirportElement(flightElement, flight.getDepartureAirport(), XML.Flight.DEPARTURE_AIRPORT);
        appendAirportElement(flightElement, flight.getArrivalAirport(), XML.Flight.ARRIVAL_AIRPORT);
        appendAircraftElement(flightElement, flight.getAircraft());
    }

    private void appendAircraftElement(Node parent, Aircraft aircraft) {
        Element aircraftElement = appendElement(parent, TicketSchemaMetadata.FLIGHT, XML.Flight.AIRCRAFT);
        aircraftElement.setAttribute(XML.Aircraft.CODE, aircraft.getCode());

        appendElementWithContent(aircraftElement, TicketSchemaMetadata.AIRCRAFT, XML.Aircraft.MODEL, aircraft.getModel());
        appendElementWithContent(aircraftElement, TicketSchemaMetadata.AIRCRAFT, XML.Aircraft.SEATS, aircraft.getSeats());
    }

    private void appendAirportElement(Node parent, Airport airport, String elementName) {
        Element airportElement = appendElement(parent, TicketSchemaMetadata.FLIGHT, elementName);
        airportElement.setAttribute(XML.Airport.CODE, airport.getCode());

        appendElementWithContent(airportElement, TicketSchemaMetadata.AIRPORT, XML.Airport.NAME, airport.getAirportName());
        appendElementWithContent(airportElement, TicketSchemaMetadata.AIRPORT, XML.Airport.TIMEZONE, airport.getTimezone());
        appendLocationElement(airportElement, airport.getLocation());
    }

    private void appendLocationElement(Node parent, Location location) {
        Element locationElement = appendElement(parent, TicketSchemaMetadata.AIRPORT, XML.Airport.LOCATION);

        appendCountryElement(locationElement, location.getCountry());
        appendElementWithContent(locationElement, TicketSchemaMetadata.AIRPORT, XML.Location.CITY, location.getCity());
        appendCoordinatesElement(locationElement, location.getCoordinates());
    }

    private void appendCountryElement(Node parent, Country country) {
        Element countryElement = appendElementWithContent(parent, TicketSchemaMetadata.AIRPORT, XML.Location.COUNTRY, country.getValue());
        countryElement.setAttribute(XML.Country.CODE, country.getCode());
    }

    private void appendCoordinatesElement(Node parent, Coordinates coordinates) {
        Element coordinatesElement = appendElement(parent, TicketSchemaMetadata.AIRPORT, XML.Location.COORDINATES);

        appendElementWithContent(coordinatesElement, TicketSchemaMetadata.AIRPORT, XML.Coordinates.LATITUDE, coordinates.getLatitude());
        appendElementWithContent(coordinatesElement, TicketSchemaMetadata.AIRPORT, XML.Coordinates.LONGITUDE, coordinates.getLongitude());
    }

    private void appendPassengerElement(Node parent, Passenger passenger) {
        Element passengerElement = appendElement(parent, TicketSchemaMetadata.TICKET, XML.Ticket.PASSENGER);
        passengerElement.setAttribute(XML.Passenger.ID, String.valueOf(passenger.getId()));

        appendElementWithContent(passengerElement, TicketSchemaMetadata.PASSENGER, XML.Passenger.NAME, passenger.getName());
        appendElementWithContent(passengerElement, TicketSchemaMetadata.PASSENGER, XML.Passenger.GENDER, passenger.getGender());
        appendElementWithContent(passengerElement, TicketSchemaMetadata.PASSENGER, XML.Passenger.BIRTHDATE, passenger.getBirthdate());
        appendElementWithContent(passengerElement, TicketSchemaMetadata.PASSENGER, XML.Passenger.EMAIL, passenger.getEmail());
    }

    private void appendPaymentElement(Node parent, Payment payment) {
        Element paymentElement = appendElement(parent, TicketSchemaMetadata.TICKET, XML.Ticket.PAYMENT);
        paymentElement.setAttribute(XML.Passenger.ID, String.valueOf(payment.getId()));

        appendPriceElement(paymentElement, payment.getPrice());
        appendElementWithContent(paymentElement, TicketSchemaMetadata.PAYMENT, XML.Payment.TYPE, payment.getPaymentType());
        appendElementWithContent(paymentElement, TicketSchemaMetadata.PAYMENT, XML.Payment.STATUS, payment.getPaymentStatus());
    }

    private void appendPriceElement(Node parent, Price price) {
        Element priceElement = appendElementWithContent(parent, TicketSchemaMetadata.PAYMENT, XML.Payment.PRICE, price.getValue());
        priceElement.setAttribute(XML.Price.CURRENCY, String.valueOf(price.getCurrency()));
    }
}
