package com.sergeev.itroi.entity;

import com.sergeev.itroi.util.XML;
import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = XML.Location.TYPE_NAME, namespace = XML.Airport.SCHEMA_URI)
public class Location {

    @XmlElement(required = true)
    private Country country;

    @XmlElement
    @XmlSchemaType(name = "token")
    private String city;

    @XmlElement(required = true)
    private Coordinates coordinates;
}
