package com.sergeev.itroi.util.metadata;

import com.sergeev.itroi.util.XML;

public enum TicketSchemaMetadata implements SchemaMetadata {

    AIRCRAFT(XML.Aircraft.TYPE_NAME),
    AIRPORT(XML.Airport.TYPE_NAME),
    FLIGHT(XML.Flight.TYPE_NAME),
    PASSENGER(XML.Passenger.TYPE_NAME),
    PAYMENT(XML.Payment.TYPE_NAME),
    TICKET(XML.Ticket.TYPE_NAME);

    private final String typeName;

    TicketSchemaMetadata(String typeName) {
        this.typeName = typeName;
    }

    public String getPrefix() {
        return typeName;
    }

    public String getURI() {
        return XML.BOOKING_ROOT_URI + typeName;
    }
}
