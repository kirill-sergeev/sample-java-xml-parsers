package com.sergeev.itroi.parser.stax;

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
import com.sergeev.itroi.parser.XmlParserException;
import com.sergeev.itroi.util.XML;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.File;
import java.time.LocalDate;
import java.time.ZonedDateTime;

public class TicketStaxUnmarshaller extends AbstractStaxUnmarshaller<com.sergeev.itroi.util.wrapper.TicketArray> {

    public TicketStaxUnmarshaller() {
        super(null);
    }

    public TicketStaxUnmarshaller(File schema) {
        super(schema);
    }

    @Override
    protected StaxInnerUnmarshaller<com.sergeev.itroi.util.wrapper.TicketArray> parser() {
        return new BookingStaxInnerUnmarshaller();
    }

    private static final class BookingStaxInnerUnmarshaller implements StaxInnerUnmarshaller<com.sergeev.itroi.util.wrapper.TicketArray> {

        private String current;

        private Aircraft aircraft;
        private Airline airline;
        private Airport airport;
        private com.sergeev.itroi.util.wrapper.TicketArray ticketArray = new com.sergeev.itroi.util.wrapper.TicketArray();
        private Coordinates coordinates;
        private Country country;
        private Flight flight;
        private Location location;
        private Passenger passenger;
        private Payment payment;
        private Price price;
        private Ticket ticket;

        @Override
        public com.sergeev.itroi.util.wrapper.TicketArray parse(XMLEventReader reader) {
            try {
                while (reader.hasNext()) {
                    XMLEvent event = reader.nextEvent();

                    if (event.isStartElement()) {
                        startElement(event);
                    }
                    if (event.isCharacters() && !event.asCharacters().isWhiteSpace()) {
                        characters(event);
                    }
                }
                reader.close();
            } catch (Exception e) {
                throw new XmlParserException(e);
            }
            return ticketArray;
        }

        private void startElement(XMLEvent event) {
            StartElement startElement = event.asStartElement();
            current = startElement.getName().getLocalPart();

            if (XML.TicketArray.TYPE_NAME.equals(current)) {
                ticketArray = new com.sergeev.itroi.util.wrapper.TicketArray();
            } else if (XML.TicketArray.TICKET.equals(current)) {
                ticket = new Ticket();
                ticket.setId(Integer.valueOf(getAttributeValue(startElement, XML.Ticket.ID)));
                ticketArray.getItems().add(ticket);
            } else if (XML.Ticket.AIRLINE.equals(current)) {
                airline = new Airline();
                airline.setCode(getAttributeValue(startElement, XML.Airline.CODE));
                ticket.setAirline(airline);
            } else if (XML.Ticket.FLIGHT.equals(current)) {
                flight = new Flight();
                flight.setId(Integer.valueOf(getAttributeValue(startElement, XML.Flight.ID)));
                ticket.setFlight(flight);
            } else if (XML.Flight.ARRIVAL_AIRPORT.equals(current)) {
                airport = new Airport();
                airport.setCode(getAttributeValue(startElement, XML.Airport.CODE));
                flight.setArrivalAirport(airport);
            } else if (XML.Flight.DEPARTURE_AIRPORT.equals(current)) {
                airport = new Airport();
                airport.setCode(getAttributeValue(startElement, XML.Airport.CODE));
                flight.setDepartureAirport(airport);
            } else if (XML.Airport.LOCATION.equals(current)) {
                location = new Location();
                airport.setLocation(location);
            } else if (XML.Location.COUNTRY.equals(current)) {
                country = new Country();
                country.setCode(getAttributeValue(startElement, XML.Country.CODE));
                location.setCountry(country);
            } else if (XML.Location.COORDINATES.equals(current)) {
                coordinates = new Coordinates();
                location.setCoordinates(coordinates);
            } else if (XML.Flight.AIRCRAFT.equals(current)) {
                aircraft = new Aircraft();
                aircraft.setCode(getAttributeValue(startElement, XML.Aircraft.CODE));
                flight.setAircraft(aircraft);
            } else if (XML.Ticket.PASSENGER.equals(current)) {
                passenger = new Passenger();
                passenger.setId(Integer.valueOf(getAttributeValue(startElement, XML.Passenger.ID)));
                ticket.setPassenger(passenger);
            } else if (XML.Ticket.PAYMENT.equals(current)) {
                payment = new Payment();
                payment.setId(Integer.valueOf(getAttributeValue(startElement, XML.Payment.ID)));
                ticket.setPayment(payment);
            } else if (XML.Payment.PRICE.equals(current)) {
                price = new Price();
                price.setCurrency(Currency.fromValue(getAttributeValue(startElement, XML.Price.CURRENCY)));
                payment.setPrice(price);
            }
        }

        @SuppressWarnings("Duplicates")
        private void characters(XMLEvent event) {
            Characters characters = event.asCharacters();
            String content = characters.getData();

            if (XML.Ticket.AIRLINE.equals(current)) {
                airline.setValue(content);
            } else if (XML.Ticket.SEAT.equals(current)) {
                ticket.setSeat(Integer.valueOf(content));
            } else if (XML.Ticket.SERVICE_TYPE.equals(current)) {
                ticket.setServiceType(ServiceType.fromValue(content));
            } else if (XML.Flight.DEPARTURE.equals(current)) {
                flight.setDeparture(ZonedDateTime.parse(content));
            } else if (XML.Flight.ARRIVAL.equals(current)) {
                flight.setArrival(ZonedDateTime.parse(content));
            } else if (XML.Flight.STATUS.equals(current)) {
                flight.setFlightStatus(FlightStatus.fromValue(content));
            } else if (XML.Airport.NAME.equals(current)) {
                airport.setAirportName(content);
            } else if (XML.Airport.TIMEZONE.equals(current)) {
                airport.setTimezone(Integer.valueOf(content));
            } else if (XML.Location.COUNTRY.equals(current)) {
                country.setValue(content);
            } else if (XML.Location.CITY.equals(current)) {
                location.setCity(content);
            } else if (XML.Coordinates.LATITUDE.equals(current)) {
                coordinates.setLatitude(Double.valueOf(content));
            } else if (XML.Coordinates.LONGITUDE.equals(current)) {
                coordinates.setLongitude(Double.valueOf(content));
            } else if (XML.Aircraft.MODEL.equals(current)) {
                aircraft.setModel(content);
            } else if (XML.Aircraft.SEATS.equals(current)) {
                aircraft.setSeats(Integer.valueOf(content));
            } else if (XML.Passenger.NAME.equals(current)) {
                passenger.setName(content);
            } else if (XML.Passenger.GENDER.equals(current)) {
                passenger.setGender(Gender.fromValue(content));
            } else if (XML.Passenger.EMAIL.equals(current)) {
                passenger.setEmail(content);
            } else if (XML.Passenger.BIRTHDATE.equals(current)) {
                passenger.setBirthdate(LocalDate.parse(content));
            } else if (XML.Payment.STATUS.equals(current)) {
                payment.setPaymentStatus(PaymentStatus.fromValue(content));
            } else if (XML.Payment.TYPE.equals(current)) {
                payment.setPaymentType(PaymentType.fromValue(content));
            } else if (XML.Payment.PRICE.equals(current)) {
                price.setValue(Double.parseDouble(content));
            } else if (XML.Ticket.BOOK_DATE.equals(current)) {
                ticket.setBookDate(LocalDate.parse(content));
            }
        }
    }
}
