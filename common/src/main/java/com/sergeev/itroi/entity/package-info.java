@XmlJavaTypeAdapters({
    @XmlJavaTypeAdapter(value = CollapsedStringAdapter.class, type = String.class),
    @XmlJavaTypeAdapter(value = LocalDateAdapter.class, type = LocalDate.class),
    @XmlJavaTypeAdapter(value = ZonedDateTimeAdapter.class, type = ZonedDateTime.class)
})

@XmlSchema(elementFormDefault = XmlNsForm.QUALIFIED,
    xmlns = {
        @XmlNs(prefix = XML.Ticket.TYPE_NAME, namespaceURI = XML.Ticket.SCHEMA_URI),
        @XmlNs(prefix = XML.Aircraft.TYPE_NAME, namespaceURI = XML.Aircraft.SCHEMA_URI),
        @XmlNs(prefix = XML.Airport.TYPE_NAME, namespaceURI = XML.Airport.SCHEMA_URI),
        @XmlNs(prefix = XML.Flight.TYPE_NAME, namespaceURI = XML.Flight.SCHEMA_URI),
        @XmlNs(prefix = XML.Passenger.TYPE_NAME, namespaceURI = XML.Passenger.SCHEMA_URI),
        @XmlNs(prefix = XML.Payment.TYPE_NAME, namespaceURI = XML.Payment.SCHEMA_URI)})
package com.sergeev.itroi.entity;

import com.sergeev.itroi.util.XML;
import com.sergeev.itroi.util.adapter.LocalDateAdapter;
import com.sergeev.itroi.util.adapter.ZonedDateTimeAdapter;

import javax.xml.bind.annotation.XmlNs;
import javax.xml.bind.annotation.XmlNsForm;
import javax.xml.bind.annotation.XmlSchema;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapters;
import java.time.LocalDate;
import java.time.ZonedDateTime;
