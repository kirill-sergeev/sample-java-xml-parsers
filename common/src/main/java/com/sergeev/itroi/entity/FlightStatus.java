package com.sergeev.itroi.entity;

import com.sergeev.itroi.util.XML;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = XML.Flight.STATUS, namespace = XML.Flight.SCHEMA_URI)
@XmlEnum
public enum FlightStatus {

    EXPECTED,
    IN_THE_AIR,
    RESCHEDULED,
    COMPLETED,
    CANCELED;

    public static FlightStatus fromValue(String v) {
        return valueOf(v);
    }

    public String value() {
        return name();
    }
}
