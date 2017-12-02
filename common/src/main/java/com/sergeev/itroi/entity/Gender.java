package com.sergeev.itroi.entity;

import com.sergeev.itroi.util.XML;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = XML.Passenger.GENDER, namespace = XML.Passenger.SCHEMA_URI)
@XmlEnum
public enum Gender {

    MALE,
    FEMALE,
    OTHER;

    public static Gender fromValue(String v) {
        return valueOf(v);
    }

    public String value() {
        return name();
    }
}
