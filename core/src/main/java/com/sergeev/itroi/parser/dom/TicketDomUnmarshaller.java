package com.sergeev.itroi.parser.dom;

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
import com.sergeev.itroi.util.XML;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.File;
import java.time.LocalDate;
import java.time.ZonedDateTime;

public class TicketDomUnmarshaller extends AbstractDomUnmarshaller<com.sergeev.itroi.util.wrapper.TicketArray> {

    public TicketDomUnmarshaller() {
        super(null);
    }

    public TicketDomUnmarshaller(File schema) {
        super(schema);
    }

    @Override
    protected com.sergeev.itroi.util.wrapper.TicketArray parseRootElement(Document document) {
        Element root = document.getDocumentElement();
        com.sergeev.itroi.util.wrapper.TicketArray ticketArray = new com.sergeev.itroi.util.wrapper.TicketArray();

        NodeList ticketNodes = root.getElementsByTagNameNS(DEFAULT_NAMESPACE_URI, XML.TicketArray.TICKET);
        for (int i = 0; i < ticketNodes.getLength(); i++) {
            Ticket ticket = parseTicket(ticketNodes.item(i));
            ticketArray.getItems().add(ticket);
        }
        return ticketArray;
    }

    private Ticket parseTicket(Node ticketNode) {
        Element ticketElement = (Element) ticketNode;

        Ticket ticket = new Ticket();
        ticket.setId(Integer.valueOf(ticketElement.getAttribute(XML.Ticket.ID)));

        Node seatNode = getNode(ticketElement, XML.Ticket.SEAT);
        ticket.setSeat(Integer.valueOf(seatNode.getTextContent()));

        Node dateNode = getNode(ticketElement, XML.Ticket.BOOK_DATE);
        ticket.setBookDate(LocalDate.parse(dateNode.getTextContent()));

        Node serviceTypeNode = getNode(ticketElement, XML.Ticket.SERVICE_TYPE);
        ticket.setServiceType(ServiceType.fromValue(serviceTypeNode.getTextContent()));

        Node airlineNode = getNode(ticketElement, XML.Ticket.AIRLINE);
        Airline airline = parseAirline(airlineNode);
        ticket.setAirline(airline);

        Node flightNode = getNode(ticketElement, XML.Ticket.FLIGHT);
        Flight flight = parseFlight(flightNode);
        ticket.setFlight(flight);

        Node paymentNode = getNode(ticketElement, XML.Ticket.PAYMENT);
        Payment payment = parsePayment(paymentNode);
        ticket.setPayment(payment);

        Node passengerNode = getNode(ticketElement, XML.Ticket.PASSENGER);
        Passenger passenger = parsePassenger(passengerNode);
        ticket.setPassenger(passenger);
        return ticket;
    }

    private Airline parseAirline(Node airlineNode) {
        Airline airline = new Airline();
        Element airlineElement = (Element) airlineNode;
        airline.setCode(airlineElement.getAttribute(XML.Airline.CODE));
        airline.setValue(airlineElement.getTextContent());
        return airline;
    }

    private Passenger parsePassenger(Node passengerNode) {
        Element passengerElement = (Element) passengerNode;

        Passenger passenger = new Passenger();
        passenger.setId(Integer.valueOf(passengerElement.getAttribute(XML.Passenger.ID)));

        Node nameNode = getNode(passengerElement, XML.Passenger.NAME);
        passenger.setName(nameNode.getTextContent());

        Node emailMode = getNode(passengerElement, XML.Passenger.EMAIL);
        passenger.setEmail(emailMode.getTextContent());

        Node birthdateNode = getNode(passengerElement, XML.Passenger.BIRTHDATE);
        passenger.setBirthdate(LocalDate.parse(birthdateNode.getTextContent()));

        Node genderNode = getNode(passengerElement, XML.Passenger.GENDER);
        passenger.setGender(Gender.fromValue(genderNode.getTextContent()));
        return passenger;
    }

    private Flight parseFlight(Node flightNode) {
        Element flightElement = (Element) flightNode;

        Flight flight = new Flight();
        flight.setId(Integer.valueOf(flightElement.getAttribute(XML.Flight.ID)));

        Node statusNode = getNode(flightElement, XML.Flight.STATUS);
        flight.setFlightStatus(FlightStatus.fromValue(statusNode.getTextContent()));

        Node arrivalNode = getNode(flightElement, XML.Flight.ARRIVAL);
        flight.setArrival(ZonedDateTime.parse(arrivalNode.getTextContent()));

        Node departureNode = getNode(flightElement, XML.Flight.DEPARTURE);
        flight.setDeparture(ZonedDateTime.parse(departureNode.getTextContent()));

        Node aircraftNode = getNode(flightElement, XML.Flight.AIRCRAFT);
        Aircraft aircraft = parseAircraft(aircraftNode);
        flight.setAircraft(aircraft);

        Node arrivalAirportNode = getNode(flightElement, XML.Flight.ARRIVAL_AIRPORT);
        Airport arrivalAirport = parseAirport(arrivalAirportNode);
        flight.setArrivalAirport(arrivalAirport);

        Node departureAirportNode = getNode(flightElement, XML.Flight.DEPARTURE_AIRPORT);
        Airport departureAirport = parseAirport(departureAirportNode);
        flight.setDepartureAirport(departureAirport);
        return flight;
    }

    private Aircraft parseAircraft(Node aircraftNode) {
        Element aircraftElement = (Element) aircraftNode;

        Aircraft aircraft = new Aircraft();
        aircraft.setCode(aircraftElement.getAttribute(XML.Aircraft.CODE));

        Node modelNode = getNode(aircraftElement, XML.Aircraft.MODEL);
        aircraft.setModel(modelNode.getTextContent());

        Node seatsNode = getNode(aircraftElement, XML.Aircraft.SEATS);
        aircraft.setSeats(Integer.valueOf(seatsNode.getTextContent()));
        return aircraft;
    }

    private Airport parseAirport(Node airportNode) {
        Element airportElement = (Element) airportNode;

        Airport airport = new Airport();
        airport.setCode(airportElement.getAttribute(XML.Airport.CODE));

        Node timezoneNode = getNode(airportElement, XML.Airport.TIMEZONE);
        airport.setTimezone(Integer.valueOf(timezoneNode.getTextContent()));

        Node nameNode = getNode(airportElement, XML.Airport.NAME);
        airport.setAirportName(nameNode.getTextContent());

        Node locationNode = getNode(airportElement, XML.Airport.LOCATION);
        Location location = parseLocation(locationNode);
        airport.setLocation(location);
        return airport;
    }

    private Location parseLocation(Node locationNode) {
        Element locationElement = (Element) locationNode;

        Location location = new Location();

        Node cityNode = getNode(locationElement, XML.Location.CITY);
        location.setCity(cityNode.getTextContent());

        Node countryNode = getNode(locationElement, XML.Location.COUNTRY);
        Country country = parseCountry(countryNode);
        location.setCountry(country);

        Node coordinatesNode = getNode(locationElement, XML.Location.COORDINATES);
        Coordinates coordinates = parseCoordinates(coordinatesNode);
        location.setCoordinates(coordinates);
        return location;
    }

    private Country parseCountry(Node countryNode) {
        Element countryElement = (Element) countryNode;

        Country country = new Country();
        country.setCode(countryElement.getAttribute(XML.Country.CODE));
        country.setValue(countryElement.getTextContent());
        return country;
    }

    private Coordinates parseCoordinates(Node coordinatesNode) {
        Element coordinatesElement = (Element) coordinatesNode;

        Coordinates coordinates = new Coordinates();

        Node latitudeNode = getNode(coordinatesElement, XML.Coordinates.LATITUDE);
        coordinates.setLatitude(Double.valueOf(latitudeNode.getTextContent()));

        Node longitudeNode = getNode(coordinatesElement, XML.Coordinates.LONGITUDE);
        coordinates.setLongitude(Double.valueOf(longitudeNode.getTextContent()));
        return coordinates;
    }

    private Payment parsePayment(Node paymentNode) {
        Element paymentElement = (Element) paymentNode;

        Payment payment = new Payment();
        payment.setId(Integer.valueOf(paymentElement.getAttribute(XML.Payment.ID)));

        Node paymentTypeNode = getNode(paymentElement, XML.Payment.TYPE);
        payment.setPaymentType(PaymentType.fromValue(paymentTypeNode.getTextContent()));

        Node paymentStatusNode = getNode(paymentElement, XML.Payment.STATUS);
        payment.setPaymentStatus(PaymentStatus.fromValue(paymentStatusNode.getTextContent()));

        Node priceNode = getNode(paymentElement, XML.Payment.PRICE);
        Price price = parsePrice(priceNode);
        payment.setPrice(price);
        return payment;
    }

    private Price parsePrice(Node priceNode) {
        Element priceElement = (Element) priceNode;

        Price price = new Price();
        price.setCurrency(Currency.fromValue(priceElement.getAttribute(XML.Price.CURRENCY)));
        price.setValue(Double.valueOf(priceElement.getTextContent()));
        return price;
    }
}
