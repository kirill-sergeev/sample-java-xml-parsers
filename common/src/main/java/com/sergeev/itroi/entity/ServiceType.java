package com.sergeev.itroi.entity;

import com.sergeev.itroi.util.XML;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = XML.Ticket.SERVICE_TYPE, namespace = XML.Ticket.SCHEMA_URI)
@XmlEnum
public enum ServiceType {

    BUSINESS,
    ECONOMY,
    FIRST,
    PREMIUM;

    public static ServiceType fromValue(String v) {
        return valueOf(v);
    }

    public String value() {
        return name();
    }
}
