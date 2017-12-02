package com.sergeev.itroi.entity;

import com.sergeev.itroi.util.XML;
import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import java.time.ZonedDateTime;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = XML.Flight.TYPE_NAME, namespace = XML.Flight.SCHEMA_URI, propOrder = {
    XML.Flight.DEPARTURE,
    XML.Flight.ARRIVAL,
    XML.Flight.STATUS,
    XML.Flight.DEPARTURE_AIRPORT,
    XML.Flight.ARRIVAL_AIRPORT,
    XML.Flight.AIRCRAFT
})
public class Flight implements Identifiable<Integer> {

    @XmlAttribute(required = true)
    @XmlSchemaType(name = "positiveInteger")
    private Integer id;

    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    private ZonedDateTime departure;

    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    private ZonedDateTime arrival;

    @XmlElement(required = true)
    @XmlSchemaType(name = "token")
    private FlightStatus flightStatus;

    @XmlElement(required = true)
    private Airport departureAirport;

    @XmlElement(required = true)
    private Airport arrivalAirport;

    @XmlElement(required = true)
    private Aircraft aircraft;
}
