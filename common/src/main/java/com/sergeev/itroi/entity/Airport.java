package com.sergeev.itroi.entity;

import com.sergeev.itroi.util.XML;
import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = XML.Airport.TYPE_NAME, namespace = XML.Airport.SCHEMA_URI)
public class Airport implements Codifiable<String> {

    @XmlAttribute(required = true)
    private String code;

    @XmlElement(required = true)
    @XmlSchemaType(name = "token")
    private String airportName;

    @XmlElement(required = true)
    @XmlSchemaType(name = "integer")
    private Integer timezone;

    @XmlElement(required = true)
    private Location location;
}
