package com.sergeev.itroi.util;

@SuppressWarnings("all")
public final class XML {

    public static final String BOOKING_ROOT_URI = "http://itroi.sergeev.com/entity/";

    public static final class TicketArray {
        public static final String TYPE_NAME = "ticketArray";

        public static final String TICKET = Ticket.TYPE_NAME;
    }

    public static final class Ticket {
        public static final String TYPE_NAME = "ticket";
        public static final String SCHEMA_URI = BOOKING_ROOT_URI + TYPE_NAME;

        public static final String ID = "id";
        public static final String AIRLINE = Airline.TYPE_NAME;
        public static final String SEAT = "seat";
        public static final String SERVICE_TYPE = "serviceType";
        public static final String FLIGHT = Flight.TYPE_NAME;
        public static final String PASSENGER = Passenger.TYPE_NAME;
        public static final String PAYMENT = Payment.TYPE_NAME;
        public static final String BOOK_DATE = "bookDate";
    }

    public static final class Airline {
        public static final String TYPE_NAME = "airline";

        public static final String CODE = "code";
    }

    public static final class Flight {
        public static final String TYPE_NAME = "flight";
        public static final String SCHEMA_URI = BOOKING_ROOT_URI + TYPE_NAME;

        public static final String ID = "id";
        public static final String DEPARTURE = "departure";
        public static final String ARRIVAL = "arrival";
        public static final String DEPARTURE_AIRPORT = "departureAirport";
        public static final String ARRIVAL_AIRPORT = "arrivalAirport";
        public static final String AIRCRAFT = Aircraft.TYPE_NAME;
        public static final String STATUS = "flightStatus";
    }

    public static final class Airport {
        public static final String TYPE_NAME = "airport";

        public static final String SCHEMA_URI = BOOKING_ROOT_URI + TYPE_NAME;
        public static final String CODE = "code";
        public static final String NAME = "airportName";
        public static final String TIMEZONE = "timezone";
        public static final String LOCATION = Location.TYPE_NAME;
    }

    public static final class Location {
        public static final String TYPE_NAME = "location";

        public static final String COUNTRY = Country.TYPE_NAME;
        public static final String CITY = "city";
        public static final String COORDINATES = Coordinates.TYPE_NAME;
    }

    public static final class Country {
        public static final String TYPE_NAME = "country";

        public static final String CODE = "code";
    }

    public static final class Coordinates {
        public static final String TYPE_NAME = "coordinates";

        public static final String LATITUDE = "latitude";
        public static final String LONGITUDE = "longitude";
    }

    public static final class Aircraft {
        public static final String TYPE_NAME = "aircraft";

        public static final String SCHEMA_URI = BOOKING_ROOT_URI + TYPE_NAME;
        public static final String CODE = "code";
        public static final String MODEL = "model";
        public static final String SEATS = "seats";
    }

    public static final class Passenger {
        public static final String TYPE_NAME = "passenger";
        public static final String SCHEMA_URI = BOOKING_ROOT_URI + TYPE_NAME;

        public static final String ID = "id";
        public static final String NAME = "name";
        public static final String GENDER = "gender";
        public static final String BIRTHDATE = "birthdate";
        public static final String EMAIL = "email";
    }

    public static final class Payment {
        public static final String TYPE_NAME = "payment";
        public static final String SCHEMA_URI = BOOKING_ROOT_URI + TYPE_NAME;

        public static final String ID = "id";
        public static final String PRICE = Price.TYPE_NAME;
        public static final String TYPE = "paymentType";
        public static final String STATUS = "paymentStatus";
    }

    public static final class Price {
        public static final String TYPE_NAME = "price";

        public static final String CURRENCY = "currency";
    }
}
