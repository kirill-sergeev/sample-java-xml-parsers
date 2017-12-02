package com.sergeev.itroi.entity;

import com.sergeev.itroi.util.XML;
import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import java.time.LocalDate;

@Data
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = XML.Ticket.TYPE_NAME, namespace = XML.Ticket.SCHEMA_URI, propOrder = {
    XML.Ticket.AIRLINE,
    XML.Ticket.SEAT,
    XML.Ticket.SERVICE_TYPE,
    XML.Ticket.FLIGHT,
    XML.Ticket.PASSENGER,
    XML.Ticket.PAYMENT,
    XML.Ticket.BOOK_DATE
})
public class Ticket implements Identifiable<Integer> {

    @XmlAttribute(required = true)
    @XmlSchemaType(name = "positiveInteger")
    private Integer id;

    @XmlElement(required = true)
    private Airline airline;

    @XmlElement
    @XmlSchemaType(name = "positiveInteger")
    private Integer seat;

    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    private ServiceType serviceType;

    @XmlElement(required = true)
    private Flight flight;

    @XmlElement(required = true)
    private Passenger passenger;

    @XmlElement(required = true)
    private Payment payment;

    @XmlSchemaType(name = "date")
    private LocalDate bookDate;
}
